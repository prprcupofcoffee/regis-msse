package com.example.david.mylibrary.application;

import android.app.Activity;

import com.example.david.mylibrary.presentation.BookcaseMasterActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * This is a Dagger module. It provides a factory method connecting
 * the activity with its injector.
 */
@Module(subcomponents = BookcaseMasterActivitySubcomponent.class)
abstract class BookcaseMasterActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(BookcaseMasterActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
        bindBookcaseMasterActivityFactory(BookcaseMasterActivitySubcomponent.Builder builder);
}
