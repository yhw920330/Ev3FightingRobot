package Team;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Stopwatch;
import java.lang.System;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.NXTConnection;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.utility.Stopwatch;


import java.io.IOException;
import java.io.InputStream;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.Color;
import lejos.utility.Delay;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;


import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Stopwatch;
import java.lang.System;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;



import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;




/**
 * Example leJOS EV3 Project with an ant build file
 *
 */
public class TouchSensor_Interface{
	  private static boolean collison_flag = false;
	  private static long lasttick = 0;
	  private static int collison_count = 0;
	  
	  private static Stopwatch stopwatch = new Stopwatch();//leJOS API
	
	  private static EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);//leJOS API
	  private static float[] sample = new float[sensor.sampleSize()];
	  public static boolean isPressed(){
		  sensor.fetchSample(sample, 0);
		  if(java.lang.System.currentTimeMillis() - lasttick > 1000){
			  if(collison_count>0) collison_count--;
			  lasttick = java.lang.System.currentTimeMillis();
		  }
		  
		  if(sample[0]==1){ // touched 
			  if(collison_flag==false){
				  collison_count++;
				  stopwatch.reset();
			  }
			  collison_flag = true;
			  return true;
		  }else{
			  collison_flag = false;
			  return false;
		  }
	  }
	  
	  public static int getCount(){
		  return collison_count; //충돌 횟수 카운트 기능 필요? 
	  }
	  public static int getTime(){
		  if(collison_flag)
			  return stopwatch.elapsed();//milli-second
		  else
			  return 9999;
	  }
	  
	  public static void main(String[] args) {
		  float[] sample = new float[sensor.sampleSize()];
		 
		  System.out.println("start!!!!!!!!!!!!!");
		  while(true){
			  sensor.fetchSample(sample, 0);
			  System.out.println(sample[0]);
		  }
	  }
}
