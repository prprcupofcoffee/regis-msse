package com.threetenterprises.mylibrary.mylibrarycontent;

import android.net.Uri;

/**
 * Specifies URIs for interacting with the {@link MyLibraryContentProvider}.
 */
public final class MyLibraryContentContract {
    public static final String AUTHORITY = "com.threetenterprises.mylibrary.provider";
    private static final String URI_FORMAT = "content://%s/%s";

    public interface BookcasesColumns {
        String BOOKCASE_ID = "id";
        String BOOKCASE_NAME = "name";
        String BOOKCASE_LOCATION = "location";
        String BOOKCASE_BOOKCOUNT = "bookcount";
    }
    public static final class Bookcases implements BookcasesColumns {
        public static final String BOOKCASES_TABLE = "bookcases";
        public static final Uri BOOKCASES_URI;

        static {
            BOOKCASES_URI = Uri.parse(String.format(URI_FORMAT, AUTHORITY, BOOKCASES_TABLE));
        }
    }
}
