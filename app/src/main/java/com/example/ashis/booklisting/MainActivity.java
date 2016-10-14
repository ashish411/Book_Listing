package com.example.ashis.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Books> book = new ArrayList<Books>();
        book.add(new Books("android","andr","ashish"));
        ListView listView = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this,book);
        listView.setAdapter(adapter);

    }
}
