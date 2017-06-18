package com.example.david.mylibrary.persistence;

import android.content.Context;

import com.example.david.mylibrary.domain.Bookcase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a {@link StringRepository} based on a static
 * set of strings.
 */

public class BookcaseRepository /*implements StringRepository*/ {
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
    public List<String> getAll() {
        List<String> bookcaseNames = new ArrayList<>();

        for (Bookcase b : bookcases) {
            bookcaseNames.add(b.getName());
        }

        return bookcaseNames;
    }

    /**
     * Provide additional information about a named bookcase.
     */
    public String getAdditionalInfo(String item) {
        for (Bookcase b : bookcases) {
            if (item.equals(b.getName()))
                return String.format("%d books", b.getBookCount());
        }

        return "";
    }
}
