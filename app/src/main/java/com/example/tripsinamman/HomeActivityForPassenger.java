package com.example.tripsinamman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivityForPassenger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_for_passenger);
    }

    public void PickUpPass(View view) {
        Intent intent = new Intent(HomeActivityForPassenger.this,MapsActivityPickUp.class);
        startActivity(intent);
    }
/*
    public void whereTo(View view) {
        Intent intent = new Intent(HomeActivityForPassenger.this,WhereToMaps.class);
        startActivity(intent);
    }

 */
}