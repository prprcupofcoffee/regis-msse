package com.example.david.mylibrary.application;

import android.app.Activity;

import com.example.david.mylibrary.persistence.BookcaseRepository;
import com.example.david.mylibrary.persistence.StringRepository;
import com.example.david.mylibrary.presentation.BookcaseMasterActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * This is a Dagger module. It provides a builder method connecting
 * the activity with its injector.
 */
@Module(subcomponents = BookcaseMasterActivitySubcomponent.class)
public abstract class BookcaseMasterActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(BookcaseMasterActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
        bindBookcaseMasterActivityInjectorFactory(BookcaseMasterActivitySubcomponent.Builder builder);

    @Provides
    public static StringRepository getStringRepository() {
        return new BookcaseRepository();
    }
}
