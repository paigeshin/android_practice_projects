package com.example.wifitutorial_recyclerview;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class WifiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Device> devices;
    private WifiManager wifiManager;
    private Context context;
    private FragmentManager fragmentManager;

    WifiAdapter(Context context, ArrayList<Device> devices, WifiManager wifiManager, FragmentManager fragmentManager) {
        this.context = context;
        this.devices = devices;
        this.wifiManager = wifiManager;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_itemview, parent, false);

        return new WifiCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ((WifiCell) holder).tvText.setText(devices.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = devices.get(position).getTitle();
                String SSID = devices.get(position).getSSID();
                openDialog(title, SSID, wifiManager);
            }
        });

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    private void openDialog(String wifiName, String SSID, WifiManager wifiManager){
        WifiPasswordDialog wifiPasswordDialog = new WifiPasswordDialog(wifiName, SSID, wifiManager);
        wifiPasswordDialog.show(fragmentManager, "와이파이 비밀번호");
    }


    private class WifiCell extends RecyclerView.ViewHolder {

        private TextView tvText;

        WifiCell(View view) {
            super(view);

            tvText = view.findViewById(R.id.tvWifiName);

        }
    }
}