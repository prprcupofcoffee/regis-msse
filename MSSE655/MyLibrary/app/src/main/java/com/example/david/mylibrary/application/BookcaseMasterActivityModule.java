package com.example.david.mylibrary.application;

import android.app.Activity;
import android.content.Context;

import com.example.david.mylibrary.business.BookcaseService;
import com.example.david.mylibrary.business.BookcaseServiceImpl;
import com.example.david.mylibrary.domain.Bookcase;
import com.example.david.mylibrary.persistence.BookcaseRepository;
import com.example.david.mylibrary.persistence.Repository;
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
@Module(subcomponents = BookcaseMasterActivitySubcomponent.class)
public abstract class BookcaseMasterActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(BookcaseMasterActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
        bindBookcaseMasterActivityInjectorFactory(BookcaseMasterActivitySubcomponent.Builder builder);

    @Provides @Singleton
    public static Repository<Bookcase> provideBookcaseRepository(Context context) {
        return new BookcaseRepository(context);
    }

    @Provides @Singleton
    public static BookcaseService provideBookcaseService(BookcaseRepository repository) {
        return new BookcaseServiceImpl(repository);
    }
}
