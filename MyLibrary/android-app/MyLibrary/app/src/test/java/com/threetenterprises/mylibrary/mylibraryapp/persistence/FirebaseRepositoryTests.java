package com.threetenterprises.mylibrary.mylibraryapp.persistence;

import android.provider.ContactsContract;
import android.renderscript.Sampler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.BookcaseListChanged;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.Messenger;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FirebaseRepository}
 */
public class FirebaseRepositoryTests {

    // Builder for FirebaseDatabase objects
    //
    private class FirebaseDatabaseBuilder {
        private DatabaseReference mDatabaseReference = mock(DatabaseReference.class);
        private Query mQuery = mock(Query.class);

        private FirebaseDatabaseBuilder withDatabaseReference(DatabaseReference databaseReference) {
            mDatabaseReference = databaseReference;
            return this;
        }

        private FirebaseDatabaseBuilder withQuery(Query query) {
            mQuery = query;
            return this;
        }

        private FirebaseDatabase build() {
            FirebaseDatabase mockDb = mock(FirebaseDatabase.class);
            when(mockDb.getReference(anyString())).thenReturn(mDatabaseReference);
            when(mDatabaseReference.orderByChild(anyString())).thenReturn(mQuery);

            return mockDb;
        }
    }

    // Builder for FirebaseRepository objects
    //
    private class FirebaseRepositoryBuilder {
        private FirebaseDatabase mDatabase = null;
        private Messenger<BookcaseListChanged> mMessenger = null;

        private FirebaseRepositoryBuilder withDatabase(FirebaseDatabase database) {
            mDatabase = database;
            return this;
        }

        private FirebaseRepositoryBuilder withMessenger(Messenger<BookcaseListChanged> messenger) {
            mMessenger = messenger;
            return this;
        }

        private FirebaseRepository build() {
            return new FirebaseRepository(mDatabase, mMessenger);
        }
    }

    @Test
    public void testLoadAllRequestsBookcasesFromDatabase() {

        // Arrange
        //
        FirebaseDatabase database =
                new FirebaseDatabaseBuilder()
                        .build();

        FirebaseRepository sut =
                new FirebaseRepositoryBuilder()
                        .withDatabase(database)
                        .build();

        // Act
        //
        sut.loadAll();

        // Assert
        // - that FirebaseDatabase::getReference was called with
        //   the argument "bookcases"
        //
        verify(database).getReference(eq("bookcases"));
    }

    @Test
    public void testLoadAllRequestsBookcasesSortedById() {

        // Arrange
        //
        DatabaseReference ref = mock(DatabaseReference.class);
        FirebaseDatabase database =
                new FirebaseDatabaseBuilder()
                        .withDatabaseReference(ref)
                        .build();

        FirebaseRepository sut =
                new FirebaseRepositoryBuilder()
                        .withDatabase(database)
                        .build();

        // Act
        //
        sut.loadAll();

        // Assert
        // - that DatabaseReference::orderByChild was called with
        //   the argument "id"
        //
        verify(ref).orderByChild(eq("id"));
    }

    @Test
    public void testLoadAllListensForValue() {

        // Arrange
        //
        Query query = mock(Query.class);
        FirebaseDatabase database =
                new FirebaseDatabaseBuilder()
                        .withQuery(query)
                        .build();

        FirebaseRepository sut =
                new FirebaseRepositoryBuilder()
                        .withDatabase(database)
                        .build();

        // Act
        //
        sut.loadAll();

        // Assert
        // - that Query::addValueEventListener was called with
        //   any argument of type ValueEventListener
        //
        verify(query).addValueEventListener(any(ValueEventListener.class));
    }
}