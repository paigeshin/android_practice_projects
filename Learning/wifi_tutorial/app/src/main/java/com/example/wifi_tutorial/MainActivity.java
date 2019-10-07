package com.example.wifi_tutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WifiPasswordDialog.WifiPasswordDialogListener {

    //🔵필수 오브텍트
    private WifiManager wifiManager;
    private List<ScanResult> results;

    private ListView listView;
    private Button button;
    private ArrayList<Device> devices = new ArrayList<>();
    private ArrayAdapter adapter;

    private String wifiPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestForSpecificPermission();

        listView = findViewById(R.id.listview);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 누르면 wifi scanning 시작
                scanWifi();
            }
        });

        //Wifi List
        listView = findViewById(R.id.listview);

        //🔵wifimanager setting
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        //핸드폰에서 와이파이가 꺼져있으면, 켜도록 만들기
        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(this, "Wifi is disabled... You need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        //listview setting => 일단 화면 시작할 때 나옴
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, devices){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView tvTitle = (TextView) view.findViewById(android.R.id.text1);
                String wifiTitle = devices.get(position).SSID + " - " + devices.get(position).getCapabilities();
                tvTitle.setText(wifiTitle);

                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //여기다가 개별 아이템 클릭시 연결하는 로직을 넣으면 된다. connectToWiFi(String ssid ,String password,Context context)
                String wifiTitle = devices.get(position).SSID + " - " + devices.get(position).getCapabilities();

                openDialog(wifiTitle);

                String wifiSSID = devices.get(position).SSID;

                connectToWiFi(wifiSSID, wifiPassword);

//                connectToWiFi(wifiSSID, wifiPassword);
            }
        });
        scanWifi(); //화면 시작하면 wifi scan 시작.

    }

    private void scanWifi() {
        //listview arraylist 초기화
        devices.clear();
        //WifiManger.SCAN_RESULTS_AVAILABLE_ACION라는 intent를 Broadcast에 등록해준 뒤에야 스캔을 시작 할 수 있다.
        //wifiReceiver는 아래에 정의한 BroadcastReceiver임.
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        //wifiManger 내부 함수 호출. => 반드시 broadcastReceiver를 등록해준 후애 호출해야한다.
        wifiManager.startScan();
        Toast.makeText(this, "Scanning Wifi ...", Toast.LENGTH_LONG).show();
    }

    //안드로이드 4대 컴포넌트, wifi를 받음.
    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //List<ScanResult> results;
            //scanning을 마치면 결과를 가져옴.
            results = wifiManager.getScanResults();
            unregisterReceiver(this);
            for(ScanResult scanResult : results){
                Device device = new Device();
                device.setSSID(scanResult.SSID);
                device.setCapabilities(scanResult.capabilities);
                devices.add(device);
                adapter.notifyDataSetChanged();
            }
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

    private void connectToWiFi(String networkSSID, String networkPassword) {

        WifiConfiguration configuration = new WifiConfiguration();
        configuration.SSID = String.format("\"%s\"", networkSSID);
        configuration.preSharedKey = String.format("\"%s\"", networkPassword); //Preshared Key가 password다.

        int netId = wifiManager.addNetwork(configuration);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

    }


    private void openDialog(String wifiName){
        WifiPasswordDialog wifiPasswordDialog = new WifiPasswordDialog(wifiName);
        wifiPasswordDialog.show(getSupportFragmentManager(), "와이파이 비밀번호");
    }


    @Override
    public void applyPassword(String password) {
        wifiPassword = password;
    }

    class Device {

        private String SSID;
        private String capabilities;

        public Device() {

        }

        public Device(String SSID, String capabilities) {
            this.SSID = SSID;
            this.capabilities = capabilities;
        }

        public String getSSID() {
            return SSID;
        }

        public void setSSID(String SSID) {
            this.SSID = SSID;
        }

        public String getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(String capabilities) {
            this.capabilities = capabilities;
        }

    }



}

