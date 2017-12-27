package com.example.nmaha.resource;



//import android.animation.ValueAnimator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;

import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import android.widget.TextView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.os.AsyncTask;
import android.os.Bundle;

import android.app.Activity;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.nmaha.resource.MainActivity.aflag;

import static com.example.nmaha.resource.MainActivity.pointStartCar1;
import static com.example.nmaha.resource.MainActivity.pointStartCar2;
import static com.example.nmaha.resource.MainActivity.pointStartCar3;
import static com.example.nmaha.resource.MainActivity.prevPointStartCar3;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar1;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar2;
import static com.example.nmaha.resource.MainActivity.pointMiddleCar3;
import static com.example.nmaha.resource.MainActivity.prevPointMiddleCar3;
import static com.example.nmaha.resource.MainActivity.pointEndCar1;
import static com.example.nmaha.resource.MainActivity.pointEndCar2;
import static com.example.nmaha.resource.MainActivity.pointEndCar3;
import static com.example.nmaha.resource.MainActivity.prevPointEndCar3;

import static com.example.nmaha.resource.MainActivity.context;
import static com.example.nmaha.resource.MainActivity.raceMode;
import static com.example.nmaha.resource.MainActivity.cityMode;
import static com.example.nmaha.resource.MainActivity.toast1;
import static com.example.nmaha.resource.MainActivity.toast2;



public class CanvasView extends View{

    static double engineforce = 0.0;
    static double fdrag = 0.0;
    final double cdrag = 0.5;
    final double Crr = 12.8;
    static double frr, ftotal, acceleration;
    int mass = 10;
    static double velocity = 0.0;
    static double time = 0.0;
    int count = 0;

    static boolean toast_flag1 = true;
    static int toast_counter1 = 0;
    static boolean toast_flag2 = true;
    static int toast_counter2 = 0;
    static boolean collision_flag1 = false;
    static boolean collision_flag2 = false;
    static boolean collide_flag1 = false;
    static boolean collide_flag2 = false;



    public Bitmap mBitmap;
    public Canvas mCanvas;
    public Paint mPaint;
    public Paint mPaintPath;
    public Paint mPaint1;
    public Paint mPaintText;
    public float mX, mY;
    //public static final float TOLERANCE = 5;
    public  String TAG = "myDebugger";
    public DisplayMetrics displayMetrics = new DisplayMetrics();
    float radius;float centerX;float centerY;double circumference;
    double[] point1 = new double[2];
    double[] point2 = new double[2];
    double[] point3 = new double[2];
    double[] point4 = new double[2];



//    double[] pointStartCar = new double[2];
//    double[] pointEndCar = new double[2];
//    double[] pointMiddleCar = new double[2];
//
//    double[] prevpointStartCar = new double[2];
//    double[] prevpointEndCar = new double[2];
//    double[] prevpointMiddleCar = new double[2];
//    public static double[] pointStartCar1 = new double[2];
//    public static double[] pointEndCar1 = new double[2];
//    public static double[] pointMiddleCar1 = new double[2];
//
//    public static double[] pointStartCar2 = new double[2];
//    public static double[] pointEndCar2 = new double[2];
//    public static double[] pointMiddleCar2 = new double[2];
//
//    public static double[] pointStartCar3 = new double[2];
//    public static double[] pointEndCar3 = new double[2];
//    public static double[] pointMiddleCar3 = new double[2];

    boolean[] pointLimits = new boolean[4];
    boolean actualRotationValidFlag = true;
    float pathCenterY;
    boolean pointLimitsForCar = false;
    double dtheta = 0; float crossProduct = 0;
    public boolean rotationEventInit = false;
    double step = 0;
    public long duration = 16;
    float translateStep =0;
    double prev_dtheta;
    float prev_crossProduct;
    float positionX, positionY;

    TextView position;
    Paint paint, paintinner;

    static int toastValue = 0;


