package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //splash screen done
        setTheme(R.style.AppNoBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                if (auth.getCurrentUser() != null) {
                    // already signed in
                    Intent intent=new Intent(MainActivity.this,OnBoarding_Activity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Intent intent=new Intent(MainActivity.this,OnBoarding_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 900);

    }
}