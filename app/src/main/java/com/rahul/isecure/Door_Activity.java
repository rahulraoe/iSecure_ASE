package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class Door_Activity extends AppCompatActivity {
    LottieAnimationView toggle;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        getSupportActionBar().setTitle("Door");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = findViewById(R.id.nav_toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeState();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeState() {
        if (flag == 0) {
            toggle.setMinAndMaxProgress(0f, 0.43f); //Here, calculation is done on the basis of start and stop frame divided by the total number of frames
            toggle.playAnimation();
            flag = 1;
            //---- Your code here------
        } else {
            toggle.setMinAndMaxProgress(0.5f, 1f);
            toggle.playAnimation();
            flag = 0;
            //---- Your code here------
        }
    }

}