    public void resetCar(){

/*
        pointMiddleCar1[0] = -displayMetrics.widthPixels/2 + 30; //0
        pointMiddleCar1[1] = 0;  //20;
        pointStartCar1[0] = pointMiddleCar1[0]; //-20
        pointStartCar1[1] = pointMiddleCar1[1] - 10; //20
        pointEndCar1 [0] = pointMiddleCar1[0]; //20
        pointEndCar1 [1] = pointMiddleCar1[1] + 10; //20

        pointMiddleCar2[0] = -displayMetrics.widthPixels/2 + 90; //0
        pointMiddleCar2[1] = 0;  //20;
        pointStartCar2[0] = pointMiddleCar2[0]; //-20
        pointStartCar2[1] = pointMiddleCar2[1] - 10; //20
        pointEndCar2[0] = pointMiddleCar2[0]; //20
        pointEndCar2[1] = pointMiddleCar2[1] + 10; //20
*/

        pointMiddleCar3[0] = -displayMetrics.widthPixels/2 + 150; //0
        pointMiddleCar3[1] = 0;  //20;
        pointStartCar3[0] = pointMiddleCar3[0]; //-20
        pointStartCar3[1] = pointMiddleCar3[1] - 10; //20
        pointEndCar3[0] = pointMiddleCar3[0]; //20
        pointEndCar3[1] = pointMiddleCar3[1] + 10; //20

//        //for(int i=0;i<pointLimitsForCar.length;i++){
//        pointMiddleCar2[0] = -displayMetrics.widthPixels/2 + 90;
//        pointMiddleCar2[1] = 0;
//        pointStartCar2[0] = pointMiddleCar2[0];
//        pointStartCar2[1] = pointMiddleCar2[1] - 10;
//        pointEndCar2[0] = pointMiddleCar2[0];
//        pointEndCar2[1] = pointEndCar1[1] + 10;
//
//        pointMiddleCar3[0] = -displayMetrics.widthPixels/2 + 150;
//        pointMiddleCar3[1] = 0;
//        pointStartCar3[0] = pointMiddleCar3[0];
//        pointStartCar3[1] = pointMiddleCar3[1] - 10;
//        pointEndCar3[0] = pointMiddleCar3[0];
//        pointEndCar3[1] = pointMiddleCar3[1] + 10;
        pointLimitsForCar = false;

        step =0;

    };

    public void holdCar(){

/*
        if ( (pointMiddleCar3[0] >= -520) && (pointMiddleCar3[0] <= 520) && (pointMiddleCar3[0] <= -340) && (pointMiddleCar3[0] >= 340)
                && (pointMiddleCar3[1] <= 675) && (pointMiddleCar3[1] >= -420) && (pointMiddleCar3[1] >= 495) && (pointMiddleCar3[1] <= -240)) {
*/

        if( (pointMiddleCar3[0] < -520) ){
            pointStartCar3[0] = -510;
            pointMiddleCar3[0] = -510;
            pointEndCar3[0] = -510;
            pointStartCar3[1] = prevPointMiddleCar3[1] - 10;
            pointMiddleCar3[1] = prevPointMiddleCar3[1] ;
            pointEndCar3[1] = prevPointMiddleCar3[1] + 10;
        }
/*
        else if( (pointMiddleCar3[0] > -340) ){
            pointStartCar3[0] = -350;
            pointMiddleCar3[0] = -350;
            pointEndCar3[0] = -350;
            pointStartCar3[1] = prevPointMiddleCar3[1] - 10;
            pointMiddleCar3[1] = prevPointMiddleCar3[1] ;
            pointEndCar3[1] = prevPointMiddleCar3[1] + 10;
        }
*/
        else if( (pointMiddleCar3[0] > 520) ){
            pointStartCar3[0] = 510;
            pointMiddleCar3[0] = 510;
            pointEndCar3[0] = 510;
            pointStartCar3[1] = prevPointMiddleCar3[1] + 10;
            pointMiddleCar3[1] = prevPointMiddleCar3[1] ;
            pointEndCar3[1] = prevPointMiddleCar3[1] - 10;
        }
/*
        else if( (pointMiddleCar3[0] < 340) ){
            pointStartCar3[0] = 350;
            pointMiddleCar3[0] = 350;
            pointEndCar3[0] = 350;
            pointStartCar3[1] = prevPointMiddleCar3[1] + 10;
            pointMiddleCar3[1] = prevPointMiddleCar3[1] ;
            pointEndCar3[1] = prevPointMiddleCar3[1] - 10;
        }
*/
        else if( (pointMiddleCar3[1] > 675) ){
            pointStartCar3[1] = 665;
            pointMiddleCar3[1] = 665 ;
            pointEndCar3[1] = 665;
            pointStartCar3[0] = prevPointMiddleCar3[0] - 10;
            pointMiddleCar3[0] = prevPointMiddleCar3[0];
            pointEndCar3[0] = prevPointMiddleCar3[0] + 10;
        }
/*
        else if( (pointMiddleCar3[1] < 495) ){
            pointStartCar3[1] = 505;
            pointMiddleCar3[1] = 505 ;
            pointEndCar3[1] = 505;
            pointStartCar3[0] = prevPointMiddleCar3[0] - 10;
            pointMiddleCar3[0] = prevPointMiddleCar3[0];
            pointEndCar3[0] = prevPointMiddleCar3[0] + 10;
        }
*/
        else if( (pointMiddleCar3[1] < -420) ){
            pointStartCar3[1] = -410;
            pointMiddleCar3[1] = -410;
            pointEndCar3[1] = -410;
            pointStartCar3[0] = prevPointMiddleCar3[0] + 10;
            pointMiddleCar3[0] = prevPointMiddleCar3[0];
            pointEndCar3[0] = prevPointMiddleCar3[0] - 10;
        }
/*
        else if( (pointMiddleCar3[1] > -240) ){
            pointStartCar3[1] = -250;
            pointMiddleCar3[1] = -250;
            pointEndCar3[1] = -250;
            pointStartCar3[0] = prevPointMiddleCar3[0] + 10;
            pointMiddleCar3[0] = prevPointMiddleCar3[0];
            pointEndCar3[0] = prevPointMiddleCar3[0] - 10;
        }
*/

        pointLimitsForCar = false;
        step =0;

        for(int i = 0; i < 1000; i++) {

        }

    };

