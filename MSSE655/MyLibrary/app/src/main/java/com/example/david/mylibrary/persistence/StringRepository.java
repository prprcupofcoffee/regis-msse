package com.example.david.mylibrary.persistence;

import java.util.List;

/**
 * Allows management of a collection of {@link String} objects.
 */
public interface StringRepository {
    List<String> getAll();
}
