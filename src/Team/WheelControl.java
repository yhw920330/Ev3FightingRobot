package Team;

//import org.lejos.ev3.sample.bumpercar.EV3BumperCar;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class WheelControl {
	
 static DifferentialPilot p = new DifferentialPilot(2.1f, 4.4f, Motor.B, Motor.C);
 
 /*
  * go forward 0.3mm at once 
  * velocity 10cm/s
  */
	 static public void init(){
		 //p.setTravelSpeed(  );
		 p.setTravelSpeed(10);
	 }
	 static public void forward(double range) {
		 p.travel(range);//p.travel(0.3);
	 }
	 static public void backward(double range) {
		 p.travel(-1*range);
	 }
 /*
  * stop
  */
	 static public void stop(){
		System.out.println("WheelControl::stop()");
		p.quickStop(); 
	 }

 /*
  * ratate angle
  */
	 static public void rotate(int angle) {
	    p.rotate(angle);
	 }
	 static public void rotateLeft(){
		rotate(-90);
	 }
	 static public void rotateRight(){
		rotate(90);
	 }
	 // p.rotateLeft();//왼쪽으로 90도 돌기
	 static public void main(String args[]){
	//	 init();
	//	 while(true)
	 }
 
 
}