package com.threetenterprises.mylibrary.mylibraryservice;

import android.content.Context;
import android.content.Intent;

/**
 * Defines a handler for intents for {@link MyLibraryService}.
 */

public abstract class MyLibraryIntentHandler {
    private final Context mContext;

    public abstract void onHandleIntent(Intent intent);

    public MyLibraryIntentHandler(Context context) {
        mContext = context;
    }

    protected final Context getContext() {
        return mContext;
    }
}
