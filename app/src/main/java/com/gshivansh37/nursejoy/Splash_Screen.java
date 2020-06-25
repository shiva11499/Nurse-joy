package com.gshivansh37.nursejoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    ImageView imageView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        imageView= (ImageView) findViewById(R.id.imageview);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        imageView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}