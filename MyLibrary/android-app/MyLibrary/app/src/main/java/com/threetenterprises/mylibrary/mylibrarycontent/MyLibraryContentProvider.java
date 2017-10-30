package com.threetenterprises.mylibrary.mylibrarycontent;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.threetenterprises.mylibrary.mylibrarycontent.application.ContentProviderModule;
import com.threetenterprises.mylibrary.mylibrarycontent.application.DaggerContentProviderComponent;

import javax.annotation.Nonnegative;
import javax.inject.Inject;

public class MyLibraryContentProvider extends ContentProvider {
    private static final String CLASS_NAME;
    static {
        CLASS_NAME = MyLibraryContentProvider.class.getSimpleName();
    }

    @Inject
    public SQLiteOpenHelper mDatabaseHelper;

    @Inject
    public UriMatcher mUriMatcher;

    /**
     * Called by the system when the service is first created. Ensures
     * the service is injected with its dependencies.
     */
    @Override
    public boolean onCreate() {
        DaggerContentProviderComponent.builder()
                .contentProviderModule(new ContentProviderModule(this.getContext()))
                .build()
                .inject(this);

        return true;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(@NonNull Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        switch (mUriMatcher.match(uri)) {
            case ContentUriMatcher.BOOKCASES:
                SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
                long id = db.insert(MyLibraryContentContract.Bookcases.BOOKCASES_TABLE, null, values);
                return ContentUris.withAppendedId(MyLibraryContentContract.Bookcases.BOOKCASES_URI, id);

            default:
                throw new UnsupportedOperationException("Unable to insert to uri: " + uri);
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (mUriMatcher.match(uri)) {
            case ContentUriMatcher.BOOKCASES:
            case ContentUriMatcher.BOOKCASES_ID:
                SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
                return db.query(MyLibraryContentContract.Bookcases.BOOKCASES_TABLE, projection, selection, selectionArgs, null, null, sortOrder);

            default:
                throw new UnsupportedOperationException("Unable to query uri: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
