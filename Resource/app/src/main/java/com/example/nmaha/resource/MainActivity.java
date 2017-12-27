package com.example.nmaha.resource;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class MainActivity extends Activity {


    public static double[] pointStartCar1 = new double[2];
    public static double[] pointEndCar1 = new double[2];
    public static double[] pointMiddleCar1 = new double[2];

    public static double[] pointStartCar2 = new double[2];
    public static double[] pointEndCar2 = new double[2];
    public static double[] pointMiddleCar2 = new double[2];

    public static double[] pointStartCar3 = new double[2];
    public static double[] pointEndCar3 = new double[2];
    public static double[] pointMiddleCar3 = new double[2];

    public static double[] prevPointStartCar3 = new double[2];
    public static double[] prevPointEndCar3 = new double[2];
    public static double[] prevPointMiddleCar3 = new double[2];

    public static boolean raceMode = true;
    public static boolean cityMode = false;

    public static Context context;
    public static Toast toast1, toast2;
    private CanvasView customCanvas;
    private Handler repeatUpdateHandler = new Handler();
    private Handler repeatUpdateHandler1 = new Handler();

    private boolean pressed = false;
    private boolean pressed1 = false;
    private int REP_DELAY = 16;


    //    TextView text = (TextView) findViewById (R.id.textView);
    private Switch modeSwitch;

    public static int aflag = 0;
    public long duration = 16;


    class RptUpdater implements Runnable {
        public void run() {

            if (pressed) {
                //do your job here
                //customCanvas.translateCar();
                customCanvas.accelerateCar();
//                customCanvas.call();


                //Log.d("Button Debugger","Continuosly Pressed");

                repeatUpdateHandler.postDelayed(new RptUpdater(), REP_DELAY);
            } else {
//                customCanvas.call();

                //customCanvas.resetStep();
            }
        }
    }

    class RptUpdater1 implements Runnable {
        public void run() {

            if (pressed1) {
                //do your job here
                //customCanvas.translateCar();

                customCanvas.deaccelerateCar();

                // Log.d("Button Debugger","Continuosly Pressed");

                repeatUpdateHandler1.postDelayed(new RptUpdater1(), REP_DELAY);
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        final Button accButton = (Button) findViewById(R.id.Accelerate);
//        Button resetCarBtn = (Button)findViewById(R.id.);
        final Button brakeButton = (Button) findViewById(R.id.Brake);
        final ToggleButton onOffButton = (ToggleButton) findViewById(R.id.toggleButton);
        modeSwitch = (Switch) findViewById (R.id.switch1);
//        Thread mythread = new Thread(new doit());
//        mythread.start();

//        position.setText ("abcd");

        MyThreadTask myThreadTask = new MyThreadTask();

        myThreadTask.start();

/*
        if( (Math.abs (pointMiddleCar3[0] - pointMiddleCar2[0]) < 50 || Math.abs (pointMiddleCar3[1] - pointMiddleCar2[1]) < 50)
                || (Math.abs (pointMiddleCar3[0] - pointMiddleCar1[0]) < 50 || Math.abs (pointMiddleCar3[1] - pointMiddleCar1[1]) < 50) ) {
            text.setText ("CAUTION");
        } else {

        }
*/


        onOffButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean enable) {

                if (enable) {
                    Log.d("OnCheckChnaged", String.valueOf("Enable: " + enable));
/*
                        if(modeSwitch.isChecked ())
                            raceMode = true;
                        else
                            raceMode = false;
*/

                    modeSwitch.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener (){
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                            if(isChecked){
                                raceMode = false;
                                cityMode = true;
                            }
                            else {
                                raceMode = true;
                                cityMode = false;
                            }
                        }
                    });

                    accButton.setClickable(true);
                    brakeButton.setClickable(true);
                    accButton.setLongClickable(true);
                    brakeButton.setLongClickable(true);


                    accButton.setOnLongClickListener(
                            new View.OnLongClickListener() {
                                public boolean onLongClick(View arg0) {
                                    pressed = true;
                                    aflag = 1;
//                                    MyClientTask myClientTask = new MyClientTask("192.168.1.130", 40000, aflag);
//                                    myClientTask.execute();
                                    repeatUpdateHandler.post(new RptUpdater());
//                                    text.setText ("abcd");
                                    return false;
                                }
                            }
                    );


                    accButton.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // Log.d("Event Debugger",""+event.getAction());
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                pressed = true;
                                //do your job here
                                aflag = 1;
//                                MyClientTask myClientTask = new MyClientTask("192.168.1.130", 40000, aflag);
//                                myClientTask.execute();
                                customCanvas.accelerateCar();
                                // Log.d("Button Debugger","Continuosly Pressed");
                            } else {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    Log.d("Button Debugger", "Up");
                                    aflag = 0;
//                                    MyClientTask myClientTask = new MyClientTask("192.168.1.130", 40000, aflag);
//                                    myClientTask.execute();
                                    pressed = false;
                                    // customCanvas.deaccelerateCar();


                                }
                            }
                            return false;
                        }
                    });


                    brakeButton.setOnLongClickListener(
                            new View.OnLongClickListener() {
                                public boolean onLongClick(View arg0) {
                                    pressed1 = true;
                                    aflag = 2;
                                    repeatUpdateHandler1.post(new RptUpdater1());
                                    return false;
                                }
                            });

                    brakeButton.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // Log.d("Event Debugger",""+event.getAction());
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                pressed1 = true;
                                //do your job here
                                aflag = 2;
