package com.example.david.mylibrary.persistence;

import android.content.Context;

import com.example.david.mylibrary.domain.Bookcase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on a static
 * set of instances.
 */

public class BookcaseRepository implements Repository<Bookcase> {
    private final String REPOSITORY_FILE = "mylibrary_bookcase_store";
    private final Context mContext;
    private List<Bookcase> mBookcases;

    @Inject
    public BookcaseRepository(Context context) {
        mContext = context;
    }

    /**
     * Fetch all available bookcase names.
     */
    public List<Bookcase> getAll() {
        return mBookcases;
    }

    /**
     * Populate the repository from its backing store.
     */
    public void loadAll() {

        // attempt to open the serialization file
        //
        try (FileInputStream fis = mContext.openFileInput(REPOSITORY_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            mBookcases = (List<Bookcase>) ois.readObject();
        }

        // if there is no serialization file, start with the built-in list
        //
        catch (FileNotFoundException fnfe) {
            mBookcases = Arrays.asList(
                    new Bookcase("Bookcase 1", "Upstairs", 127),
                    new Bookcase("Bookcase 2", "Downstairs", 64),
                    new Bookcase("Bookcase 3", "Den", 93)
            );
        }

        // if any other error is encountered, start with an empty list
        //
        catch (Exception ioe) {
            mBookcases = new ArrayList<>();
        }
    }

    /**
     * Persist the repository's contents to its backing store.
     */
    public void storeAll() {

        // attempt to open the serialization file
        //
        try (FileOutputStream fos = mContext.openFileOutput(REPOSITORY_FILE, Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(mBookcases);
        }

        // if the serialization file can't be created,
        // swallow the exception...while this is usually
        // a bad practice, FileNotFoundException is thrown
        // only when the application's data directory
        // doesn't exist or lacks permissions to create a
        // file, both of which are catastrophic to the app.
        //
        // Logging the error for later transmission to the
        // developer would probably be better, but the app
        // doesn't have that infrastructure yet.
        //
        catch (FileNotFoundException fnfe) {
            // do nothing
        }

        // if any other error is encountered, also swallow
        // the exception, under the never-test-for-a-condition-
        // you-don't-know-how-to-handle principle (because not
        // swallowing it means the method has to be declared as
        // throwing the exception, an example of kick-the-can-down-
        // the-road-and-let-the-caller-who-also-doesn't-know-how-
        // to-handle-it-deal-with-it principle.
        //
        // See previous note about logging.
        //
        catch (Exception ioe) {
            // do nothing
        }
    }
}
