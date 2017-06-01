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

    /**
     * Provide additional information about a named bookcase.
     */
    public String getAdditionalInfo(String item) {
        switch (item) {
            case "Bookcase 1": return "127 books";
            case "Bookcase 2": return "64 books";
            case "Bookcase 3": return "93 books";
            default: return "";
        }
    }
}
