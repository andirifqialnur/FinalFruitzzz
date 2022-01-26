package com.example.fruitzzz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    ImageView img;
    TextView Tv;

    private static int SPLASH_SCREEN = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.imgSplash);
        Tv = findViewById(R.id.idTV);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splasssh);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.bounce_splasssh);
        img.startAnimation(anim);
        Tv.startAnimation(anim2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent aftersplash = new Intent(SplashActivity.this,OnBoardingActivity.class);
                    startActivity(aftersplash);
                } else {
                    Intent aftersplash = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(aftersplash);
                }
                finish();
            }
        },SPLASH_SCREEN);
    }
}