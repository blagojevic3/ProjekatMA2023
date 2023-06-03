package com.example.projekatma2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new KorakPoKorak()).commit();
        }


    }
}