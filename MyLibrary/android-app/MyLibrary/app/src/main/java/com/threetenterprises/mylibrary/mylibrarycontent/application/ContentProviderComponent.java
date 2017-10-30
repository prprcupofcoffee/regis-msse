package com.threetenterprises.mylibrary.mylibrarycontent.application;

import com.threetenterprises.mylibrary.mylibrarycontent.MyLibraryContentProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is a Dagger component. It aggregates providers for injection into
 * the Application object.
 */
@Singleton
@Component(modules = {
        ContentProviderModule.class
})
public interface ContentProviderComponent {

    // Classes this component is responsible for injecting.
    //
    void inject(MyLibraryContentProvider provider);
}
