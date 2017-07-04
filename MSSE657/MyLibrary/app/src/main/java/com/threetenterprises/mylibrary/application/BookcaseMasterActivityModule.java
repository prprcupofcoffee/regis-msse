package com.threetenterprises.mylibrary.application;

import android.app.Activity;
import android.content.Context;

import com.threetenterprises.mylibrary.business.BookcaseService;
import com.threetenterprises.mylibrary.business.BookcaseServiceImpl;
import com.threetenterprises.mylibrary.domain.Bookcase;
import com.threetenterprises.mylibrary.persistence.MyLibrarySQLiteOpenHelper;
import com.threetenterprises.mylibrary.persistence.Repository;
import com.threetenterprises.mylibrary.persistence.SqliteBookcaseRepository;
import com.threetenterprises.mylibrary.presentation.BookcaseMasterActivity;

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
    public static MyLibrarySQLiteOpenHelper provideMyLibrarySQLiteOpenHelper(Context context) {
        return new MyLibrarySQLiteOpenHelper(context);
    }

    @Provides @Singleton
    public static Repository<Bookcase> provideBookcaseRepository(MyLibrarySQLiteOpenHelper helper) {
        return new SqliteBookcaseRepository(helper);
    }

    @Provides @Singleton
    public static BookcaseService provideBookcaseService(SqliteBookcaseRepository repository) {
        return new BookcaseServiceImpl(repository);
    }
}
