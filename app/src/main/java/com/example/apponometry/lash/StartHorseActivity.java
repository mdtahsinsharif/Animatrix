package com.example.apponometry.lash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class StartHorseActivity extends MainActivity {


    private RelativeLayout mealLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_horse);

        //ImageView startagain = (ImageView)findViewById(R.id.startagain);
        //startagain.setVisibility(View.GONE);

        StartActivity();

        mealLayout = (RelativeLayout) findViewById(R.id.layout);

        Timer timer = new Timer();

        MyTimer mt = new MyTimer();

        timer.schedule(mt, 1000, 1000);
    }

    class MyTimer extends TimerTask {

        public void run() {

            //ImageView myAnimation = (ImageView) findViewById(R.id.background);
            //final AnimationDrawable myAnimationDrawable = (AnimationDrawable) myAnimation.getDrawable();
            //This runs in a background thread.
            //We cannot call the UI from this thread, so we must call the main UI thread and pass a runnable
            runOnUiThread(new Runnable() {

                public void run() {
                    Random rand = new Random();
                    //The random generator creates values between [0,256) for use as RGB values used below to create a random color
                    //We call the RelativeLayout object and we change the color.  The first parameter in argb() is the alpha.
                    //mealLayout.setBackgroun;
                    //mealLayout.setAnimation(myAnimationDrawable);

                    mealLayout.setBackgroundColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                }
            });
        }
    }

    public void StartActivity() {
        final Vibrator v;

        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        //final RelativeLayout tapwriter = (RelativeLayout)findViewById(R.id.tapwriter);
        //final RelativeLayout horseactivity = (RelativeLayout)findViewById(R.id.horselayout);
        final ImageView keeptapping = (ImageView)findViewById(R.id.keeptapping);
        keeptapping.setVisibility(View.GONE);
        final ImageView andagain = (ImageView)findViewById(R.id.andagain);
        andagain.setVisibility(View.GONE);
        final ImageView tapagain = (ImageView)findViewById(R.id.tapagain);
        tapagain.setVisibility(View.GONE);
        final ImageView startagain1 = (ImageView)findViewById(R.id.startagain);
        startagain1.setVisibility(View.GONE);

        final ImageView tapanimal = (ImageView)findViewById(R.id.tapanimal);
        final long[] pattern = {0, 50, 100, 50, 700};
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.horselayout);
        final ImageView dog = (ImageView) findViewById(R.id.dog);
        dog.setVisibility(View.GONE);
        final ImageView flyingbird = (ImageView) findViewById(R.id.flyingbird);
        flyingbird.setVisibility(View.GONE);
        final ImageView myAnimation = (ImageView) findViewById(R.id.runninghorse);
        final AnimationDrawable myAnimationDrawable = (AnimationDrawable) myAnimation.getDrawable();
/*
            ImageView myAnimation1 = (ImageView) findViewById(R.id.stick);
            final AnimationDrawable myAnimationDrawable1 = (AnimationDrawable) myAnimation1.getDrawable();
*/
        myAnimation.post(
                new Runnable() {

                    @Override
                    public void run() {
                        final MediaPlayer mediaPlayer = MediaPlayer.create(StartHorseActivity.this, R.raw.gallops1);
                        mediaPlayer.start();

                        v.vibrate(pattern, 0);
                        myAnimationDrawable.start();
                        //myAnimationDrawable1.start();
                        //move();
                        layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MediaPlayer mediaPlayer256 = MediaPlayer.create(StartHorseActivity.this, R.raw.horsesnort4);
                                mediaPlayer256.start();
/*

*/
                                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                myAnimation.startAnimation(animation1);
                                move();
                                tapanimal.setVisibility(View.GONE);
                                tapagain.setVisibility(View.VISIBLE);
                                fade();



                                layout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mediaPlayer.stop();
                                        mediaPlayer.release();

                                        final MediaPlayer mediaPlayer256 = MediaPlayer.create(StartHorseActivity.this, R.raw.leopardgrowl);
                                        mediaPlayer256.start();

                                        //String msg = " " + myAnimation.getPaddingLeft();
                                        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                        myAnimation.clearAnimation();
                                        myAnimation.setVisibility(View.GONE);
                                        dog.setVisibility(View.VISIBLE);
                                        tapagain.setVisibility(View.GONE);
                                        andagain.setVisibility(View.VISIBLE);
                                        fade1();
                                        final AnimationDrawable myAnimationDrawable2 = (AnimationDrawable) dog.getDrawable();
                                        dog.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                myAnimationDrawable2.start();
                                                move2();
                                                layout.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        mediaPlayer256.stop();
                                                        mediaPlayer256.release();
                                                        andagain.setVisibility(View.GONE);
                                                        keeptapping.setVisibility(View.VISIBLE);
                                                        String msg = " " + dog.getPaddingLeft();
                                                        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                                                                dog.clearAnimation();
                                                                dog.setVisibility(View.GONE);
                                                                flyingbird.setVisibility(View.VISIBLE);
                                                                final MediaPlayer mediaPlayerbird = MediaPlayer.create(StartHorseActivity.this, R.raw.pigeonflight);
                                                                mediaPlayerbird.start();
                                                        //

                                                          //
                                                                final AnimationDrawable myAnimationDrawable3 = (AnimationDrawable) flyingbird.getDrawable();
                                                                flyingbird.post(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        myAnimationDrawable3.start();

                                                                        final ImageView myAnimation8 = (ImageView) findViewById(R.id.flyingbird);
                                                                        Animation animation14 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                                                        myAnimation8.startAnimation(animation14);
                                                                        //move1();
                                                                        layout.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                mediaPlayerbird.stop();
                                                                                mediaPlayerbird.release();
                                                                                myAnimation8.clearAnimation();
                                                                                keeptapping.setVisibility(View.GONE);
                                                                                final MediaPlayer mediaPlayercrowcaw = MediaPlayer.create(StartHorseActivity.this, R.raw.animalgrowls);
                                                                                mediaPlayercrowcaw.start();
                                                                                flyingbird.setVisibility(View.GONE);
                                                                                //horseactivity.setVisibility(View.GONE);
                                                                                //tapwriter.setVisibility(View.GONE);

                                                                                //keeptapping.setVisibility(View.VISIBLE);
                                                                                startagain1.setVisibility(View.VISIBLE);
                                                                                startagain1.setOnClickListener(new View.OnClickListener() {
                                                                                    @Override
                                                                                    public void onClick(View v) {
                                                                                        mediaPlayercrowcaw.stop();
                                                                                        mediaPlayercrowcaw.release();

                                                                                        Intent intent = new Intent(StartHorseActivity.this, MainActivity.class);
                                                                                        startActivity(intent);
                                                                                    }
                                                                                });


                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }


    public void move(){
        ImageView image = (ImageView)findViewById(R.id.runninghorse);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void move1(){
        ImageView image = (ImageView)findViewById(R.id.flyingbird);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveup);
        image.startAnimation(animation1);
    }

    public void move2(){
        ImageView image = (ImageView)findViewById(R.id.dog);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void fade(){
        ImageView image = (ImageView)findViewById(R.id.tapagain);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        image.startAnimation(animation1);
    }

    public void fade1(){
        ImageView image = (ImageView)findViewById(R.id.andagain);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        image.startAnimation(animation1);
    }

}

