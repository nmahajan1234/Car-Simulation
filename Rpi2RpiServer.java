import java.io.*;
import java.net.*;
public class Rpi2RpiServer extends Thread{
	DatagramSocket socket ;

	
	RpiServer() throws SocketException {
		
	}
	
	public void run() {
		try {
			byte[] incomingData = new byte[1024];
			socket = new DatagramSocket(8001);
			while(true) {
//				socket = new DatagramSocket(9879);
//				socket.setReuseAddress(true);
				
				// System.out.println("socket");
				DatagramPacket ipkt = new DatagramPacket(incomingData, incomingData.length);
				socket.receive(ipkt);
				// System.out.println("received");
				InetAddress ipClient = ipkt.getAddress();
				int port = ipkt.getPort();
				String rData = new String(ipkt.getData(),0,ipkt.getLength());
				
				String[] pos1Data = rData.split(",");
				
				Tester.car1frontX = Double.parseDouble(pos1Data[0]);
				Tester.car1frontY = Double.parseDouble(pos1Data[1]);
				Tester.car1middleX = Double.parseDouble(pos1Data[2]);
				Tester.car1middleY = Double.parseDouble(pos1Data[3]);
				Tester.car1endX = Double.parseDouble(pos1Data[4]);
				Tester.car1endY = Double.parseDouble(pos1Data[5]);
				

				StringBuffer sb = new StringBuffer();

				sb.append(Tester.car1frontX+","+Tester.car1frontY+","+Tester.car1middleX+","+Tester.car1middleY+","+Tester.car1endX+","+Tester.car1endY);

//				byte[] forward = new byte[1024];
//				forward = sb.toString().getBytes();
//				String rMessage = new String(ipkt.getData(),0,ipkt.getLength());
//				String[] mesg = rMessage.split(","); 
				for(String s : pos1Data ) {
					System.out.print(s);
				}
				System.out.println();
//				DatagramPacket opkt = new DatagramPacket(forward, forward.length, ipClient, port);
///				socket.send(opkt);
				
				Thread.sleep(500);
//				socket.close();
				
			}
		}
		catch (Exception e) {
			System.out.println("errr");
		}
	}
	
	
}
