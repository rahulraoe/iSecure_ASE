package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

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
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Fdoorwithbg-removebg-preview.png?alt=media&token=95e28ef0-ec43-4462-9e05-3743b8fdd777")
                .into(secondimg);
        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Fcamara_with_bg-removebg-preview.png?alt=media&token=c727cdcb-c36a-4a06-84bf-698d0fa4a0e0")
                .into(thirdimg);
        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://firebasestorage.googleapis.com/v0/b/ase-project17.appspot.com/o/Home_Icons%2Falarm4%20copy.png?alt=media&token=1e5a4b42-7e8a-4e9a-a081-0e9faeb09f11")
                .into(fourthimg);

        firstimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Activity.this,SmartMat_Activity.class);
                startActivity(intent);
            }
        });

        secondimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Activity.this,Door_Activity.class);
                startActivity(intent);
            }
        });

        fourthimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home_Activity.this,Alarm_Activity.class);
                startActivity(intent);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent it=new Intent(Home_Activity.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });


        Glide.with(Home_Activity.this)
                .asBitmap()
                .load("https://img.icons8.com/ios/50/null/user-male-circle.png")
                .into(user);
    }
}