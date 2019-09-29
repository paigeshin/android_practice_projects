package com.example.hanjidelivery.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hanjidelivery.R;

public class VoidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_void);

        //put data
        String message = "Hello World";
        SharedPreferences sharedPreferences = getSharedPreferences("shared1", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("message", message);
        editor.apply(); //saving to disk!

        //get data back from SharedPreferences
        SharedPreferences getSharedData = getSharedPreferences("shared1", MODE_PRIVATE);
        String value = getSharedData.getString("message", "Nothing yet");

    }
}
