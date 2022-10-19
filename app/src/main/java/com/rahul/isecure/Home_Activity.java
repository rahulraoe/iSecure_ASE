package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}