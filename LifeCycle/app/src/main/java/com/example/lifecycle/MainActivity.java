package com.example.lifecycle;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText name;
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.guess_button);
        name = findViewById(R.id.guess_field);

        showGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guess = name.getText().toString().trim();
                if(!guess.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                    intent.putExtra("name", guess);
                    intent.putExtra("a", " babo");
                    intent.putExtra("b", 123);

                    startActivityForResult(intent, REQUEST_CODE);
                    //startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Enter guess", Toast.LENGTH_LONG).show();
                }
            }
        });

        Toast.makeText(this, "onCreate()", Toast.LENGTH_LONG).show();
        Log.d("Cycle", "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Cycle", "onStart()");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("Cycle", "onPostResume()");
    }

    //Foreground. The stage where users see actually things.
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Cycle", "onResume()");
    }

    //Phone Call. => if some other activity such as phone call comes, it stops temporarily other activities.
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Cycle", "onPause()");
    }

    // onStop() => onRestart() => onStart()
    // onStop() => onCreate()
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Cycle", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Cycle", "onRestart()");
    }

    //Mostly, save the data in this stage
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Cycle", "onDestroy()");
    }

    //그냥 startActivity보다 이렇게 하는 것이 보안적으로 더 안정적이다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            assert data != null;
            String message = data.getStringExtra("message");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

    }
}
