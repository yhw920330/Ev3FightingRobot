package Team;

public class LineTracer {

	static public int direction;
	static public final int RIGHT = 1;
	static public final int LEFT = -1;

	private boolean wheel_init=false;
	//private RotateController controllerTracer;
	public LineTracer(){
		init();
	}
	public boolean isOnline() {
		return LightSensor_Interface.isOnline();
	}
	public boolean isBlack(){
		if (LightSensor_Interface.getLight() <= 0.4) 
			return true;
		else
			return false;
	}
	/*
	public void run() {

		dis = controllerTracer.sensingBlock();
		isReadyToBurst = FALSE;

		if (LightSensor_Interface.getLight() <= 0.4) {
			if (dis > 0.35 && isReadyToBurst == TRUE) {
				WheelControl.forward(3);
			} else {
				if (isReadyToBurst == FALSE)
					isReadyToBurst = TRUE;
				else
					isReadyToBurst = FALSE;
				WheelControl.forward(0.5);

				outCount = 0;
			}
		}
		// Out of line
		else {

			// WheelControl.stop();
			WheelControl.backward(0.3);
			WheelControl.rotate(5 * direction);
			outCount++;
			// Changing direction 
			if(outCount>2){
				direction*=-1; 
				outCount=0;
			}
		}
		if ( start_tick - (int) java.lang.System.currentTimeMillis() == 30 );
	}
	*/
	private void init(){
//		initailization
		direction = RIGHT;
		if(!wheel_init){
			wheel_init=true;
			WheelControl.init();
		}		
	}
	
}
