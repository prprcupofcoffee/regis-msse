package com.example.david.mylibrary.application;

import android.content.Context;

import com.example.david.mylibrary.persistence.BookcaseRepository;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * This is a Dagger component. It aggregates providers for injection into
 * the Application object.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        BookcaseMasterActivityModule.class,
        BookcaseMasterActivityFragmentModule.class,
        BookcaseDetailActivityModule.class,
        BookcaseDetailActivityFragmentModule.class
})
public interface ApplicationComponent {

    // Classes this component is responsible for injecting:
    //
    void inject(MyLibraryApplication application);
    void inject(BookcaseRepository bookcaseRepository);
}
