package com.example.david.mylibrary.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Allows management of a collection of objects.
 */
public interface Repository<T extends Serializable> {

    /**
     * Provides a list of the items in the repository.
     *
     * @return  A {@link List<>} containing the items.
     */
    List<T> getAll();

    /**
     * Populate the repository from its backing store.
     */
    void loadAll();

    /**
     * Persist the repository's contents to its backing store.
     */
    void storeAll();
}
