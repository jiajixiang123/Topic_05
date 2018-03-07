package com.example.a1.topic_05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Animation animation;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {
        animation = AnimationUtils.loadAnimation(this, R.anim.set);
        image.setAnimation(animation);
        text.setAnimation(animation);
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
    }
}
