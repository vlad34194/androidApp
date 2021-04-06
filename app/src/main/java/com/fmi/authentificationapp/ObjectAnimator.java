package com.fmi.authentificationapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ObjectAnimator extends AppCompatActivity {
    ImageView imageView;
    Button button;
    android.animation.ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        imageView = (ImageView) findViewById(R.id.carImage);
        button = (Button) findViewById(R.id.btnMove);

        objectAnimator = android.animation.ObjectAnimator.ofFloat(imageView, "x", 6000);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator.setDuration(8000);
                objectAnimator.start();
            }
        });
    }
}