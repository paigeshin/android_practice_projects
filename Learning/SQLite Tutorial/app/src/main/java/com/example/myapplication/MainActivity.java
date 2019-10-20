package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.data.DatabaseHandler;
import com.example.myapplication.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    Listview 사용법
    ArrayList<String> 을 준비
    ListView
    ArrayAdpater(p1, p2, p3)
    p1 - context
    p2 - Android default layout
    p3 - String ArrayList
    위와같이 세팅해주고 listview에 arrayAdapter를 추가해준다.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);
        ArrayList<Contact> contacts = new ArrayList<>();
        ArrayList<String> contactString = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter;


        DatabaseHandler databaseHandler = new DatabaseHandler(MainActivity.this);
        contacts.add(new Contact(0, "kim", "124214"));
        contacts.add(new Contact(1, "lee", "124124"));
        contacts.add(new Contact(2, "park", "12421"));

        for(Contact contact : contacts){
            contactString.add(contact.getName());
        }

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactString);

        //add to our listview
        listView.setAdapter(arrayAdapter);

        //Attach eventListener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });

    }
}
