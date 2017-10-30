package com.threetenterprises.mylibrary.mylibraryapp.persistence;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.BookcaseListChanged;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.Messenger;
import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;
import com.threetenterprises.mylibrary.mylibrarycontent.MyLibraryContentContract;
import com.threetenterprises.mylibrary.mylibraryservice.Constants;
import com.threetenterprises.mylibrary.mylibraryservice.MyLibraryService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on an Android service.
 */

public class MyLibraryServiceRepository implements Repository<Bookcase> {
    private static final String CLASS_NAME = MyLibraryServiceRepository.class.getSimpleName();
    private final Context mContext;
    private final Messenger<BookcaseListChanged> mMessenger;
    private final BroadcastReceiver mBookcaseListChangedBroadcastReceiver;

    private class BookcaseListChangedBroadcastReceiver extends BroadcastReceiver {
        /**
         * This method is called when the BroadcastReceiver is receiving an Intent
         * broadcast.
         *
         * @param context The Context in which the receiver is running.
         * @param intent  The Intent being received.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(CLASS_NAME, "onReceive called");

            try {
                // grab the bookcases that were sent
                //
                List<Bookcase> bookcases = intent.getParcelableArrayListExtra(Constants.EXTRA_BOOKCASES);

                // make sure the content provider has up-to-date information
                //
                reconcileBookcases(bookcases);

                // alert the UI that there is new data
                //
                mMessenger.sendMessage(new BookcaseListChanged());

            } catch (Exception ex) {
                Log.e(CLASS_NAME, "Invalid data in intent", ex);
            }
        }
    }

    @Inject
    public MyLibraryServiceRepository(Context context, Messenger<BookcaseListChanged> messenger) {
        mContext = context;
        mMessenger = messenger;
        mBookcaseListChangedBroadcastReceiver = new BookcaseListChangedBroadcastReceiver();

        // arrange to become aware when bookcase data has changed
        //
        IntentFilter ift = new IntentFilter(Constants.NOTIFY_BOOKCASE_LIST_CHANGED);
        LocalBroadcastManager.getInstance(context).registerReceiver(mBookcaseListChangedBroadcastReceiver, ift);
    }

    /**
     * Provides a list of the items in the repository.
     *
     * @return A {@link List <>} containing the items.
     */
    @Override
    public List<Bookcase> getAll() {

        // fetch the bookcase information from the content provider
        //
        Uri uri = MyLibraryContentContract.Bookcases.BOOKCASES_URI;
        String columns[] = new String[] {
                MyLibraryContentContract.Bookcases.BOOKCASE_ID,
                MyLibraryContentContract.Bookcases.BOOKCASE_NAME,
                MyLibraryContentContract.Bookcases.BOOKCASE_LOCATION,
                MyLibraryContentContract.Bookcases.BOOKCASE_BOOKCOUNT
        };
        ContentResolver cr = mContext.getContentResolver();

        try (Cursor bookcasesCursor = cr.query(uri, columns, null, null, null)) {
            int idColumn = bookcasesCursor.getColumnIndex(MyLibraryContentContract.Bookcases.BOOKCASE_ID);
            int nameColumn = bookcasesCursor.getColumnIndex(MyLibraryContentContract.Bookcases.BOOKCASE_NAME);
            int locationColumn = bookcasesCursor.getColumnIndex(MyLibraryContentContract.Bookcases.BOOKCASE_LOCATION);
            int bookcountColumn = bookcasesCursor.getColumnIndex(MyLibraryContentContract.Bookcases.BOOKCASE_BOOKCOUNT);

            List<Bookcase> bookcases = new ArrayList<>();
            while (bookcasesCursor.moveToNext()) {
                Bookcase bc = new Bookcase();

                bc.setId(bookcasesCursor.getInt(idColumn));
                bc.setName(bookcasesCursor.getString(nameColumn));
                bc.setLocation(bookcasesCursor.getString(locationColumn));
                bc.setBookcount(bookcasesCursor.getInt(bookcountColumn));

                bookcases.add(bc);
            }

            return bookcases;
        }
    }

    /**
     * Populate the repository from its backing store.
     */
    @Override
    public void loadAll() {

        // create an intent to send to the service
        //
        Intent intent = new Intent(mContext, MyLibraryService.class);
        intent.setAction(Constants.ACTION_GET_BOOKCASES);

        // fire it off - the broadcast receiver will know when the data is ready
        //
        mContext.startService(intent);
    }

    /**
     * Persist the repository's contents to its backing store.
     */
    @Override
    public void storeAll() {
        for (Bookcase b : getAll()) {

            // create an intent to send to the service
            //
            Intent intent = new Intent(mContext, MyLibraryService.class);
            intent.setAction(Constants.ACTION_PUT_BOOKCASE);
            intent.putExtra(Constants.EXTRA_BOOKCASE, (Parcelable) b);

            // fire it off
            //
            mContext.startService(intent);
        }
    }

    private void reconcileBookcases(List<Bookcase> bookcases) {
        String columns[] = new String[] { MyLibraryContentContract.Bookcases.BOOKCASE_ID };
        String selection = MyLibraryContentContract.Bookcases.BOOKCASE_ID + " = ?";
        ContentResolver resolver = mContext.getContentResolver();

        // make sure there is a Bookcase in the database for each
        // Bookcase in the list
        //
        for (Bookcase bc : bookcases) {
            String selectionArgs[] = new String[] { Integer.toString(bc.getId()) };

            try (Cursor bookcaseCursor = resolver.query(MyLibraryContentContract.Bookcases.BOOKCASES_URI, columns, selection, selectionArgs, null)) {

                // if no existing record has this ID, add one
                //
                if (0 == bookcaseCursor.getCount()) {
                    ContentValues cv = new ContentValues();
                    cv.put(MyLibraryContentContract.Bookcases.BOOKCASE_ID, bc.getId());
                    cv.put(MyLibraryContentContract.Bookcases.BOOKCASE_NAME, bc.getName());
                    cv.put(MyLibraryContentContract.Bookcases.BOOKCASE_LOCATION, bc.getLocation());
                    cv.put(MyLibraryContentContract.Bookcases.BOOKCASE_BOOKCOUNT, bc.getBookcount());

                    resolver.insert(MyLibraryContentContract.Bookcases.BOOKCASES_URI, cv);
                }
            }
        }
    }
}
