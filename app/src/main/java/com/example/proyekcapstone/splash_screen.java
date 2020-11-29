package com.example.proyekcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyekcapstone.R;

public class splash_screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);

        int waktu_loading=4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent home=new Intent(splash_screen.this, MainActivity.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}


