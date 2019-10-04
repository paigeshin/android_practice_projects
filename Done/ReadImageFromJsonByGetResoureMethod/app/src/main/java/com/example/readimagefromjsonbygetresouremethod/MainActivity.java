package com.example.readimagefromjsonbygetresouremethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        try {
            JSONObject jsonObject = new JSONObject(readJSON());
            String image = jsonObject.getString("image");
            System.out.println(image);
            int resource_image = getResources().getIdentifier(image, "drawable", getPackageName());
            System.out.println(resource_image);
            imageView.setImageResource(resource_image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        imageView = findViewById(R.id.imageView);



    }

    private String readJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open("task.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        System.out.println(json);
        return json;
    }

}
