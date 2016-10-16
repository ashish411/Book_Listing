package com.example.ashis.booklisting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashis on 10/14/2016.
 */
public class CustomAdapter extends ArrayAdapter<Books> {
    public CustomAdapter(Activity context, ArrayList<Books> booksArrayList) {
        super(context, 0, booksArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, parent, false);
        }
        final Books currentBook = getItem(position);
        String title = currentBook.getBookTitle();
        TextView titleText = (TextView) rootView.findViewById(R.id.textView_title);
        titleText.setText(title);

        String author = currentBook.getBookAuthor();
        TextView authorText = (TextView) rootView.findViewById(R.id.textView_author);
        authorText.setText(author);

        String publisher = currentBook.getBookPublisher();
        TextView publisherText = (TextView) rootView.findViewById(R.id.textView_publisher);
        publisherText.setText(publisher);

        return rootView;
    }
}
