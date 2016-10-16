package com.example.ashis.booklisting;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
      private TextView emptyText;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyText=(TextView) findViewById(R.id.emptyView);
        Intent intent = getIntent();
        String qry = intent.getStringExtra("searchQuery");
        Log.i("qry",qry);
        if (qry.contains(" ")){
            qry.replace(" ","+");
        }
        String JSON_RESPONSE = "https://www.googleapis.com/books/v1/volumes?q="+qry;
        Log.i("JSON",JSON_RESPONSE);
       ListView listView = (ListView) findViewById(R.id.list);
        adapter=new CustomAdapter(this,new ArrayList<Books>());
        listView.setAdapter(adapter);
        BookAsyncTask task = new BookAsyncTask();
        task.execute(JSON_RESPONSE);
        listView.setEmptyView(emptyText);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Books books = adapter.getItem(position);
                Uri booksUri = Uri.parse(books.getmUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW,booksUri);
                startActivity(webIntent);

            }
        });
    }

    private class BookAsyncTask extends AsyncTask<String,Void,List<Books>>{

        @Override
        protected List<Books> doInBackground(String... urls) {
            if (urls.length<1 || urls[0]==null){
                return null;
            }
            List<Books> result = QueryUtils.fetchBookData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Books> books) {
            adapter.clear();
            if (books!=null && !books.isEmpty()){
                adapter.addAll(books);
            }
            View loader = findViewById(R.id.progress);
            loader.setVisibility(View.GONE);
            emptyText.setText("No books Found");
        }
    }
}
