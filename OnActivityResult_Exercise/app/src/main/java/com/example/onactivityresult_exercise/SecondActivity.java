package com.example.onactivityresult_exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String text = extras.getString("text");
            String message = extras.getString("message");
            textView.setText(text);
            Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("message_from_activity2", "This is from activity2");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
