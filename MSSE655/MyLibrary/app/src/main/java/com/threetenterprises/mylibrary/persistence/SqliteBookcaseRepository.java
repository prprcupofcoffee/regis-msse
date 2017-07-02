package com.threetenterprises.mylibrary.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.threetenterprises.mylibrary.domain.Bookcase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on a local
 * SQLite database.
 */

public class SqliteBookcaseRepository implements Repository<Bookcase> {
    private static final String BOOKCASE_TABLE_NAME = "bookcase";
    private static final String BOOKCASE_ID_COLUMN = "id";
    private static final String BOOKCASE_NAME_COLUMN = "name";
    private static final String BOOKCASE_LOCATION_COLUMN = "location";
    private static final String BOOKCASE_BOOKCOUNT_COLUMN = "bookcount";
    private final MyLibrarySQLiteOpenHelper mHelper;
    private List<Bookcase> mBookcases;

    @Inject
    public SqliteBookcaseRepository(MyLibrarySQLiteOpenHelper helper) {
        mHelper = helper;
    }

    /**
     * Fetch all available bookcase names.
     */
    public List<Bookcase> getAll() {
        return mBookcases;
    }

    /**
     * Populate the repository from its backing store.
     */
    public void loadAll() {

        // attempt to query for bookcases
        //
        try (SQLiteDatabase db = mHelper.getReadableDatabase();
             Cursor cursor = db.query(BOOKCASE_TABLE_NAME, new String[] { BOOKCASE_ID_COLUMN, BOOKCASE_NAME_COLUMN, BOOKCASE_LOCATION_COLUMN, BOOKCASE_BOOKCOUNT_COLUMN }, null, null, null, null, null)) {
            cursor.moveToFirst();

            // load the records into the list
            //
            mBookcases = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                mBookcases.add(readBookcaseFromCursor(cursor));
                cursor.moveToNext();
            }
        }
    }

    /**
     * Persist the repository's contents to its backing store. Each {@link Bookcase}
     * object will be either added to or updated in the database, according to whether
     * it was created by user action or originally read from the database.
     */
    public void storeAll() {
        for (Bookcase b : mBookcases) {
            if (b.getIsNew()) {
                addBookcase(b);
                b.setIsNew(false);
            }
            else {
                updateBookcase(b);
            }
        }
    }

    /**
     * Creates a {@link Bookcase} object from the values at the
     * current position of a {@link Cursor}.
     *
     * @param cursor    A {@link Cursor} positioned at a row with values to use in creating a {@link Bookcase} object
     * @return          The newly-constructed {@link Bookcase}
     */
    private Bookcase readBookcaseFromCursor(Cursor cursor) {
        Bookcase b = new Bookcase();
        b.setId(cursor.getInt(0));
        b.setName(cursor.getString(1));
        b.setLocation(cursor.getString(2));
        b.setBookCount(cursor.getInt(3));
        b.setIsNew(false);
        return b;
    }

    /**
     * Adds information about a bookcase to the database.
     *
     * @param bookcase  The new bookcase information to add
     */
    private void addBookcase(Bookcase bookcase) {

        try (SQLiteDatabase db = mHelper.getWritableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(BOOKCASE_NAME_COLUMN, bookcase.getName());
            cv.put(BOOKCASE_LOCATION_COLUMN, bookcase.getLocation());
            cv.put(BOOKCASE_BOOKCOUNT_COLUMN, bookcase.getBookCount());

            long newId = db.insert(BOOKCASE_TABLE_NAME, null, cv);

            // remember the ID assigned by the database
            //
            bookcase.setId((int) newId);
        }
    }

    /**
     * Changes information about a bookcase in the database.
     *
     * @param bookcase  The bookcase information to change
     */
    private void updateBookcase(Bookcase bookcase) {

        try (SQLiteDatabase db = mHelper.getReadableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(BOOKCASE_NAME_COLUMN, bookcase.getName());
            cv.put(BOOKCASE_LOCATION_COLUMN, bookcase.getLocation());
            cv.put(BOOKCASE_BOOKCOUNT_COLUMN, bookcase.getBookCount());

            long rowsUpdated = db.update(
                    BOOKCASE_TABLE_NAME,
                    cv,
                    String.format("%s = ?", BOOKCASE_ID_COLUMN),
                    new String[] { String.valueOf(bookcase.getId()) });
        }
    }
}
