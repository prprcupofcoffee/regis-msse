package com.example.david.mylibrary.application;

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
        BookcaseMasterActivityModule.class,
        BookcaseDetailActivityModule.class
})
public interface ApplicationComponent {
    void inject(MyLibraryApplication application);
}
