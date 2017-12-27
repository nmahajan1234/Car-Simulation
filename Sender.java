

import java.io.*;
import java.net.*;
public class Sender extends Thread {
	
	public Sender() {}

	public void run() {
	
	try {
	int multicastPort = 6688;
	MulticastSocket socket = new MulticastSocket();
	InetAddress group =InetAddress.getByName("250.45.12.77");
	String message;
	StringBuffer carPos = new StringBuffer();
	
	
		while(true) {
			

			carPos.append("1,"+Tester.car1frontX+","+Tester.car1frontY+","+Tester.car1middleX+","+Tester.car1middleY+","+Tester.car1endX+","+Tester.car1endY+",");
			
			message = carPos.toString();
			
			byte[] msg = message.getBytes();
			DatagramPacket dpkt = new DatagramPacket(msg,msg.length, group, multicastPort);
			socket.send(dpkt);
			//System.out.println(message);
			Thread.sleep(1000);
			carPos.delete(0,carPos.length());
			
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
}

