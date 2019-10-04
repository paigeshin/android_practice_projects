package com.example.sqlitetutorial;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText etName, etSurname, etMark, etId;
    Button btnAdd, btnViewAll, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(MainActivity.this);

        etName = findViewById(R.id.name);
        etSurname = findViewById(R.id.surname);
        etMark = findViewById(R.id.marks);
        etId = findViewById(R.id.update_id);

        btnAdd = findViewById(R.id.addData);
        btnViewAll = findViewById(R.id.viewAll);
        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);

        //데이터 집어넣기
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(etName.getText().toString(), etSurname.getText().toString(), etMark.getText().toString());
                if(isInserted){
                    Toast.makeText(MainActivity.this, "Date Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Date Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        //데이터 전체 보여주기
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAlldata();
                //cursor.getCount()가 0이라면, 데이터가 없다는 뜻임.
                if(cursor.getCount() == 0){
                    // show error message
                    showMessage("Error", "Nothing found");

                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        buffer.append("Id :" + cursor.getString(0) + "\n");
                        buffer.append("Name :" + cursor.getString(1) + "\n");
                        buffer.append("Surname :" + cursor.getString(2) + "\n");
                        buffer.append("Marks :" + cursor.getString(0) + "\n\n");
                    }
                    showMessage("Data", buffer.toString());
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(
                        etId.getText().toString(),
                        etName.getText().toString(),
                        etSurname.getText().toString(),
                        etMark.getText().toString());
                if(isUpdate){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Date not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedRows = myDb.deleteData(etId.getText().toString());
                if(deletedRows > 0){
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Alert Dialog Builder
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
