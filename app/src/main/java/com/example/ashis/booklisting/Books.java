package com.example.ashis.booklisting;

/**
 * Created by ashis on 10/14/2016.
 */
public class Books {
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;

    public Books(String bookTitle, String bookAuthor, String bookPublisher) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
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
