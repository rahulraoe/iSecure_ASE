package com.rahul.isecure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Account extends AppCompatActivity {
    TextView name,email;
    FirebaseAuth mAuth;
    RelativeLayout logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppBarTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 = mAuth.getCurrentUser();
        logout=(RelativeLayout) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                FirebaseAuth.getInstance().signOut();
                Intent it=new Intent(Account.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });
        DatabaseReference myRef1 = database.getReference("User").child(user1.getUid()).child("name");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status = dataSnapshot.getValue(String.class);
                name.setText(status);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Account.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        DatabaseReference myRef2 = database.getReference("User").child(user1.getUid()).child("email");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String status = dataSnapshot.getValue(String.class);
                email.setText(status);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Account.this, "Error", Toast.LENGTH_SHORT).show();
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