package com.developers.nilmahadevvidhyalaya.Splesh;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.developers.nilmahadevvidhyalaya.MainActivity;
import com.developers.nilmahadevvidhyalaya.R;


public class Splesh extends ActionBarActivity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splesh);
        // Track app opens.
        //    ParseAnalytics.trackAppOpened(getIntent());
        /*mp = MediaPlayer.create(getBaseContext(), R.raw.startup);
        mp.start();*/
        new DoInBackGround().execute();
    }

    class DoInBackGround extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @SuppressWarnings("static-access")
        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                StartAnimations();
               // StartAnimation2();
                StartAnimaiton3();
                Thread.sleep(4200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            i = new Intent(getBaseContext(), MainActivity.class);
            finish();
            startActivity(i);
        }
    }

    private void StartAnimations() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.re_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.iv_logo);

        iv.clearAnimation();
        iv.startAnimation(anim);
    }

   /* private void StartAnimation2() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.re_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.iv_khodiyar);
        iv.clearAnimation();
        iv.startAnimation(anim);
    }*/

    private void StartAnimaiton3() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.re_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.iv_vidhya);
        iv.clearAnimation();
        iv.startAnimation(anim);

    }
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
}
