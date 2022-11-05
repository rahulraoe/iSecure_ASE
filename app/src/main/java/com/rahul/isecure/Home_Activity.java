package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Home_Activity extends AppCompatActivity {

    ImageView firstimg,secondimg,thirdimg,fourthimg,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppNoBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        firstimg=(ImageView)findViewById(R.id.firstimg);
        secondimg=(ImageView)findViewById(R.id.secondimg);
        thirdimg=(ImageView)findViewById(R.id.thirdimg);
        fourthimg=(ImageView)findViewById(R.id.fourthimg);
        user=(ImageView)findViewById(R.id.user_settings);

        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Fmat%20without%20logo.png?alt=media&token=b6518847-46e2-4982-80c0-a8aa31a2743f")
                .into(firstimg);
        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Fdoorwithbg.jpg?alt=media&token=c4da35f9-b931-4eb4-ba3e-1f5ce2dec9ee")
                .into(secondimg);
        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Fcamara%20with%20bg.jpg?alt=media&token=7f78a0d0-c1ae-4733-83d6-9ed7bca31422")
                .into(thirdimg);
        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Falarm3.png?alt=media&token=a613674a-e5e4-413a-a4a4-fac4926fc3e1")
                .into(fourthimg);


        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://img.icons8.com/ios/50/null/user-male-circle.png")
                .into(user);
    }
}