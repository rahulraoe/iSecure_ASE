package com.rahul.isecure;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SmartMat_Activity extends AppCompatActivity {

LottieAnimationView animationView,animationView2;
TextView text_anim;
    FirebaseAuth mAuth;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_mat);

        getSupportActionBar().setTitle("Smart Mat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        animationView=(LottieAnimationView) findViewById(R.id.animationView);
        animationView2=(LottieAnimationView) findViewById(R.id.animationView2);

        text_anim=(TextView) findViewById(R.id.text_anim);

//        final DocumentReference docRef = db.collection("Users").document(auth.getCurrentUser().getUid()).collection("Devices").document("smartmat");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot snapshot,
//                                @Nullable FirebaseFirestoreException e) {
//                if (e != null) {
//                    Toast.makeText(SmartMat_Activity.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (snapshot != null && snapshot.exists()) {
//
//
//                    String status=snapshot.getString("status");
//
//                    if(status.equals("No one is on mat")){
//                        animationView2.setVisibility(View.VISIBLE);
//                        animationView.setVisibility(View.GONE);
//                        text_anim.setText("No one is on the mat. Checking actively.");
//                    }
//                    else
//                    {
//                        animationView.setVisibility(View.VISIBLE);
//                        animationView2.setVisibility(View.GONE);
//
//                        text_anim.setText("Someone is on the mat. Please check.");
//                    }
//                } else {
//                    Toast.makeText(SmartMat_Activity.this, "some error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 = mAuth.getCurrentUser();
        DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("Devices/smartmat");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
// This method is called once with the initial value and again
                // whenever data at this location is updated.
                String status = dataSnapshot.getValue(String.class);

                if(status.equals("No one is on mat")){
                        animationView2.setVisibility(View.VISIBLE);
                        animationView.setVisibility(View.GONE);
                        text_anim.setText("No one is on the mat. Checking actively.");
                    }
                    else
                    {
                        animationView.setVisibility(View.VISIBLE);
                        animationView2.setVisibility(View.GONE);

                        text_anim.setText("Someone is on the mat. Please check.");
                    }


            }

            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                Toast.makeText(SmartMat_Activity.this, "Error", Toast.LENGTH_SHORT).show();
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

}