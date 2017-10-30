package com.threetenterprises.mylibrary.mylibraryservice;

import android.content.Context;

import com.threetenterprises.mylibrary.mylibraryservice.handlers.GetBookcasesIntentHandler;
import com.threetenterprises.mylibrary.mylibraryservice.handlers.PutBookcaseIntentHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Builds the intent-to-handler-factory map that is
 * injected by Dagger into {@link MyLibraryService}.
 */

public final class IntentHandlerFactoryMapProvider {
    public static Map<String, IntentHandlerFactory> provideMap(Context context) {
        Map<String, IntentHandlerFactory> map = new HashMap<>();

        map.put(Constants.ACTION_GET_BOOKCASES, new IntentHandlerFactory<>(GetBookcasesIntentHandler.class, context));
        map.put(Constants.ACTION_PUT_BOOKCASE, new IntentHandlerFactory<>(PutBookcaseIntentHandler.class, context));

        return map;
    }
}
