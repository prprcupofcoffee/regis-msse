package com.example.david.mylibrary.persistence;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a {@link StringRepository} based on a static
 * set of strings.
 */

public class BookcaseRepository implements StringRepository {

    private static final List<String> bookcaseNames = Arrays.asList(
            "Bookcase 1",
            "Bookcase 2",
            "Bookcase 3"
    );

    /**
     * Fetch all available bookcase names.
     */
    public List<String> getAll() {
        return bookcaseNames;
    }
}
