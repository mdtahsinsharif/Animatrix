package com.example.apponometry.lash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.FloatMath;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.view.MotionEvent;

public class MainActivity extends Activity implements SensorEventListener {


    AnimationDrawable rocketAnimation;
    private ImageView platform;
    public Vibrator v;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private MainActivity mShakeDetector;
    private ImageView mImageView;
    public Button button;

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener mListener;
    private long mShakeTimestamp;
    private int mShakeCount;
    //final MediaPlayer mediaPlayer = MediaPlayer.create(ShakeDetector.this, R.raw.cryhorse);

    public void setOnShakeListener(OnShakeListener listener) {
        this.mListener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (mListener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float gForce = FloatMath.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                // ignore shake events too close to each other (500ms)
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }

                // reset the shake count after 3 seconds of no shakes
                if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    mShakeCount = 0;
                }

                mShakeTimestamp = now;
                mShakeCount++;

                mListener.onShake(mShakeCount);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView whipopen = (ImageView) findViewById(R.id.whipopen);
        whipopen.setVisibility(View.GONE);

        final ImageView unwindwriting = (ImageView) findViewById(R.id.unwindwriting);
        final ImageView shakewriting = (ImageView)findViewById(R.id.shakewriting);
        shakewriting.setVisibility(View.GONE);

        //mImageView = (ImageView) findViewById(R.id.standing);
        //mImageView.setImageResource(R.drawable.standing);
        //zoom();
        //blink();

        ImageView myAnimation = (ImageView) findViewById(R.id.walkinghorse);
        AnimationDrawable myAnimationDrawable = (AnimationDrawable) myAnimation.getDrawable();
        myAnimationDrawable.start();

        final ImageView whipclosed = (ImageView) findViewById(R.id.whip);
        whipclosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unwindwriting.setVisibility(View.GONE);
                whipclosed.setVisibility(View.GONE);
                whipopen.setVisibility(View.VISIBLE);
                shakewriting.setVisibility(View.VISIBLE);
            }
        });


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new MainActivity();
        mShakeDetector.setOnShakeListener(new OnShakeListener() {

            @Override
            public void onShake(int event) {
                MediaPlayer mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.whip2222);
                mediaPlayer2.start();

                handleShakeEvent(event);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void handleShakeEvent(int count) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer23 = MediaPlayer.create(MainActivity.this, R.raw.cryhorse);
                mediaPlayer23.start();

                Intent intent = new Intent(MainActivity.this, StartHorseActivity.class);
                startActivity(intent);
            }
        }, 400);
    }
/*
    public void clockwise() {
        ImageView image = (ImageView) findViewById(R.id.shakephone);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanimation);
        image.startAnimation(animation);
    }
    */
}

/*
    public void zoom(){
        ImageView image = (ImageView)findViewById(R.id.standing);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        image.startAnimation(animation1);
    }
    */



