package com.rahul.isecure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Door_Activity extends AppCompatActivity {
    LottieAnimationView toggle;
    int flag = 0;
    FirebaseAuth mAuth;
    LottieAnimationView animationView,animationView2;
    TextView text_anim;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Door_Activity.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        changeState();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.app_background));
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.app_background));



            }
        });


        text_anim=(TextView) findViewById(R.id.text_anim);

        mAuth = FirebaseAuth.getInstance();
        animationView=(LottieAnimationView) findViewById(R.id.animationView);
        animationView2=(LottieAnimationView) findViewById(R.id.animationView2);

        FirebaseUser user1 = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("Devices/door");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status = dataSnapshot.getValue(String.class);

                if(status.equals("closed")){
                    animationView.setVisibility(View.VISIBLE);
                    animationView2.setVisibility(View.GONE);
                    text_anim.setText("Door is closed. Click the toggle to open.");



                    toggle.setMinAndMaxProgress(0.5f, 1f);
                    toggle.playAnimation();


                }
                else
                {
                    //Toast.makeText(Door_Activity.this, "hello open", Toast.LENGTH_SHORT).show();
                    animationView2.setVisibility(View.VISIBLE);
                    animationView.setVisibility(View.GONE);
//
                    text_anim.setText("Door is open. Click the toggle to close.");
                    toggle.setMinAndMaxProgress(0f, 0.43f); //Here, calculation is done on the basis of start and stop frame divided by the total number of frames
                    toggle.playAnimation();

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Toast.makeText(Door_Activity.this, "Error", Toast.LENGTH_SHORT).show();
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


            FirebaseUser user1 = mAuth.getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("Devices/door");
            myRef1.setValue("closed");


        } else {
            toggle.setMinAndMaxProgress(0.5f, 1f);
            toggle.playAnimation();
            flag = 0;

            FirebaseUser user1 = mAuth.getCurrentUser();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("Devices/door");
            myRef1.setValue("open");


            //---- Your code here------
        }
    }

}