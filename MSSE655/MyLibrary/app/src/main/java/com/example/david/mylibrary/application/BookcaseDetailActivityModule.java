package com.example.david.mylibrary.application;

import android.app.Activity;

import com.example.david.mylibrary.persistence.BookcaseRepository;
import com.example.david.mylibrary.persistence.StringRepository;
import com.example.david.mylibrary.presentation.BookcaseDetailActivity;

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
 * a builder method connecting
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
