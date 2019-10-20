package com.example.bluetoothtutorial;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BluetoothRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "BluetoothAdapterRec";

    private Context context;
    private ArrayList<BluetoothDevice> bluetoothDevices;
    private BluetoothAdapter bluetoothAdapter;

    public BluetoothRecyclerviewAdapter(Context context, ArrayList<BluetoothDevice> bluetoothDevices, BluetoothAdapter bluetoothAdapter) {
        this.context = context;
        this.bluetoothDevices = bluetoothDevices;
        this.bluetoothAdapter = bluetoothAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_device, parent, false);

        return new BluetoothCell(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ((BluetoothCell) holder).name.setText(bluetoothDevices.get(position).getName());
        ((BluetoothCell) holder).address.setText(bluetoothDevices.get(position).getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bluetoothAdapter.cancelDiscovery();

                Log.d(TAG, "onItemClick: deviceName = " + bluetoothDevices.get(position).getName());
                Log.d(TAG, "onItemClick: deviceAddress = " + bluetoothDevices.get(position).getAddress());

                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
                    Log.d(TAG, "Trying to pair with " + bluetoothDevices.get(position).getName());
                    bluetoothDevices.get(position).createBond();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }


    private class BluetoothCell extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView address;

        public BluetoothCell(View view) {
            super(view);

            name = view.findViewById(R.id.tvDeviceName);
            address = view.findViewById(R.id.tvDeviceAddress);

        }
    }
}
