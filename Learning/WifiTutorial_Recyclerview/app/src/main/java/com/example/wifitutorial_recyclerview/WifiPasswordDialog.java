package com.example.wifitutorial_recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class WifiPasswordDialog extends AppCompatDialogFragment {

    private WifiManager wifiManager;
    private TextView tvWifiName;
    private EditText etPassword;

    private String wifiName;
    private String SSID;
    private WifiPasswordDialogListener wifiPasswordDialogListener;

    public WifiPasswordDialog(String wifiName, String SSID, WifiManager wifiManager) {
        this.wifiName = wifiName;
        this.SSID = SSID;
        this.wifiManager = wifiManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.wifi_password_input_dialog, null);
        builder.setView(view)
                .setTitle("와이파이 비밀번호를 입력해주세요.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String wifiPassword = etPassword.getText().toString();
                        connectToWifi(SSID, wifiPassword);
                        wifiPasswordDialogListener.applyWifiPassword(wifiPassword);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        tvWifiName = view.findViewById(R.id.tvWifiTitle);
        tvWifiName.setText(wifiName);
        etPassword = view.findViewById(R.id.etWifiPassword);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface args0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            wifiPasswordDialogListener = (WifiPasswordDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement WifiPasswordDialogListener");
        }

    }

    private void connectToWifi(String networkSSID, String networkPassword){

        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = String.format("\"%s\"", networkSSID);
        wifiConfiguration.preSharedKey = String.format("\"%s\"", networkPassword);

        int netId = wifiManager.addNetwork(wifiConfiguration);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();

    }

    public interface WifiPasswordDialogListener {
        void applyWifiPassword(String password);
    }
}
