package com.example.nmaha.resource;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.example.nmaha.resource.MainActivity.pointStartCar1;
import static com.example.nmaha.resource.MainActivity.pointStartCar2;
import static com.example.nmaha.resource.MainActivity.pointStartCar3;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar1;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar2;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar3;
import static com.example.nmaha.resource.MainActivity.pointEndCar1;
import static com.example.nmaha.resource.MainActivity.pointEndCar2;
import static com.example.nmaha.resource.MainActivity.pointEndCar3;

import static com.example.nmaha.resource.MainActivity.context;
import static com.example.nmaha.resource.MainActivity.toast1;
import static com.example.nmaha.resource.MainActivity.toast2;





public class MyClientTask extends AsyncTask<Void, Void, Void> {


    //    private static Socket socket;

    double[] start = new double[2];
    double[] middle = new double[2];
    double[] end = new double[2];

    //CanvasView c1;
    MyClientTask() {

        start[0] = pointStartCar3[0];
        Log.d("start",String.valueOf(pointStartCar3[0]));
        start[1] = pointStartCar3[1];
        middle[0] = pointMiddleCar3[0];
        middle[1] = pointMiddleCar3[1];
        end[0] = pointEndCar3[0];
        end[1] = pointEndCar3[1];

    }

    @Override
    protected Void doInBackground(Void... arg0) {

//                ServerSocket serversocket = new ServerSocket(40000);
        try {
            DatagramSocket socket_send = null;

            try {
                socket_send = new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }

            //      ServerSocket serversocket = new ServerSocket(40000);
//            int[] buf = new int[6];
//            String[] buf_s;
//            int port = 4000;
//            String iaddress = "192.168.1.130";
//            Socket socket = new Socket(iaddress, port);
//            //              Log.d("iaddress is ", String.valueOf(iaddress));
//
//            os  = new DataOutputStream(socket.getOutputStream());
//
//            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            InetAddress hostAddress = InetAddress.getByName("192.168.1.131");
//            InetAddress hostAddress = InetAddress.getByName("192.168.0.23");

            //String buttonPressed = "accelerate";
            StringBuffer coordinates = new StringBuffer();

            coordinates.append(start[0] + "," + start[1] + "," + middle[0] + "," + middle[1] + "," + end[0] + "," + end[1]+",");
            byte[] outbyte = new byte[1024];
            outbyte = coordinates.toString().getBytes();
//            Log.d ("socket","sent");
            DatagramPacket outpkt = new DatagramPacket(outbyte, outbyte.length, hostAddress, 7009);
            Log.d ("socket","packt made");
            socket_send.send(outpkt);
            Log.d ("socket","sent");
            coordinates.delete(0,coordinates.length());
            socket_send.close();

//            String coord = start[0] + "," + start[1] + "," + middle[0] + "," + middle[1] + "," + end[0] + "," + end[1];
//            //  String coord = "1,2,3,4,5,6";
//            os.writeBytes(coord+"\n");


//            String responseLine = is.readLine();
//
//            int c=0;
//			/*for (int i = 0; i < buf_s.length; i++)
//			{
//			buf[i] = Integer.parseInt(buf_s[i]);
//			if (buf.length == 6) break;
//			}*/
//            System.out.println("All coordinates are:\n " + responseLine);
//		/*for (int i = 0; i < 6; i++)
//			System.out.print(buf[i]+",");
//		*/
//            String[] value2 = responseLine.split(",");



//            c1.pointStartCar[0] = Double.parseDouble(value2[0]);
//            c1.pointStartCar[1] = Double.parseDouble(value2[1]);
//            c1.pointMiddleCar[0] = Double.parseDouble(value2[2]);
//            c1.pointMiddleCar[1] = Double.parseDouble(value2[3]);
//            c1.pointEndCar[0] = Double.parseDouble(value2[4]);
//            c1.pointEndCar[1] = Double.parseDouble(value2[5]);
////            Log.d("initial",String.valueOf(c1.pointStartCar[0]));
////            Log.d("initial",String.valueOf(c1.pointEndCar[0]));
////            Log.d("after", String.valueOf(c1.prevpointStartCar[0]));
////            Log.d("after", String.valueOf(c1.prevpointEndCar[0]));
//            c1.give(c1.prevpointStartCar,c1.prevpointMiddleCar,c1.prevpointEndCar);
//            os.close();
//            is.close();
            //socket.close();
        }catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onPostExecute(Void result) {
//        textResponse.setText(response);
        super.onPostExecute(result);
    }

    private static int isParseInt(String str) {

        int num = -1;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }

        return num;
    }

}