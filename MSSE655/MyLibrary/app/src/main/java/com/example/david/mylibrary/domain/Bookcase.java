package com.example.david.mylibrary.domain;

import com.example.david.mylibrary.persistence.PersistableObject;

import java.io.Serializable;

/**
 * Models a bookcase, with attributes such as name,
 * location, and number of books.
 */

public class Bookcase extends PersistableObject implements Serializable {
    private int mId;
    private int mBookCount;
    private String mLocation;
    private String mName;

    @Override
    public String toString() {
        return getName();
    }

    public int getBookCount() {
        return mBookCount;
    }

    public void setBookCount(int value) {
        mBookCount = value;
    }

    public int getId() {
        return mId;
    }

    public void setId(int value) {
        mId = value;
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
