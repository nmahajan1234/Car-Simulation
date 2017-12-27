
import java.io.*;
import java.net.*;
public class RpiServer2 extends Thread{
	
	DatagramSocket sendsocket ;

	Rpi2RpiServer2() throws SocketException {
		
	}
	
	public void run() {
		try {
			byte[] incomingData = new byte[1024];
			sendsocket = new DatagramSocket();
			while(true) {

				InetAddress ClientIP = InetAddress.getByName("192.168.0.16");;
				int port = 8008;
				System.out.println("Server2");
				StringBuffer SendBuffer = new StringBuffer();
				SendBuffer.append(Tester.car2frontX+","+Tester.car2frontY+","+Tester.car2middleX+","+Tester.car2middleY+","+Tester.car2endX+","+Tester.car2endY+",");
				SendBuffer.append(Tester.car3frontX+","+Tester.car3frontY+","+Tester.car3middleX+","+Tester.car3middleY+","+Tester.car3endX+","+Tester.car3endY+",");
				byte[] forward = new byte[1024];
				forward = SendBuffer.toString().getBytes();
				System.out.println("constructing packet");
				DatagramPacket OutPacket = new DatagramPacket(forward, forward.length, ClientIP, port);
				sendsocket.send(OutPacket);
				System.out.println("packet Sent");
//				socket.close();
				
				Thread.sleep(400);
			}
		}
		catch (Exception e) {
			System.out.println("error");
		}
	}
	
	
}
