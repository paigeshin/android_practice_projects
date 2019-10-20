package com.example.recyclerview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.recyclerview.R;
import com.example.recyclerview.model.Contact;
import com.example.recyclerview.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    //We create our table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL - Structured Query Language
        /*
            CREATE TABLE table_name(id INTEGER PRIMARY KEY, name TEXT, phone_number TEXT);
         */
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT"
                +")";

        db.execSQL(CREATE_CONTACT_TABLE); //Creating our table.

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.DROP_TABLE);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again
        onCreate(db);
        //⁉️ onUpgrade때마다 호출되면.. 그럼 매번 데이터가 초기화 되는게 아닌가? - 일단 더 지켜보자.

    }

    /*
        CRUD = Create, Read, Update, Delete
     */



    //Add Contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert to row
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
    }

    //Get a contact
    public Contact getContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        //SELECT id, name, phone_number FROM table WHERE id = id;
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID = "=?", new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor != null)
        cursor.moveToFirst(); //데이터가 있다면 맨 처음 row부터 query를 진행한다.


        //query를 반납.. result[0]의 개념으로 보면된다.  => column Index   id, name, phone_number
        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));
        cursor.close();

        return contact;
    }

    //Get all Contacts
    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getWritableDatabase();

        List<Contact> contactList = new ArrayList<>();

        //Select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through our data - 데이터가 있다면 맨 처음 row부터 query를 진행한다.
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    //Update Contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update thw row
        //UPDATE table_name SET name=?,phone_number=? WHERE id=?
        return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
    }

    //Delete single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    //Get Contact count
    public int getCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
