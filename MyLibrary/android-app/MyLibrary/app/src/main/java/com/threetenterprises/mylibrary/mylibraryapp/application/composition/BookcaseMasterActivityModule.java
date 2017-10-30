package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import android.app.Activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.BookcaseListChanged;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.Messenger;
import com.threetenterprises.mylibrary.mylibraryapp.business.BookcaseService;
import com.threetenterprises.mylibrary.mylibraryapp.business.BookcaseServiceImpl;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.FirebaseRepository;
import com.threetenterprises.mylibrary.mylibraryapp.presentation.BookcaseMasterActivity;

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
    public static Messenger<BookcaseListChanged> provideBookcaseListChangedMessenger() {
        return new Messenger<>();
    }

    /*****
     * Use these to provide a SQLite-based repository
     *
    @Provides @Singleton
    public static SQLiteOpenHelper provideSQLiteOpenHelper(Context context) {
        return new MyLibrarySQLiteOpenHelper(context);
    }

    @Provides @Singleton
    public static Repository<Bookcase> provideBookcaseRepository(SQLiteOpenHelper helper) {
        return new SqliteBookcaseRepository(helper);
    }

    @Provides @Singleton
    public static BookcaseService provideBookcaseService(SqliteBookcaseRepository repository) {
        return new BookcaseServiceImpl(repository);
    }
     */

    /*****
     * Use these to provide a webservice-based repository
     *
    @Provides @Singleton
    public static BookcaseService provideBookcaseService(WebServiceBookcaseRepository repository) {
        return new BookcaseServiceImpl(repository);
    }
     */

    /*****
     * Use these to provide an Android-service-based repository
     *
    @Provides @Singleton
    public static BookcaseService provideBookcaseService(MyLibraryServiceRepository serviceManager) {
        return new BookcaseServiceImpl(serviceManager);
    }
     */

    @Provides @Singleton
    public static BookcaseService provideBookcaseService(FirebaseRepository serviceManager) {
        return new BookcaseServiceImpl(serviceManager);
    }

    @Provides @Singleton
    public static FirebaseDatabase provideDatabaseReference() {
        return FirebaseDatabase.getInstance();
    }
}
