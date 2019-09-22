package com.example.onactivityresult_exercise;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_OK = 3;

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", text);
                intent.putExtra("message", "This is from activity first");
                startActivityForResult(intent, REQUEST_OK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_OK){
            assert data != null;
            Toast.makeText(MainActivity.this, data.getStringExtra("message_from_activity2"), Toast.LENGTH_SHORT).show();
        }
    }
}
