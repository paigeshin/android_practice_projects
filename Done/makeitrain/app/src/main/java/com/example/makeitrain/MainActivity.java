package com.example.makeitrain;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //Todo
    //만달러, 2만달러 일 때마다 text Color를 바꿔줌

    private TextView moneyText;
    private Button showMoney;
    private Button showTag;

    private int moneyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyText = findViewById(R.id.money_text);


    }

    //make it rain
    public void showMoney(View view){

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyCounter += 1000;
        moneyText.setText(String.valueOf(numberFormat.format(moneyCounter)));

        if(moneyCounter >= 10000) {
            moneyText.setTextColor(getResources().getColor(R.color.myColor));
        }

        if(moneyCounter >= 20000){
            moneyText.setTextColor(Color.parseColor("#432451"));
        }

        switch(moneyCounter){
            case 6000:
                moneyText.setTextColor(Color.GREEN);
                break;
        }

        Log.d("MYTAG", "onClick: Show Money");
    }

    //show tag
    public void showTag(View view){
        Log.d("MYTAG-SHOWTAG", "onClick: Show Money ");
        Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
    }

}
