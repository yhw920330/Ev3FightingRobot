package Team;

import java.io.IOException;
import java.io.InputStream;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class MovingControl {

	static private int isReadyToBurst;
	static public final int TRUE = 1;
	static public final int FALSE = 0;
	static private int outCount = 0;
	static private int MAX = 3;
	static public int Data;
	static int result;
	static int des;
	
	
		public static void main(String[] args) {

		boolean isPause = true;  // 긴급 정지
		int color; //
		float dis; //
		RotateController controller = new RotateController();
		
		int start_tick=0, end_tick = 0, pause_start_tick=0, pause_end_tick=0;
			
		LineTracer tracer = new LineTracer();
		isReadyToBurst = 0;
		
		BackThread thread = new BackThread();
		thread.start();
		
		while (true) {
					
			switch (Data) {
			
			case 0:
				des = Des.A;
				//start_tick = (int)java.lang.System.currentTimeMillis();
				isPause = false;
				break;
			case 1:
				des = Des.B;
				isPause = false;
				break;
			// Stop
			case 2:
				WheelControl.stop();
				return;
				// Pause
			case 3:
				WheelControl.stop();
				//pause_start_tick = (int)java.lang.System.currentTimeMillis();
				isPause = true;
				break;
			// Restart
			case 4:
				//pause_end_tick = (int)java.lang.System.currentTimeMillis();
				start_tick = (int)java.lang.System.currentTimeMillis();
				isPause = false;
				break;
			default:
				break;

			}
			if (!isPause) {
				// 30초 이내 도착 검사
				// if ((int) java.lang.System.currentTimeMillis() -
				// start_tick >
				// 20 * 1000) {
				// System.out.printf("Time out %d\n",
				// (int)java.lang.System.currentTimeMillis() - start_tick);
				// return;
				// }
				// 충돌 횟수 검사
				if (TouchSensor_Interface.getCount() >= 15) {
					System.out.printf("Touch Count %d\n",
							TouchSensor_Interface.getCount());
					// return;
				}
				// if (dis < 0.053) {
				// LineTracer.stop();vc
				color = controller.getColor();
				if (colorRotate(color, des)) {
					Delay.msDelay(5 * 1000);
					System.out.println("Running Time " + (end_tick-start_tick/*-pause_start_tick+pause_end_tick*/)/1000);
					return;
				}
				// }
				if (TouchSensor_Interface.isPressed()) {
					WheelControl.backward(5);
				}

				end_tick = (int)System.currentTimeMillis();

				// if(tracer.isBlack()){
				lineTracing(controller);
				// }
				// if((start_tick -end_tick)/1000 > 30)
				// break;
			}
		}
		
	}

	
	static private void lineTracing(RotateController controllerTracer) {

		double dis = controllerTracer.sensingBlock();
		System.out.println(dis);
		// in Line
		if (LightSensor_Interface.getLight() <= 0.4) {
			if (dis > 0.15 && isReadyToBurst == MAX) {
				WheelControl.forward(3);
			} else {
				isReadyToBurst++;
				WheelControl.forward(0.5);
				outCount = 0;
			}
		}
		// Out of line
		else {
			WheelControl.backward(0.3);
			WheelControl.rotate(5 * LineTracer.direction);
			outCount++;
			isReadyToBurst = 0;
			// Changing direction
			if (outCount > 1) {
				LineTracer.direction *= -1;
				outCount = 0;
			}
		}
	}

	static private boolean colorRotate(int color, int des) {

		switch (color) {
		case Color.RED:
			if (des == Des.A) {
				WheelControl.rotate(-30);
			} else {
				WheelControl.rotate(30);
			}
			WheelControl.forward(2.5);
			break;
		case Color.BLUE:
			if (des == Des.A) {
				WheelControl.rotate(30);
			} else {
				WheelControl.rotate(-30);
			}
			WheelControl.forward(2.5);
			break;
		case Color.WHITE:
			return true;
		}

		return false;
	}

}

// set destination
class Des {
	public static final int A = 0;
	public static final int B = 1;
}