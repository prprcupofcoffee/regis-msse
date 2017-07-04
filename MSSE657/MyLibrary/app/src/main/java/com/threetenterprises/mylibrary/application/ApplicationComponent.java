package com.threetenterprises.mylibrary.application;

import com.threetenterprises.mylibrary.persistence.SerializedBookcaseRepository;
import com.threetenterprises.mylibrary.persistence.SqliteBookcaseRepository;

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
    void inject(SerializedBookcaseRepository bookcaseRepository);
    void inject(SqliteBookcaseRepository bookcaseRepository);
}
