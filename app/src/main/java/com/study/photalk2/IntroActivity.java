package com.study.photalk2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class IntroActivity extends Activity implements View.OnClickListener{
    String TAG=this.getClass().getName();
    Animation animation;
    LinearLayout intro_layout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        intro_layout=(LinearLayout)findViewById(R.id.intro_layout);
        animation = AnimationUtils.loadAnimation(this, R.anim.intro);

        intro_layout.setAnimation(animation); //적용대상 지정
        intro_layout.startAnimation(animation);

        intro_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}





















