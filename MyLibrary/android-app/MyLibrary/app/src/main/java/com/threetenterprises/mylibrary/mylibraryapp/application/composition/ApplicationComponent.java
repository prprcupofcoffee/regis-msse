package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import com.threetenterprises.mylibrary.mylibraryapp.application.MyLibraryApplication;
import com.threetenterprises.mylibrary.mylibraryapp.business.BookcaseServiceImpl;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.SerializedBookcaseRepository;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.SqliteBookcaseRepository;

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

    // Classes this component is responsible for injecting.
    // Any dependency of an Activity or Fragment needs to be
    // listed here.
    //
    void inject(MyLibraryApplication application);
    void inject(SerializedBookcaseRepository bookcaseRepository);
    void inject(SqliteBookcaseRepository bookcaseRepository);
    void inject(BookcaseServiceImpl bookcaseService);
}
