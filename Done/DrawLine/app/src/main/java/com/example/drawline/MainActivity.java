package com.example.drawline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.drawline.customview.CustomView;

public class MainActivity extends AppCompatActivity {

    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.customView);

    }
}
