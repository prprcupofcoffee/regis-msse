package com.threetenterprises.mylibrary.mylibraryapp.business;

import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.MyLibraryServiceRepository;
import com.threetenterprises.mylibrary.mylibraryapp.persistence.Repository;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link BookcaseService}
 */
public class BookcaseServiceImplTests {

    // Builder for BookcaseServiceImpl objects
    //
    private class BookcaseServiceImplBuilder {
        private Repository<Bookcase> mRepository = mock(Repository.class);

        private BookcaseServiceImplBuilder withRepository(Repository<Bookcase> repository) {
            mRepository = repository;
            return this;
        }

        private BookcaseServiceImpl build() {
            return new BookcaseServiceImpl(mRepository);
        }
    }

    @Test
    public void service_loadsAllItemsFromRepository() throws Exception {

        // Arrange
        //
        Repository<Bookcase> repository = mock(Repository.class);
        BookcaseServiceImpl sut;

        // Act
        //
        sut = new BookcaseServiceImplBuilder()
                .withRepository(repository)
                .build();

        // Assert
        //
        verify(repository).loadAll();
    }

    @Test
    public void service_retrievesItemsFromRepository() throws Exception {

        // Arrange
        //
        Repository<Bookcase> repository = mock(Repository.class);
        BookcaseServiceImpl sut =
                new BookcaseServiceImplBuilder()
                        .withRepository(repository)
                        .build();

        // Act
        //
        sut.retrieveAll();

        // Assert
        //
        verify(repository).getAll();
    }

    @Test
    public void service_storesItemsToRepository() throws Exception {

        // Arrange
        //
        Repository<Bookcase> repository = mock(Repository.class);
        BookcaseServiceImpl sut =
                new BookcaseServiceImplBuilder()
                        .withRepository(repository)
                        .build();

        // Act
        //
        sut.save();

        // Assert
        //
        verify(repository).storeAll();
    }
}
