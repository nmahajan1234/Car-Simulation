

public class MainThread {
	
	public static double car1frontX =-510;
	public static double car1frontY =10;
	public static double car1middleX = -510;
	public static double car1middleY = 0;
	public static double car1endX = -510;
	public static double car1endY = -10;
	
	
	public static double car2frontX =-450;
	public static double car2frontY = 10;
	public static double car2middleX = -450;
	public static double car2middleY = 0;
	public static double car2endX = -450;
	public static double car2endY = -10;
	
	public static double car3frontX =-390;
	public static double car3frontY = 10;
	public static double car3middleX = -390;
	public static double car3middleY = 0;
	public static double car3endX = -390;
	public static double car3endY = -10;
	
	public static void main(String args[]){
		
		
	try {
		Receiver r = new Receiver();
		r.start();
		Sender sr = new Sender();
		sr.start();
		
		RpiServer server = new RpiServer();
		server.start();
		
		RpiServer2 server2 = new RpiServer2();
		server2.start();
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
	}

}

