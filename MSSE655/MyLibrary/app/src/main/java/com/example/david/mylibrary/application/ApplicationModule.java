package com.example.david.mylibrary.application;

import android.app.Activity;
import android.content.Context;
import android.renderscript.RSInvalidStateException;

import com.example.david.mylibrary.persistence.BookcaseRepository;
import com.example.david.mylibrary.persistence.StringRepository;
import com.example.david.mylibrary.presentation.BookcaseMasterActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * This is a Dagger module. It provides implementations of injectables.
 *
 * It provides a builder method connecting
 * the activity with its injector.
 */
@Module
@Singleton
public abstract class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }
}
