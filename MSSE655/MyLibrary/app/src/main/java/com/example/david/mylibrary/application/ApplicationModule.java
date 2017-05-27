package com.example.david.mylibrary.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to satisfy dependencies upon
 * the application's Context.
 */
@Module
public final class ApplicationModule {

    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}