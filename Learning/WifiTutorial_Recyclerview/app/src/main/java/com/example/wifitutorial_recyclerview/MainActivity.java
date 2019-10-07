package com.example.wifitutorial_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WifiPasswordDialog.WifiPasswordDialogListener {

    //🔵필수 오브젝트
    private WifiManager wifiManager;
    private List<ScanResult> scanResults;

    private RecyclerView recyclerView;
    private Button btnScan;
    private ArrayList<Device> devices = new ArrayList<>();
    private WifiAdapter wifiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestForSpecificPermission();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        scanWifi();

        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(MainActivity.this, "Wifi가 꺼져있습니다... 와이파이를 켜고 있습니다", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerview);
        btnScan = findViewById(R.id.btnScan);
        wifiAdapter = new WifiAdapter(getApplicationContext(), devices, wifiManager, getSupportFragmentManager());
        recyclerView.setAdapter(wifiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifi();
            }
        });

    }

    private void scanWifi(){
        devices.clear();
        //일단 scan을 하려면 broadcast receiver를 등록해줘야한다.
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(MainActivity.this, "와이파이 검색중 ...", Toast.LENGTH_LONG).show();
    }

    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            scanResults = wifiManager.getScanResults();
            unregisterReceiver(wifiReceiver);
            for(ScanResult scanResult : scanResults){
                String SSID = scanResult.SSID;
                String capabilities = scanResult.capabilities;

                Device device = new Device(SSID, capabilities);

                devices.add(device);
            }
            wifiAdapter.notifyDataSetChanged();
        }
    };

    private void requestForSpecificPermission() {

        String[] permissions =  new String[]{
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};

        ActivityCompat.requestPermissions(this, permissions, 101);
    }

    //Dialog에서 값들을 parameter를 통해서 가져옴.
    @Override
    public void applyWifiPassword(String password) {
        Toast.makeText(this, password, Toast.LENGTH_LONG).show();
    }


}
