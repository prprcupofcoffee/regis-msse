package com.threetenterprises.mylibrary.mylibrarycontent;

import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link MyLibraryContentProvider}
 */
public class MyLibraryContentProviderTests {

    // Builder for MyLibraryContentProvider objects
    //
    private class MyLibraryContentProviderBuilder {
        private SQLiteOpenHelper mDatabaseHelper = null;
        private UriMatcher mUriMatcher = mock(UriMatcher.class);

        private MyLibraryContentProviderBuilder withSQLiteOpenHelper(SQLiteOpenHelper databaseHelper) {
            mDatabaseHelper = databaseHelper;
            return this;
        }

        private MyLibraryContentProviderBuilder withUriMatcher(UriMatcher uriMatcher) {
            mUriMatcher = uriMatcher;
            return this;
        }

        private MyLibraryContentProvider build() {
            MyLibraryContentProvider contentProvider = new MyLibraryContentProvider();
            contentProvider.mDatabaseHelper = mDatabaseHelper;
            contentProvider.mUriMatcher = mUriMatcher;
            return contentProvider;
        }
    }

    @Test
    public void queryOpensDatabaseForReading() {

        // Arrange
        //
        Uri mockUri = mock(Uri.class);

        SQLiteOpenHelper mockDatabaseHelper = mock(SQLiteOpenHelper.class);
        when(mockDatabaseHelper.getReadableDatabase()).thenReturn(mock(SQLiteDatabase.class));

        UriMatcher mockUriMatcher = mock(UriMatcher.class);
        when(mockUriMatcher.match(any(Uri.class))).thenReturn(ContentUriMatcher.BOOKCASES);

        MyLibraryContentProvider sut =
                new MyLibraryContentProviderBuilder()
                        .withSQLiteOpenHelper(mockDatabaseHelper)
                        .withUriMatcher(mockUriMatcher)
                        .build();

        // Act
        //
        sut.query(mockUri, null, null, null, null);

        // Assert
        //
        verify(mockDatabaseHelper).getReadableDatabase();
        verify(mockDatabaseHelper, never()).getWritableDatabase();
    }
}