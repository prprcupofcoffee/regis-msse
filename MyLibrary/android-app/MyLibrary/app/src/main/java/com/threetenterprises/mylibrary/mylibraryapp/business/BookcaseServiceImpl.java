package com.threetenterprises.mylibrary.mylibraryapp.business;

import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.MyLibraryServiceRepository;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.Repository;

import java.util.List;

import javax.inject.Inject;

/**
 * Provides access to a collection of {@link Bookcase} objects.
 */

public class BookcaseServiceImpl implements BookcaseService {
    private Repository<Bookcase> mBookcaseRepository;

    @Inject
    public BookcaseServiceImpl(Repository repository) {
        mBookcaseRepository = repository;
        mBookcaseRepository.loadAll();
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
     * Sends all {@link Bookcase} instances to persistent storage.
     */
    public void save() {
        mBookcaseRepository.storeAll();
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
