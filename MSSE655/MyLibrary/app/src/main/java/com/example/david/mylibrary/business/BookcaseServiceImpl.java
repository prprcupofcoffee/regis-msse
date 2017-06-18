package com.example.david.mylibrary.business;

import com.example.david.mylibrary.domain.Bookcase;
import com.example.david.mylibrary.persistence.BookcaseRepository;

import java.util.List;

/**
 * Provides access to a collection of {@link Bookcase} objects.
 */

public class BookcaseServiceImpl implements BookcaseService {
    private BookcaseRepository mBookcaseRepository;

    public BookcaseServiceImpl(BookcaseRepository repository) {
        mBookcaseRepository = repository;
    }

    /**
     * Provides all available {@link Bookcase} instances.
     *
     * @return A {@link List} containing every instance in the store
     */
    public List<Bookcase> retrieveAll() {
        return mBookcaseRepository.getAll();
    }

    /**
     * Adds a new {@link Bookcase} instance to the store.
     *
     * @param bookcase  The new {@link Bookcase} to be added
     * @return          The {@link Bookcase} instance
     */
    public Bookcase create(Bookcase bookcase) {
        throw new UnsupportedOperationException();
    }

    /**
     * Changes a {@link Bookcase} in the store.
     *
     * @param bookcase  The existing {@link Bookcase} to be changed
     * @return          The {@link Bookcase}
     */
    public Bookcase update(Bookcase bookcase) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a {@link Bookcase} from the store.
     *
     * @param bookcase  The existing {@link Bookcase} instance to be removed
     */
    public void delete(Bookcase bookcase) {
        throw new UnsupportedOperationException();
    }
}