    @SuppressLint("NewApi")

    public void  resetPoints(){
        point1[0] = 0;
        point1[1] = radius;
        point2[0] = 0;
        point2[1] = -radius;
        point3[0] = radius;
        point3[1] = 0;
        point4[0] = -radius;
        point4[1] = 0;
        for(int i=0;i<pointLimits.length;i++){
            pointLimits[i] = false;
        }
        actualRotationValidFlag = true;
        rotationEventInit = false;
        dtheta =0; crossProduct =0;

    };


    int counter =0;

    public boolean verifierFunc(double dtheta,float crossProduct,double prev_dtheta,float prev_crossProduct){

        if(dtheta == prev_dtheta && crossProduct == prev_crossProduct){
            return false;
        }else{
            return true;
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void invalidateFunc (){

/*
        if (!collision_flag1 || !collision_flag2) {
            translatedFlag = true;
            translateCar ();
        } else {
            translatedFlag = false;
            translateCar ();
        }
*/

        translateCar ();



/*
        position = (TextView) findViewById (R.id.xyPosition);
        position.setText (String.valueOf (pointMiddleCar[0]+", "+pointMiddleCar[1]));
*/

        if(rotationEventInit && actualRotationValidFlag && verifierFunc(dtheta,crossProduct,prev_dtheta,prev_crossProduct) ){
            turnCar(dtheta,crossProduct);
            prev_crossProduct = crossProduct;
            prev_dtheta = dtheta;

        }

        invalidate();

        new CountDownTimer(duration, duration) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                invalidateFunc();
            }
        }.start();

    };




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        //get screen size
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);

        Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);
//        Log.d("Width", ""+displayMetrics.widthPixels);
//        Log.d("Height", ""+displayMetrics.heightPixels);


        centerX= displayMetrics.widthPixels/2;
        int adjustment = (230*displayMetrics.heightPixels)/1776;
        centerY = (3 *displayMetrics.heightPixels/4)-adjustment;
        //        radius = (displayMetrics.widthPixels* 200)/1080; //(displayMetrics.heightPixels/4)
        radius = 100;

        circumference = 2*Math.PI*radius;
        pathCenterY = displayMetrics.heightPixels/4;

