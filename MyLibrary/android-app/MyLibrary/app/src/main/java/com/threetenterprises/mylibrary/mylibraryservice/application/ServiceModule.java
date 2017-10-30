package com.threetenterprises.mylibrary.mylibraryservice.application;

import android.content.Context;

import com.threetenterprises.mylibrary.mylibraryservice.IntentHandlerFactory;
import com.threetenterprises.mylibrary.mylibraryservice.IntentHandlerFactoryMapProvider;
import com.threetenterprises.mylibrary.mylibraryservice.MyLibraryService;

import java.util.Map;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. It provides implementations of injectables.
 */
@Module
public class ServiceModule {
    private final Context mContext;
    private final MyLibraryService mService;

    public ServiceModule(Context context, MyLibraryService service) {
        this.mContext = context;
        this.mService = service;
    }

    @Provides
    MyLibraryService provideMyLibraryService() {
        return mService;
    }

    @Provides
    Map<String, IntentHandlerFactory> provideIntentHandlerFactoryMap() {
        return IntentHandlerFactoryMapProvider.provideMap(mContext);
    }
}
