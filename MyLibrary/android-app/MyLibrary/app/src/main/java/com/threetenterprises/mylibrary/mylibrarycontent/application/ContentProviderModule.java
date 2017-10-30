package com.threetenterprises.mylibrary.mylibrarycontent.application;

import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteOpenHelper;

import com.threetenterprises.mylibrary.mylibrarycontent.ContentUriMatcher;
import com.threetenterprises.mylibrary.mylibrarycontent.MyLibrarySQLiteOpenHelper;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. It provides implementations of injectables.
 */
@Module
public class ContentProviderModule {
    private final Context mContext;

    public ContentProviderModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public SQLiteOpenHelper provideSQLiteOpenHelper() {
        return new MyLibrarySQLiteOpenHelper(mContext);
    }

    @Provides
    public UriMatcher providesUriMatcher() {
        return new ContentUriMatcher();
    }
}
