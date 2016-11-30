package Team;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.utility.Stopwatch;


/**
 * Example leJOS EV3 Project with an ant build file
 *
 */
public class LightSensor_Interface{
	  private static boolean outline_flag = false;
	  private static Stopwatch stopwatch = new Stopwatch();//leJOS API
	  private static NXTLightSensor sensor = new NXTLightSensor(SensorPort.S4);//leJOS API
	  private static float[] sample = new float[sensor.getRedMode().sampleSize()];
	  public static float getLight(){
		  sensor.getRedMode().fetchSample(sample, 0);
		  return sample[0];
	  }
	  public static boolean isOnline(){//	  
		  sensor.getRedMode().fetchSample(sample, 0);
//		  System.out.println(sample[0]);
		  if(sample[0]<=0.35){//on line
			  outline_flag  = true;
			  return true;
		  } else{
			  outline_flag  = false;
			  return false;
		  }
	  }
	  public static int getTime(){
		  return stopwatch.elapsed();//milli-second
	  }
//	  public static void main(String[] args) {
//		  float[] sample = new float[sensor.sampleSize()];
//		  while(true){
//			  sensor.getRedMode().fetchSample(sample, 0);
//			  System.out.println(sample[0]);
//		  }
//	  }
}
