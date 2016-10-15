package com.example.ashis.booklisting;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//   private static

    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        String qry = bundle.getString("searchQuery");
        Log.i("qry",qry);
        String JSON_RESPONSE = "https://www.googleapis.com/books/v1/volumes?q="+qry;
        Log.i("JSON",JSON_RESPONSE);
       ListView listView = (ListView) findViewById(R.id.list);
        adapter=new CustomAdapter(this,new ArrayList<Books>());
        listView.setAdapter(adapter);
        BookAsyncTask task = new BookAsyncTask();
        task.execute(JSON_RESPONSE);
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

        }
    }
}
