package com.example.david.mylibrary.application;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * This is a Dagger component. It maintains the application's
 * object graph.
 */
@Component(modules = {
        AndroidInjectionModule.class,
        BookcaseMasterActivityModule.class
})
public interface ApplicationComponent {
}
