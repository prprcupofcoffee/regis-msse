package com.threetenterprises.mylibrary.mylibraryservice.application;

import com.threetenterprises.mylibrary.mylibraryservice.MyLibraryService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is a Dagger component. It aggregates providers for injection into
 * the Application object.
 */
@Singleton
@Component(modules = {
        ServiceModule.class
})
public interface ServiceComponent {

    // Classes this component is responsible for injecting.
    //
    void inject(MyLibraryService service);
}