//                                MyClientTask myClientTask = new MyClientTask("192.168.1.125", 40000, aflag);
//                                myClientTask.execute();
                                customCanvas.deaccelerateCar();


                                // Log.d("Button Debugger","Continuosly Pressed");
                            } else {
                                if (event.getAction() == MotionEvent.ACTION_UP) {
                                    Log.d("Button Debugger", "Up");
                                    aflag = 0;
//                                    MyClientTask myClientTask = new MyClientTask("192.168.1.125", 40000, aflag);
//                                    myClientTask.execute();
                                    pressed1 = false;
                                }
                            }
                            return false;
                        }
                    });


                } else { //For Toggle button

                    Log.d("OnCheckChnaged", String.valueOf("Enable: " + enable));
                    accButton.setClickable(false);
                    brakeButton.setClickable(false);
                    accButton.setLongClickable(false);
                    brakeButton.setLongClickable(false);
//                    modeSwitch.setClickable (false);

                }
            }
        });


    }
//    class doit implements Runnable{
//        public void run() {
//
//
//
//        }
//    }

    public void resetCarControllerFunc(View v) {
        customCanvas.resetCar();
    }

    ;


    public class MyThreadTask extends Thread {

        MyThreadTask() {

            Log.d("Thread :","In thread .........");
        }

        public void run() {
            DatagramSocket  receiveSocket = null;

            try {
                receiveSocket = new DatagramSocket(8004);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Log.d("data","before receiving packet in thread");
                    byte[] receiveBuffer = new byte[1024];
                    DatagramPacket inputPkt = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                    receiveSocket.receive(inputPkt);
                    Log.d("data","received");
                    String posData = new String(inputPkt.getData());
                    String[] carData = posData.split(",");

                    pointStartCar1[0] = Double.parseDouble(carData[0]);
                    pointStartCar1[1] = Double.parseDouble(carData[1]);
                    pointMiddleCar1[0] = Double.parseDouble(carData[2]);
                    pointMiddleCar1[1] = Double.parseDouble(carData[3]);
                    pointEndCar1[0] = Double.parseDouble(carData[4]);
                    pointEndCar1[1] = Double.parseDouble(carData[5]);

                    Log.d("Data2Pos : ", carData[0] + carData[1] + carData[2]+carData[3] + carData[4] + carData[5]);

                    pointStartCar2[0] = Double.parseDouble(carData[6]);
                    pointStartCar2[1] = Double.parseDouble(carData[7]);
                    pointMiddleCar2[0] = Double.parseDouble(carData[8]);
                    pointMiddleCar2[1] = Double.parseDouble(carData[9]);
                    pointEndCar2[0] = Double.parseDouble(carData[10]);
                    pointEndCar2[1] = Double.parseDouble(carData[11]);

                    Log.d("Data3Pos : ", carData[6] + carData[7] + carData[8]+carData[9] + carData[10] + carData[11]);


                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}