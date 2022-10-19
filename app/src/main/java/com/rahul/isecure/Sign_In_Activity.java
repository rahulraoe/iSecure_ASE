package com.rahul.isecure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Sign_In_Activity extends AppCompatActivity {

    EditText user_email,user_password;
    RelativeLayout sign_in;
    FirebaseAuth mAuth;
    TextView bottom_text,forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user_password=(EditText)findViewById(R.id.user_password);
        user_email=(EditText) findViewById(R.id.user_email);
        sign_in=(RelativeLayout) findViewById(R.id.sign_in);
        bottom_text=(TextView)findViewById(R.id.bottom_text);

        bottom_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_In_Activity.this,Sign_Up_Activity.class);
                startActivity(intent);
            }
        });

        forgot_password=(TextView)findViewById(R.id.forgot_password);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(Sign_In_Activity.this,Forgot_Password.class);
//                startActivity(intent);

                Toast.makeText(Sign_In_Activity.this, "not working yet", Toast.LENGTH_SHORT).show();
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user_email.getText().toString().isEmpty()){

                    Toast.makeText(Sign_In_Activity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                }
                else if(user_password.getText().toString().isEmpty()){

                    Toast.makeText(Sign_In_Activity.this, "Please enter password", Toast.LENGTH_LONG).show();
                }
                else
                {

                    mAuth = FirebaseAuth.getInstance();
                    ProgressDialog dialog = ProgressDialog.show(Sign_In_Activity.this, "",
                            "Loading. Please wait...", true);




                    mAuth.signInWithEmailAndPassword(user_email.getText().toString().trim(),user_password.getText().toString())
                            .addOnCompleteListener(Sign_In_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        dialog.dismiss();
//getCount();
                                        Intent intent=new Intent(Sign_In_Activity.this,Home_Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {

                                        dialog.dismiss();
                                        if(task.getException().toString().contains("There is no user")){
                                            Toast.makeText(Sign_In_Activity.this, "User doesn't exist. Please sign up", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(task.getException().toString().contains("password is invalid")){
                                            Toast.makeText(Sign_In_Activity.this, "Email Id and password doesn't match.", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(Sign_In_Activity.this, "Please try again later ",
                                                    Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }
                            });
                }
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