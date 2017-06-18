package com.example.david.mylibrary.persistence;

import android.content.Context;

import com.example.david.mylibrary.domain.Bookcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on a static
 * set of instances.
 */

public class BookcaseRepository {
    private final Context mContext;

    @Inject
    public BookcaseRepository(Context context) {
        mContext = context;
    }

    private static final List<Bookcase> bookcases = Arrays.asList(
            new Bookcase("Bookcase 1", "Upstairs", 127),
            new Bookcase("Bookcase 2", "Downstairs", 64),
            new Bookcase("Bookcase 3", "Den", 93)
    );

    /**
     * Fetch all available bookcase names.
     */
    public List<Bookcase> getAll() {
        return bookcases;
    }
}
