package Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import lejos.hardware.Bluetooth;
import lejos.remote.nxt.NXTConnection;

public class BackThread extends Thread {

//	InputStream in;
	private int data;
/*
	public BackThread(InputStream in) {
		// TODO Auto-generated constructor stub
		this.in = in;
	}
*/
	NXTConnection bl = Bluetooth.getNXTCommConnector().waitForConnection(
			1000, NXTConnection.RAW);
	private InputStream in ;

	public void run() {
		
		in = bl.openDataInputStream();
	
		while (true) {
			// System.out.println("BACK THREAD");
			try {
				// Get order of user android 
				data = in.read();

				MovingControl.Data = data;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}