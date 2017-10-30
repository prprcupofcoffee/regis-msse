package com.threetenterprises.mylibrary.mylibraryapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.threetenterprises.mylibrary.mylibraryapp.persistence.PersistableObject;

import java.io.Serializable;

/**
 * Models a bookcase, with attributes such as name,
 * location, and number of books.
 */

public class Bookcase extends PersistableObject implements Serializable, Parcelable {
    private int mId;
    private int mBookCount;
    private String mLocation;
    private String mName;

    @Override
    public String toString() {
        return getName();
    }

    public int getBookcount() {
        return mBookCount;
    }

    public void setBookcount(int value) {
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

    /**
     * Asserts there is nothing special about the contents
     * of the created {@link Parcel}.
     *
     * @return Zero, indicating there is no
     *         file descriptor
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object into a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mLocation);
        dest.writeInt(mBookCount);
    }

    public static final Parcelable.Creator<Bookcase> CREATOR = new Parcelable.Creator<Bookcase>() {
        public Bookcase createFromParcel(Parcel in) {
            Bookcase bc = new Bookcase();
            bc.setId(in.readInt());
            bc.setName(in.readString());
            bc.setLocation(in.readString());
            bc.setBookcount(in.readInt());

            return bc;
        }

        public Bookcase[] newArray(int size) {
            return new Bookcase[size];
        }
    };
}
