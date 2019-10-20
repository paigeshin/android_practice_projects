package com.example.bluetoothconnection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnOn, btnOff;

    BluetoothAdapter bluetoothAdapter;
    int REQUEST_CODE_FOR_ENABLE_BLUETOOTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothOnMethod();
        bluetoothOffMethod();

    }

    private void bluetoothOnMethod() {
        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null){
                    Toast.makeText(getApplicationContext(), "This device does not support Bluetooth", Toast.LENGTH_LONG).show();
                }
                if(bluetoothAdapter != null){
                    //블루투스가 켜져 있지 않으면 강제로 켜게 만든다.
                    if(!bluetoothAdapter.enable()){
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, REQUEST_CODE_FOR_ENABLE_BLUETOOTH);
                    }

                }
            }
        });
    }

    private void bluetoothOffMethod(){
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_FOR_ENABLE_BLUETOOTH){
            //블루투스가 enable됨
            if(resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(), "Bluetooth is Enabled", Toast.LENGTH_LONG).show();
            }
            //블루투스가 enable되지 않음
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Bluetooth is not Enabled", Toast.LENGTH_LONG).show();
            }

        }
    }
}
