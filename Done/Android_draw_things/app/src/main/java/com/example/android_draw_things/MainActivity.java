package com.example.android_draw_things;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_draw_things.views.CustomView;

public class MainActivity extends AppCompatActivity {

    private CustomView customView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = findViewById(R.id.customView);
        button = findViewById(R.id.btnSwapColor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.swapColor();
            }
        });
    }
}
