package com.example.david.mylibrary.business;

import com.example.david.mylibrary.domain.Bookcase;

import java.util.List;

/**
 * Provides access to a collection of {@link Bookcase} objects.
 */

public interface BookcaseService {

    /**
     * Provides all available {@link Bookcase} instances.
     *
     * @return A {@link List} containing every instance in the store
     */
    public List<Bookcase> retrieveAll();

    /**
     * Sends all {@link Bookcase} instances to persistent storage.
     */
    public void save();

    /**
     * Adds a new {@link Bookcase} instance to the store.
     *
     * @param bookcase  The new {@link Bookcase} to be added
     * @return          The {@link Bookcase} instance
     */
    public Bookcase create(Bookcase bookcase);

    /**
     * Changes a {@link Bookcase} in the store.
     *
     * @param bookcase  The existing {@link Bookcase} to be changed
     * @return          The {@link Bookcase}
     */
    public Bookcase update(Bookcase bookcase);

    /**
     * Removes a {@link Bookcase} from the store.
     *
     * @param bookcase  The existing {@link Bookcase} instance to be removed
     */
    public void delete(Bookcase bookcase);
}
