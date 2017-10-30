package com.threetenterprises.mylibrary.mylibraryapp.persistence;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.BookcaseListChanged;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.Messenger;
import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on a Firebase database.
 */

public class FirebaseRepository implements Repository<Bookcase> {
    private static final String CLASS_NAME = FirebaseRepository.class.getSimpleName();
    private final List<Bookcase> mBookcases;
    private final Messenger<BookcaseListChanged> mMessenger;
    private final ValueEventListener mBookcasesChangedEventListener;
    private final FirebaseDatabase mDatabase;

    @Inject
    public FirebaseRepository(FirebaseDatabase database, Messenger<BookcaseListChanged> messenger) {
        mDatabase = database;
        mMessenger = messenger;
        mBookcases = new ArrayList<>();

        // arrange to become aware when bookcase data has changed
        //
        mBookcasesChangedEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    mBookcases.clear();

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        mBookcases.add(child.getValue(Bookcase.class));
                    }

                    mMessenger.sendMessage(new BookcaseListChanged());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(CLASS_NAME, "DatabaseError: " + databaseError);
            }
        };
    }

    /**
     * Provides a list of the items in the repository.
     *
     * @return A {@link List <>} containing the items.
     */
    @Override
    public List<Bookcase> getAll() {
        return mBookcases;
    }

    /**
     * Populate the repository from its backing store.
     */
    @Override
    public void loadAll() {

        // when the content keyed by "bookcases" changes,
        // retrieve that content, sorted by the "id" field
        // of the child nodes
        //
        Query ref = mDatabase.getReference("bookcases").orderByChild("id");
        ref.addValueEventListener(mBookcasesChangedEventListener);
    }

    /**
     * Persist the repository's contents to its backing store.
     */
    @Override
    public void storeAll() {
        for (Bookcase b : getAll()) {

            // extract the properties we want to store
            // (in particular, we are avoiding {@link Persistable#isNew})
            // ((maybe that means that a Firebase-based app doesn't need domain objects to implement Persistable...))
            //
            Map<String, Object> values = new HashMap<>();
            values.put("id", b.getId());
            values.put("name", b.getName());
            values.put("location", b.getLocation());
            values.put("bookcount", b.getBookcount());

            // put the data in the right location in the database
            //
            String key = String.format("bookcases/bookcase_%d", b.getId());
            DatabaseReference ref = mDatabase.getReference(key);
            ref.updateChildren(values);
        }
    }
}
