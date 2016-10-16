package com.example.ashis.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        final EditText editText_search  = (EditText) findViewById(R.id.editText_search);


        Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = editText_search.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("searchQuery",searchText);
                startActivity(intent);
                finish();
            }
        });
    }
}
