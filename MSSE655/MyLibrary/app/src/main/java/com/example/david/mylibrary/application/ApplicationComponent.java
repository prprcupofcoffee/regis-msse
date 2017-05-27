package com.example.david.mylibrary.application;

import android.app.Application;

import java.util.List;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * This is a Dagger component. It aggregates providers for injection into
 * the Application object.
 */
@Component(modules = {
        AndroidInjectionModule.class/*,
        BookcaseMasterActivityModule.class,*/
})
public interface ApplicationComponent {
    void inject(MyLibraryApplication application);
}
