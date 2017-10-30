package com.threetenterprises.mylibrary.mylibraryservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.threetenterprises.mylibrary.mylibraryservice.application.DaggerServiceComponent;
import com.threetenterprises.mylibrary.mylibraryservice.application.ServiceModule;

import java.util.Map;

import javax.inject.Inject;

/**
 * Entry point to the MyLibrary service component. Dispatches
 * {@link Intent}s to appropriate handlers.
 */

public class MyLibraryService extends IntentService {
    public static final String CLASS_NAME = MyLibraryService.class.getSimpleName();

    public MyLibraryService() {
        super("MyLibraryService");
    }

    // Holds correspondence between action and handler class
    //
    @Inject
    Map<String, IntentHandlerFactory> IntentMap;

    /**
     * Called by the system when the service is first created. Ensures
     * the service is injected with its dependencies.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        DaggerServiceComponent.builder()
                .serviceModule(new ServiceModule(this.getApplicationContext(), this))
                .build()
                .inject(this);
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        // if the intent is null, the service is restarting the app,
        // and there is no work being requested
        //
        if (intent == null)
            return;

        // if there is no map, we can't handle the intent
        //
        if (IntentMap == null)
            return;

        // find the handler for this intent, if there is one
        //
        IntentHandlerFactory factory = IntentMap.get(intent.getAction());
        if (factory == null)
            return;

        // send off the Intent
        //
        try {
            MyLibraryIntentHandler handler = factory.obtain();
            if (handler != null)
                handler.onHandleIntent(intent);
        } catch (Exception ex) {
            Log.e(CLASS_NAME, "Unable to obtain handler for " + intent.getAction(), ex);
        }
    }
}