//        position = findViewById (R.id.xyPosition);

        resetPoints();
        resetCar();


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(1f);
        //for circle points
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.RED);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeJoin(Paint.Join.ROUND);
        mPaint1.setStrokeWidth(4f);

        //text
        mPaintPath = new Paint();
        mPaintPath.setAntiAlias(true);
        mPaintPath.setColor(Color.BLACK);
        mPaintPath.setStyle(Paint.Style.STROKE);
        mPaintPath.setStrokeJoin(Paint.Join.ROUND);
        mPaintPath.setStrokeWidth(1f);

        //print-text
        mPaintText = new Paint();
        mPaintText.setTextSize (45);
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setStrokeJoin(Paint.Join.BEVEL);
        mPaintText.setStrokeWidth(1f);

        invalidateFunc();
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("onDraw","Ondraw"+counter);
        drawWheel(canvas);
        drawPath(canvas);
    }

    //@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint({"WrongCall", "NewApi"})



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void accelerateCar(){


        if(step < 2.5){
            step = step + 0.025;
        }else{
            step = 2.5;
        }

    };


    public void deaccelerateCar(){

//        Log.d("Deaccelaration","started"+step);
        if(step > 0){
            step = step -0.025;
        }

    };



    public void give(double[] start,double[] middle,double[] end){
        pointStartCar2[0] = start[0];

        pointStartCar2[1] = start[1];
        pointMiddleCar2[0] = middle[0];
        pointMiddleCar2[1] = middle[1];
        pointEndCar2[0] = end[0];
        pointEndCar2[1] = end[1];
        Log.d("initial",String.valueOf(pointStartCar1[0]));
        Log.d("initial",String.valueOf(pointEndCar1[0]));
        Log.d("after", String.valueOf(pointStartCar2[0]));
        Log.d("after", String.valueOf(pointEndCar2[0]));


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void translateCar() {

//        Log.d ("Race Mode value",String.valueOf(raceMode));
        Log.d ("Race Mode value",String.valueOf(raceMode));


//        sqlupdate ();
        count++;
        if(count == 75) {
            MyClientTask myClientTask = new MyClientTask();

            myClientTask.execute();

            count -= 75;
        }


//        Log.d("coord",String.valueOf(pointMiddleCar[1]));


        boolean leftRight = true;


//        boolean topDown = true;

/*
        if ( (pointMiddleCar3[0] >= -520) && (pointMiddleCar3[0] <= 520) && (pointMiddleCar3[1] <= 675) && (pointMiddleCar3[1] >= -420)) {
            leftRight = true;
        } else {
            leftRight = false;
        }
*/
        boolean translatedFlag = false;

        if(raceMode) {

            if (Math.abs (pointMiddleCar3[0] - pointMiddleCar2[0]) <= 30 && Math.abs (pointMiddleCar3[1] - pointMiddleCar2[1]) <= 30) {
                if (toast_flag2) {
                    toast2 = Toast.makeText (context, "COLLIDED 2", Toast.LENGTH_SHORT);
                    toast2.show ();
                    toast_flag2 = false;
                    //translatedFlag = true;
                    collide_flag2 = true;
                } else {
                    toast_counter2++;
                }
            }
            if (toast_counter2 == 30) {
                toast_flag2 = true;
                toast_counter2 = 0;
                //translatedFlag = false;
                collide_flag2 = false;
            }

            if (Math.abs (pointMiddleCar3[0] - pointMiddleCar1[0]) <= 30 && Math.abs (pointMiddleCar3[1] - pointMiddleCar1[1]) <= 30) {
                if (toast_flag1) {
                    toast1 = Toast.makeText (context, "COLLIDED 1", Toast.LENGTH_SHORT);
                    toast1.show ();
                    toast_flag1 = false;
                    //translatedFlag = true;
                    collide_flag1 = true;
                } else {
                    toast_counter1++;
                }
            }
            if (toast_counter1 == 30) {
                toast_flag1 = true;
                toast_counter1 = 0;
                //translatedFlag = false;
                collide_flag1 = false;
            }

        } else {

            if (Math.abs (pointMiddleCar3[0] - pointMiddleCar2[0]) < 50 && Math.abs (pointMiddleCar3[1] - pointMiddleCar2[1]) < 50) {
                if (toast_flag2) {
                    toast2 = Toast.makeText (context, "CAUTION COLLISION 2", Toast.LENGTH_SHORT);
                    toast2.show ();
                    toast_flag2 = false;
                    //translatedFlag = true;
                    collision_flag2 = true;
                } else {
                    toast_counter2++;
                }
            }
            if (toast_counter2 == 30) {
                toast_flag2 = true;
                toast_counter2 = 0;
                //translatedFlag = false;
                collision_flag2 = false;
            }

            if (Math.abs (pointMiddleCar3[0] - pointMiddleCar1[0]) < 50 && Math.abs (pointMiddleCar3[1] - pointMiddleCar1[1]) < 50) {
                if (toast_flag1) {
                    toast1 = Toast.makeText (context, "CAUTION COLLISION 1", Toast.LENGTH_SHORT);
                    toast1.show ();
                    toast_flag1 = false;
                    //translatedFlag = true;
                    collision_flag1 = true;
                } else {
                    toast_counter1++;
                }
            }
            if (toast_counter1 == 30) {
                toast_flag1 = true;
                toast_counter1 = 0;
                //translatedFlag = false;
                collision_flag1 = false;
            }

        }

        if ( (pointMiddleCar3[0] >= -520) && (pointMiddleCar3[0] <= 520)
//                && (pointMiddleCar3[0] <= -340) && (pointMiddleCar3[0] >= 340)
                && (pointMiddleCar3[1] <= 675) && (pointMiddleCar3[1] >= -420)
//                && (pointMiddleCar3[1] >= 495) && (pointMiddleCar3[1] <= -240)
                ) {
            leftRight = true;
        } else {
            leftRight = false;
        }
/*
        if( Math.abs (pointMiddleCar3[0] - pointMiddleCar2[0]) <= 2 || Math.abs (pointMiddleCar3[1] - pointMiddleCar2[1]) <= 2
                || Math.abs (pointMiddleCar3[0] - pointMiddleCar1[0]) <= 2 || Math.abs (pointMiddleCar3[1] - pointMiddleCar1[1]) <= 2 ) {
            Toast.makeText (context, "CAUTION COLLISON", Toast.LENGTH_LONG).show ();
        }
*/

        if(leftRight) {
//            Log.d("leftRight flag", "Flag: "+String.valueOf (leftRight));

            if(collide_flag1 || collide_flag2) {
                collide_flag1 = false;
                collide_flag2 = false;
                resetCar ();
//                translatedFlag = true;
            }
            else translatedFlag = false;

            if(collision_flag2 || collision_flag1) {
                translatedFlag = true;
            }
            else translatedFlag = false;

            if (step > 0) {
                step = step - 0.0099;
                // Log.d("TranslateCar",""+step);
            }

            if (aflag == 0) {
                engineforce = 0;
//                Log.d("flag",String.valueOf(MainActivity.aflag));
                //time = 1;
            }

            else if (aflag == 1) {
                engineforce = step*200;
//                Log.d("flag",String.valueOf(MainActivity.aflag));
//                Toast.makeText (context, "STOP", Toast.LENGTH_SHORT).show ();

            }

            else if(aflag == 2)
                if(velocity > 0)
                    engineforce = - (step * 40);
                else engineforce = 0;
            else
                engineforce = 0;

            fdrag = cdrag * velocity;
            frr = Crr * velocity;
            ftotal = engineforce - frr - fdrag;
            invalidate();

            acceleration = ftotal / mass;
            invalidate();
//            Log.d("acceleration", String.valueOf(acceleration));

//        time = time + .0166;
//        Log.d("time", String.valueOf(time));

            invalidate();
            //velocity = (step*10) / 0.0166;

            velocity = velocity + (acceleration * 0.166);
//            Log.d("velocity", String.valueOf(velocity));


            if (step > 0) {

                translateStep = (float)step;
                if (pointStartCar3[0] == pointEndCar3[0]) {
                    //move along Y
                    if (pointStartCar3[1] < pointEndCar3[1]) {
                        //move up
//                        Log.d (TAG, "move up on Y");
                        pointStartCar3[1] = pointStartCar3[1] - (translateStep);
                        pointMiddleCar3[1] = pointMiddleCar3[1] - (translateStep);
                        pointEndCar3[1] = pointEndCar3[1] - (translateStep);

                        //New 12/03/2017
                        prevPointStartCar3[1] = pointStartCar3[1];
                        prevPointMiddleCar3[1] = pointMiddleCar3[1];
                        prevPointEndCar3[1] = pointEndCar3[1];

                    } else {
                        //move down
                        //Log.d(TAG, "move down on Y");
                        pointStartCar3[1] = pointStartCar3[1] + (translateStep);
                        pointMiddleCar3[1] = pointMiddleCar3[1] + (translateStep);
                        pointEndCar3[1] = pointEndCar3[1] + (translateStep);

                        //New 12/03/2017
                        prevPointStartCar3[1] = pointStartCar3[1];
                        prevPointMiddleCar3[1] = pointMiddleCar3[1];
                        prevPointEndCar3[1] = pointEndCar3[1];
                    }
                    //                position.setText (String.valueOf ("X: "+pointMiddleCar[0]));
                    translatedFlag = true;
                }

                if (pointStartCar3[1] == pointEndCar3[1]) {
                    //move along Y
                    //Log.d("comparison",""+pointStartCar[1]+" "+pointEndCar[1]);
                    if (pointStartCar3[0] < pointEndCar3[0]) {
                        //move left
                        //Log.d(TAG, "move left on X");
                        pointStartCar3[0] = pointStartCar3[0] - translateStep;
                        pointMiddleCar3[0] = pointMiddleCar3[0] - translateStep;
                        pointEndCar3[0] = pointEndCar3[0] - translateStep;

                        //New 12/03/2017
                        prevPointStartCar3[0] = pointStartCar3[0];
                        prevPointMiddleCar3[0] = pointMiddleCar3[0];
                        prevPointEndCar3[0] = pointEndCar3[0];

                    } else {
                        //move right
                        //Log.d(TAG, "move right on X");
                        pointStartCar3[0] = pointStartCar3[0] + translateStep;
                        pointMiddleCar3[0] = pointMiddleCar3[0] + translateStep;
                        pointEndCar3[0] = pointEndCar3[0] + translateStep;

                        //New 12/03/2017
                        prevPointStartCar3[0] = pointStartCar3[0];
                        prevPointMiddleCar3[0] = pointMiddleCar3[0];
                        prevPointEndCar3[0] = pointEndCar3[0];
                    }
                    //                position.setText (String.valueOf ("X: "+pointMiddleCar[0]));
                    translatedFlag = true;

                }
                if (!translatedFlag) {

                    double slope = slopeCalculator (pointEndCar3[0], pointEndCar3[1], pointStartCar3[0], pointStartCar3[1]);
                    double stepForLine = 0;
                    stepForLine = translateStep;
                    if (pointStartCar3[0] < pointEndCar3[0]) {
                        stepForLine = stepForLine * -1;
                        //                    position.setText (String.valueOf ("X: "+pointMiddleCar[0]));
                    }
                    //Log.d("stepForLine ", "" + stepForLine + " slope =" + slope);

                    pointStartCar3[1] = slope * (pointStartCar3[0] + stepForLine) - slope * pointEndCar3[0] + pointEndCar3[1];
                    pointStartCar3[0] = pointStartCar3[0] + stepForLine;

                    pointMiddleCar3[1] = slope * (pointMiddleCar3[0] + stepForLine) - slope * pointEndCar3[0] + pointEndCar3[1];
                    pointMiddleCar3[0] = pointMiddleCar3[0] + stepForLine;

                    pointEndCar3[1] = slope * (pointEndCar3[0] + stepForLine) - slope * pointEndCar3[0] + pointEndCar3[1];
                    pointEndCar3[0] = pointEndCar3[0] + stepForLine;

                    //New 12/03/2017
                    prevPointStartCar3[0] = pointStartCar3[0];
                    prevPointMiddleCar3[0] = pointMiddleCar3[0];
                    prevPointEndCar3[0] = pointEndCar3[0];
                    prevPointStartCar3[1] = pointStartCar3[1];
                    prevPointMiddleCar3[1] = pointMiddleCar3[1];
                    prevPointEndCar3[1] = pointEndCar3[1];

                    translatedFlag = true;
                }
            }


        } else {

            holdCar ();

        }
//        position.setText (String.valueOf ("Hello"));
    };

    @SuppressLint("NewApi")
    public double slopeCalculator(double x1,double y1,double x2,double y2){
        double slope = 0;
        x1 = x1 + centerX;
        y1 = y1 + pathCenterY;
        x2 = x2 + centerX;
        y2 = y2 + pathCenterY;
        slope = (y2-y1)/(x2-x1);

        return slope;
    };

    public void turnCar (double dtheta, float direction){

        double[] rotationCoordstoBePassed = new double[2];
        //dtheta = dtheta;
//        Log.d("theta",""+dtheta);
        double[] rotatedCoords;

        rotationCoordstoBePassed[0] = pointStartCar3[0] - pointMiddleCar3[0];
        rotationCoordstoBePassed[1] = pointStartCar3[1] - pointMiddleCar3[1];
        rotatedCoords= rotationCoords(rotationCoordstoBePassed,dtheta,direction);

        pointStartCar3[0] =  pointMiddleCar3[0] + rotatedCoords[0];
        pointStartCar3[1]=  pointMiddleCar3[1] + rotatedCoords[1];



        rotationCoordstoBePassed[0] = pointEndCar3[0] - pointMiddleCar3[0];
        rotationCoordstoBePassed[1] = pointEndCar3[1] - pointMiddleCar3[1];
        rotatedCoords = rotationCoords(rotationCoordstoBePassed,dtheta,direction);
        pointEndCar3[0] =  pointMiddleCar3[0]+rotatedCoords[0];
        pointEndCar3[1]=   pointMiddleCar3[1]+rotatedCoords[1];

    };


    public void  drawPath(Canvas canvas){

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(60);
        paint.setStyle(Paint.Style.STROKE);

        paintinner = new Paint();
        paintinner.setColor(Color.YELLOW);
        paintinner.setStrokeWidth(60);
        paintinner.setStyle(Paint.Style.STROKE);

        mPaintPath.setStrokeWidth(5f);

        canvas.drawCircle(centerX, pathCenterY,2 , mPaintPath);

        float left = -displayMetrics.widthPixels/2 + 10 ;
        float top = -displayMetrics.heightPixels/4 + 10;
        float right = displayMetrics.widthPixels/2 -10 ;
        float bottom = displayMetrics.heightPixels/4 -10;

        mPaintPath.setStrokeWidth(1f);

        //        canvas.drawRect(left+centerX,top+pathCenterY, centerX,bottom+pathCenterY,mPaintPath);
        canvas.drawRect(40, 40, 1040, 1100, paint);
        canvas.drawRect(100, 100, 980, 1040, paintinner);
        canvas.drawRect(160, 160, 920,  980, paint);
        //        canvas.drawRect(left,top,right,bottom,mPaintPath);

        //middle
        mPaintPath.setStrokeWidth(1f);
        mPaintPath.setColor(Color.RED);
        canvas.drawCircle(centerX+(float) pointMiddleCar1[0],pathCenterY+(float) pointMiddleCar1[1],5,mPaintPath);

        mPaintPath.setStrokeWidth(1f);
        mPaintPath.setColor(Color.RED);
        canvas.drawCircle(centerX+(float) pointMiddleCar2[0],pathCenterY+(float) pointMiddleCar2[1],5,mPaintPath);

        mPaintPath.setStrokeWidth(1f);
        mPaintPath.setColor(Color.RED);
        canvas.drawCircle(centerX+(float) pointMiddleCar3[0],pathCenterY+(float) pointMiddleCar3[1],5,mPaintPath);

        //start
        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLACK);
        canvas.drawCircle(centerX+(float) pointStartCar1[0],pathCenterY+(float) pointStartCar1[1],10,mPaintPath);

        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLACK);
        canvas.drawCircle(centerX+(float) pointStartCar2[0],pathCenterY+(float) pointStartCar2[1],10,mPaintPath);

        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLACK);
        canvas.drawCircle(centerX+(float) pointStartCar3[0],pathCenterY+(float) pointStartCar3[1],10,mPaintPath);



        //end
        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLUE);
        canvas.drawCircle(centerX+(float) pointEndCar1[0],pathCenterY+ (float)pointEndCar1[1],10,mPaintPath);

        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLUE);
        canvas.drawCircle(centerX+(float) pointEndCar2[0],pathCenterY+ (float)pointEndCar2[1],10,mPaintPath);

        mPaintPath.setStrokeWidth(5f);
        mPaintPath.setColor(Color.BLUE);
        canvas.drawCircle(centerX+(float) pointEndCar3[0],pathCenterY+ (float)pointEndCar3[1],10,mPaintPath);

    };

    public void drawWheel(Canvas canvas){
        //draw center

        //        canvas.drawCircle(centerX,centerY,radius , mPaint);
        canvas.drawCircle(centerX, 3*displayMetrics.heightPixels/4-60, radius, mPaint);
        //name circle as center
//        canvas.drawText("center",(displayMetrics.widthPixels/2)+10,(displayMetrics.heightPixels/4)+10,mPaint);
        //point1

//        canvas.drawText(String.format ("Ignition: %s",));
//        canvas.drawText (String.format( "     --  CAR GAME  --    "),600,50,mPaintText);
//        canvas.drawText(String.format( "Position-X: %.2f m.", pointMiddleCar[0]),600,105,mPaintText);
//        canvas.drawText(String.format( "Position-Y: %.2f m.", pointMiddleCar[1]),600,160,mPaintText);
//        canvas.drawText(String.format( "Velocity: %.2f m/s", Math.abs(velocity)),600,215,mPaintText);
//        canvas.drawText(String.format( "Acceleration: %.2f m/s2", acceleration),600,270,mPaintText);
//        canvas.drawText(String.format( "Steering Angle: %.2f degrees", dtheta*5000),600,325,mPaintText);
//        canvas.drawText(String.format( "Engine RPM: %.0f rev/s.", Math.abs (velocity/0.1)),600,380,mPaintText);

        mPaint1.setColor(Color.RED);
        canvas.drawCircle((float) point1[0]+centerX,(float) point1[1]+(3*displayMetrics.heightPixels/4) - 60, 10, mPaint1);
        //point2
        mPaint1.setColor(Color.BLUE);
        canvas.drawCircle((float) point2[0]+centerX,(float) point2[1]+(3*displayMetrics.heightPixels/4) - 60, 10, mPaint1);
        //point3
        mPaint1.setColor(Color.GRAY);
        canvas.drawCircle((float) point3[0]+centerX,(float) point3[1]+(3*displayMetrics.heightPixels/4) - 60, 10, mPaint1);
        //point4
        mPaint1.setColor(Color.GREEN);
        canvas.drawCircle((float) point4[0]+centerX,(float) point4[1]+(3*displayMetrics.heightPixels/4) - 60 ,10, mPaint1);

    };


    //cross Product
    public  float crossProduct(float[]radius,float[]tranlation){
        float res;

        res = radius[0]*tranlation[1] - radius[1]*tranlation[0]; // (a1b2−a2b1)
        return res;
    };

    //roatation
    public double[] rotationCoords(double[] coord,double dtheata,float direction){

        double[]res = new double[2];

        if(direction > 0) {

            res[0] = coord[0] * Math.cos(dtheata) - coord[1] * Math.sin(dtheata);
            res[1] = coord[1] * Math.cos(dtheata) + coord[0] * Math.sin(dtheata);
        }
        if(direction < 0){

            res[0] = coord[0]*Math.cos(dtheata) + coord[1]*Math.sin(dtheata);
            res[1] = coord[1]*Math.cos(dtheata) - coord[0]*Math.sin(dtheata);

        }

        return  res;
    };

    public void limitCheckerForCar (){
        if(Math.abs(pointStartCar3[0]) > (displayMetrics.widthPixels/2)-5 || Math.abs(pointStartCar3[1]) > (displayMetrics.heightPixels/4)-5){
            pointLimitsForCar =true;

        }
        if(Math.abs(pointEndCar3[0]) > (displayMetrics.widthPixels/2)-5 || Math.abs(pointEndCar3[1]) > (displayMetrics.heightPixels/4)-5){
            pointLimitsForCar =true;

        }
        if(Math.abs(pointMiddleCar3[0]) > (displayMetrics.widthPixels/2)-5 || Math.abs(pointMiddleCar3[1]) > (displayMetrics.heightPixels/4)-5){
            pointLimitsForCar =true;

        }

    };

    public void limitCheckerForPoints (){
        //Log.d("Radius",""+radius);
        //Log.d("	Point1",""+point1[1]);

        double exactHeight = point1[1]+centerY;
        double exactLokHeight =  centerY;
        if(exactHeight <= exactLokHeight  ){

            //Log.d("limit breaker","point1 out of bounds");
            pointLimits[0] = true;
            actualRotationValidFlag = false;
        }
        exactHeight = point2[1]+centerY;
        if(exactHeight >= exactLokHeight  ){

            //Log.d("limit breaker","point2 out of bounds");
            pointLimits[1] = true;
            actualRotationValidFlag = false;
        }
        exactHeight = point3[0]+centerX;
        exactLokHeight =  centerX;
        if(exactHeight <= exactLokHeight  ){

            //Log.d("limit breaker","point3 out of bounds");
            pointLimits[2] = true;
            actualRotationValidFlag = false;
        }

        exactHeight = point4[0]+centerX;
        exactLokHeight =  centerX;
        if(exactHeight >= exactLokHeight  ){

            //Log.d("limit breaker","point4 out of bounds");
            pointLimits[3] = true;
            actualRotationValidFlag = false;
        }
    };

    //transform range //maynot be used
    public double transfromRange (double a, double b ,double c,double d, double x){
        double y =0;
        y = ( (x-a)*(d-c)/(b-a) ) + c;
        return  y;
    };

    // when ACTION_DOWN start touch according to the x,y values
    public void startTouch(float x, float y) {


        mX = x;//previous coordinates
        mY = y;

    }

    // when ACTION_MOVE move touch according to the x,y values
    public void moveTouch(float x, float y) {

        double translationX = x - mX;
        double translationY = y - mY;
        rotationEventInit = true;

        double translationDistance = Math.sqrt(translationX*translationX + translationY*translationY);

        dtheta = translationDistance*360/circumference;

        dtheta = dtheta/300; //adjusting factor

        //direction of rotation
        float radiusVecX = (float) (-centerX + mX);
        float radiusVecY = (float) (-centerY + mY);
        float [] radiusVec = new float[2];
        radiusVec[0] = radiusVecX;
        radiusVec[1] = radiusVecY;
        //radiusVec[2] = 0;

        float[] translationVec = new float[2];
        translationVec[0] = (float) translationX;
        translationVec[1] = (float) translationY;

        int direction = 0;

        crossProduct = crossProduct(radiusVec,translationVec);
        if(crossProduct < 0){
            //Log.d(TAG,"turn left");
            //"anticlockwise" rotation thus turn left
            direction = 1;

        }
        if(crossProduct > 0){
            //Log.d(TAG,"turn right");
            //"clockwise" rotation thus turn right
            direction =-1;
            //turnCar(dtheta,crossProduct);
        }



        //roatatepoints just save the rotated points ,canvas onDraw method will automatically draw them
        limitCheckerForPoints();
        if(!pointLimits[0]){
            point1 = rotationCoords(point1,dtheta,crossProduct);
        }

        if(!pointLimits[1]){
            point2 = rotationCoords(point2,dtheta,crossProduct);
        }

        if(!pointLimits[2]){
            point3 = rotationCoords(point3,dtheta,crossProduct);
        }

        if(!pointLimits[3]){
            point4 = rotationCoords(point4,dtheta,crossProduct);
        }

        //updateoldcoordinates
        mX =x;
        mY =y;


    }

    public void clearCanvas() {
        //mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    public void upTouch() {
        //rotationEventInit = false;
        // SystemClock.sleep(100); //arbitary sleep value to give animation effect
        resetPoints();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                //invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                if(mX != x && mY != y){
                    moveTouch(x, y);

                }else{
                    rotationEventInit = false;
                }


                break;
            case MotionEvent.ACTION_UP:
                //rotationEventInit = false;
                upTouch();
                //invalidate();
                break;
        }

        return true;
    }
}