package com.rahul.isecure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Sign_Up_Activity extends AppCompatActivity {
    EditText user_name,user_email,user_password,user_address;
    RelativeLayout sign_up;
    FirebaseAuth mAuth;
    TextView bottom_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user_name=(EditText)findViewById(R.id.user_name);
        user_email=(EditText) findViewById(R.id.user_email);
        user_password=(EditText) findViewById(R.id.user_password);
        user_address=(EditText)findViewById(R.id.user_address);
        sign_up=(RelativeLayout) findViewById(R.id.sign_up);

        bottom_text=(TextView)findViewById(R.id.bottom_text);

        bottom_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_Up_Activity.this,Sign_In_Activity.class);
                startActivity(intent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = ProgressDialog.show(Sign_Up_Activity.this, "",
                        "Loading. Please wait...", true);


                if(user_name.getText().toString().isEmpty()){

                    Toast.makeText(Sign_Up_Activity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(user_email.getText().toString().isEmpty()){

                    Toast.makeText(Sign_Up_Activity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(user_password.getText().toString().isEmpty()){

                    Toast.makeText(Sign_Up_Activity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(isEmailValid(user_email.getText().toString().trim())==false){
                    Toast.makeText(Sign_Up_Activity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(user_name.getText().toString().length()<3)
                {

                    Toast.makeText(Sign_Up_Activity.this, "Name should be greater than 3 characters", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(user_password.getText().toString().length()<6)
                {

                    Toast.makeText(Sign_Up_Activity.this, "Password is too short. should be 6 characters.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else if(user_password.getText().toString().length()<6)
                {

                    Toast.makeText(Sign_Up_Activity.this, "Please Enter your Address", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else
                {

                    mAuth = FirebaseAuth.getInstance();




                    mAuth.createUserWithEmailAndPassword(user_email.getText().toString(), user_password.getText().toString())
                            .addOnCompleteListener(Sign_Up_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user1 = mAuth.getCurrentUser();
                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        Map<String, Object> data = new HashMap<>();
                                        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh.mm aa");
                                        final String    date = dateFormat2.format(new Date()).toString();

                                        data.put("name", user_name.getText().toString());
                                        data.put("email", user_email.getText().toString());
                                        data.put("date",date);
                                        data.put("uid",user1.getUid());

                                        User newuser=new User(user_name.getText().toString(),user_email.getText().toString(),date, user1.getUid());

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference("User");

                                        myRef.child(user1.getUid()).setValue(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("Devices/smartmat");
                                                myRef1.setValue("No one is on mat");
                                                DatabaseReference myRef2= database.getReference("User").child(user1.getUid()).child("Devices/door");
                                                myRef2.setValue("closed");
                                                DatabaseReference myRef3= database.getReference("User").child(user1.getUid()).child("Devices/alarm");
                                                myRef3.setValue("off");
                                                dialog.dismiss();
                                                        Intent intent=new Intent(Sign_Up_Activity.this,Home_Activity.class);
                                                        startActivity(intent);
                                                        finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Sign_Up_Activity.this, "Sign Up failed. Please try again later.", Toast.LENGTH_LONG).show();
                                                     dialog.dismiss();
                                            }
                                        });

//                                        db.collection("Users").document(user.getUid())
//                                                .set(data)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        dialog.dismiss();
//                                                        Intent intent=new Intent(Sign_Up_Activity.this,Home_Activity.class);
//                                                        startActivity(intent);
//                                                        finish();
//                                                    }
//                                                })
//                                                .addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Toast.makeText(Sign_Up_Activity.this, "Sign Up failed. Please try again later.", Toast.LENGTH_LONG).show();
//                                                        dialog.dismiss();
//                                                    }
//                                                });

                                    }
                                    else {

                                        dialog.dismiss();
                                        Toast.makeText(Sign_Up_Activity.this, "Authentication failed."+task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });


    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
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