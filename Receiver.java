

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver extends Thread {
	
	public Receiver() {
		
	}
		
		public void run(){
			
			try{
				byte[] ReceiveBuf = new byte[256];
				MulticastSocket socket = new MulticastSocket(6688);
				InetAddress NwGroup = InetAddress.getByName("250.45.12.77");
				socket.joinGroup(NwGroup);
				while (true){
					DatagramPacket packet = new DatagramPacket(ReceiveBuf, ReceiveBuf.length);
					socket.receive(packet);
					String rmsg = new String(packet.getData(),0,packet.getLength());
					
					String[] Positions = rmsg.split(",");

					if(Positions[0].equals("2")) {
						Tester.car2frontX = Double.parseDouble(Positions[1]);
						Tester.car2frontY = Double.parseDouble(Positions[2]);
						Tester.car2middleX = Double.parseDouble(Positions[3]);
						Tester.car2middleY = Double.parseDouble(Positions[4]);
						Tester.car2endX = Double.parseDouble(Positions[5]);
						Tester.car2endY = Double.parseDouble(Positions[6]);
					}
					if(Positions[0].equals("3")) {
						Tester.car3frontX = Double.parseDouble(Positions[1]);
						Tester.car3frontY = Double.parseDouble(Positions[2]);
						Tester.car3middleX = Double.parseDouble(Positions[3]);
						Tester.car3middleY = Double.parseDouble(Positions[4]);
						Tester.car3endX = Double.parseDouble(Positions[5]);
						Tester.car3endY = Double.parseDouble(Positions[6]);
					}
					Thread.sleep(1000);
				}
				
			}catch (Exception e){
				
			}
			
		}
		
}

