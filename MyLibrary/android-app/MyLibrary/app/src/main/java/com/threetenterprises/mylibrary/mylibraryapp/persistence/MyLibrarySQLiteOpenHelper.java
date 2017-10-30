package com.threetenterprises.mylibrary.mylibraryapp.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a facade to a SQLite database. This class has the
 * responsibility to know the database's name and version number,
 * and to apply appropriate DDL when the version number changes.
 */

public final class MyLibrarySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mylibrary.db";
    private static final int DATABASE_VERSION = 1;

    private List<Bookcase> mBookcases;

    @Inject
    public MyLibrarySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSql =
                "create table bookcase (" +
                        "id integer primary key autoincrement," +
                        "name text not null," +
                        "location text not null," +
                        "bookcount int not null)";
        db.execSQL(createSql);
        db.execSQL("insert into bookcase (name, location, bookcount) values ('Bookcase 1', 'Upstairs', 127)");
        db.execSQL("insert into bookcase (name, location, bookcount) values ('Bookcase 2', 'Downstairs', 64)");
        db.execSQL("insert into bookcase (name, location, bookcount) values ('Bookcase 3', 'Den', 93)");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // there is only version 1; no work to do here
        //
        return;
    }
}
