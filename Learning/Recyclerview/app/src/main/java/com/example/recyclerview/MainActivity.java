package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.recyclerview.adapter.RecyclerViewAdapter;
import com.example.recyclerview.data.DatabaseHandler;
import com.example.recyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contact> contactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Recyclerview에서 사용
        contactArrayList = new ArrayList<>();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        db.addContact(new Contact(0, "park", "129412412"));
        db.addContact(new Contact(1, "park", "129412412"));
        db.addContact(new Contact(2, "park", "129412412"));
        db.addContact(new Contact(3, "park", "129412412"));
        db.addContact(new Contact(4, "park", "129412412"));
        db.addContact(new Contact(5, "park", "129412412"));
        db.addContact(new Contact(6, "park", "129412412"));
        db.addContact(new Contact(7, "park", "129412412"));
        db.addContact(new Contact(8, "park", "129412412"));
        db.addContact(new Contact(9, "park", "129412412"));
        //DB에서 값을 가져옴
        List<Contact> contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            Log.d("MainActivity", "onCreate: " + contact.getName());
            contactArrayList.add(contact);
        }



        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
