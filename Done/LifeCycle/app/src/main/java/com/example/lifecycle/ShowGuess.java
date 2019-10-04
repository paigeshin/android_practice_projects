package com.example.lifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);
        textView = findViewById(R.id.textView);

        Bundle extra = getIntent().getExtras();

        if(extra != null){

            String wholeText = extra.getString("name") + extra.getString("a") + extra.getInt("b");
            textView.setText(wholeText);

        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("message", "From Second Activity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
