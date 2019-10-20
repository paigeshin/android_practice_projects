package com.example.recyclerview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Contact contact = contactList.get(position);
        ((ViewHolder) holder).phoneNumber.setText(contact.getName());
        ((ViewHolder) holder).contactName.setText(contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    //매번 recycle형식으로 화면에 출력해줄 것을 담아주는 view임.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contactName;
        public TextView phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);

            itemView.setOnClickListener(this);
            contactName.setOnClickListener(this);
            phoneNumber.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Contact contact = contactList.get(position);

//            Log.d("Clicked", "onClick: " + contact.getName());

            switch (v.getId()){

                case R.id.name:
                    Toast.makeText(itemView.getContext(), contact.getName(), Toast.LENGTH_LONG).show();
                    Log.d("ContactName", contact.getName());
                    break;
                case R.id.phone_number:
                    Toast.makeText(itemView.getContext(), contact.getPhoneNumber(), Toast.LENGTH_LONG).show();
                    Log.d("PhoneNumber", contact.getPhoneNumber());
                    break;
            }

        }
    }

}
