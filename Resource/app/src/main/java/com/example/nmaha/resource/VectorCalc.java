package com.example.nmaha.resource;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class VectorCalc{
//private ServerSocket serversocket;
private Socket socket;
private PrintStream output;
private BufferedReader input;
private Scanner scan = new Scanner(System.in);


 public void run(){
    try{

        socket = new Socket("192.168.1.130",40000);




        if (socket.isConnected()){
            OutputStream os = socket.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            bw.write("kk");
//            System.out.println(msg);
            Log.d("received msg", "msg");
            bw.flush();

            String reply = "getting msg";
            output.println(reply);

        }

    }
    catch (Exception e){
        e.printStackTrace();
    }
 }

}