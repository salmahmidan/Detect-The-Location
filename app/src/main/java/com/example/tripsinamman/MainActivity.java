package com.example.tripsinamman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void buttonClickPassenger(View view) {
        if(mAuth.getCurrentUser()!=null) {
            Intent i = new Intent(MainActivity.this, HomeActivityForPassenger.class);
            MainActivity.this.startActivity(i);
        }else{
            Intent i = new Intent(MainActivity.this,LoginPassenger.class );
            MainActivity.this.startActivity(i);
        }

    }
    public void buttonClickDriver(View view) {
        if(mAuth.getCurrentUser()==null) {

            Intent i = new Intent(MainActivity.this, LoginDriver.class);
            MainActivity.this.startActivity(i);
        }else{
            Intent i = new Intent(MainActivity.this, HomeActivityForDriver.class);
            MainActivity.this.startActivity(i);
        }
    }


    public void logout(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance ();
        mAuth.signOut ();
        finish ();
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"You Are Now Logged Out",Toast.LENGTH_LONG).show();

    }
}