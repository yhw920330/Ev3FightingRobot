package Team;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
public class RotateController {

	private  EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S2);//color
	private  EV3UltrasonicSensor sonarSensor = new EV3UltrasonicSensor(SensorPort.S3);//sonar

	public RotateController() {
		// TODO Auto-generated constructor stub
	}
	public int getColor() {
		return colorSensor.getColorID();
	}
	public float sensingBlock(){
		float[] sample = new float[sonarSensor.sampleSize()];
		sonarSensor.fetchSample(sample, 0);
		return sample[0];
	}
}