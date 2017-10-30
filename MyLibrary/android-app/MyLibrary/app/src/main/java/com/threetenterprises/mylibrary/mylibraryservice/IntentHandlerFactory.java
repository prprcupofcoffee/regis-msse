package com.threetenterprises.mylibrary.mylibraryservice;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;

/**
 * Creates instances of {@link MyLibraryIntentHandler}.
 */

public class IntentHandlerFactory<T extends MyLibraryIntentHandler> {
    private final Class<T> mClass;
    private final Context mContext;

    public IntentHandlerFactory(Class<T> clazz, Context context) {
        mClass = clazz;
        mContext = context;
    }

    /**
     * Creates an object of type {@link MyLibraryIntentHandler}.
     *
     * @return The newly created {@link MyLibraryIntentHandler} object
     */
    public T obtain() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return mClass.getConstructor(Context.class).newInstance(mContext);
    }
}
