package com.example.ashis.booklisting;

/**
 * Created by ashis on 10/14/2016.
 */
public class Books {
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String mUrl;

    public Books(String bookTitle, String bookAuthor, String bookPublisher, String Url) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        mUrl = Url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }
}
