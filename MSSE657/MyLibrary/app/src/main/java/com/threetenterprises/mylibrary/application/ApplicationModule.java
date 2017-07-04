package com.threetenterprises.mylibrary.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. It provides implementations of injectables.
 */
@Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
