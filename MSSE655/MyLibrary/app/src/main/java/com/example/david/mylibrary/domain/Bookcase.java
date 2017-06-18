package com.example.david.mylibrary.domain;

/**
 * Models a bookcase, with attributes such as name,
 * location, and number of books.
 */

public class Bookcase {
    private int mBookCount;
    private String mLocation;
    private String mName;

    public Bookcase(String name, String location, int bookCount) {
        mName = name;
        mLocation = location;
        mBookCount = bookCount;
    }

    public int getBookCount() {
        return mBookCount;
    }

    public void setBookCount(int value) {
        mBookCount = value;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String value) {
        mLocation = value;
    }

    public String getName() {
        return mName;
    }

    public void setName(String value) {
        mName = value;
    }
}
