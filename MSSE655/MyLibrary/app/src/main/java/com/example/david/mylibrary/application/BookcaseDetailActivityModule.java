package com.example.david.mylibrary.application;

import android.app.Activity;

import com.example.david.mylibrary.presentation.BookcaseDetailActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * This is a Dagger module. It provides a builder method connecting
 * the activity with its injector.
 */
@Module(subcomponents = BookcaseDetailActivitySubcomponent.class)
abstract class BookcaseDetailActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(BookcaseDetailActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
        bindBookcaseMasterActivityFactory(BookcaseDetailActivitySubcomponent.Builder builder);
}
