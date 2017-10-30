package com.threetenterprises.mylibrary.mylibrarycontent;

import android.content.UriMatcher;

/**
 * A {@link UriMatcher} prepopulated with values germane to this content provider.
 */

public class ContentUriMatcher extends UriMatcher {
    public static final int BOOKCASES = 1;
    public static final int BOOKCASES_ID = 2;

    public ContentUriMatcher() {
        super(UriMatcher.NO_MATCH);

        addUriWithAuthority(MyLibraryContentContract.Bookcases.BOOKCASES_TABLE, BOOKCASES);
        addUriWithAuthority(MyLibraryContentContract.Bookcases.BOOKCASES_TABLE + "/#", BOOKCASES_ID);
    }

    private void addUriWithAuthority(String path, int code) {
        this.addURI(MyLibraryContentContract.AUTHORITY, path, code);
    }
}
