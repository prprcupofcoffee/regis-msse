package com.threetenterprises.mylibrary.business;

import com.threetenterprises.mylibrary.domain.Bookcase;
import com.threetenterprises.mylibrary.persistence.Repository;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BookcaseServiceImplTests {
    @Test
    public void service_loadsAllItemsFromRepository() throws Exception {

        // Arrange
        //
        Repository<Bookcase> repository = mock(Repository.class);
        BookcaseServiceImpl sut;

        // Act
        //
        sut = new BookcaseServiceImpl(repository);

        // Assert
        //
        verify(repository).loadAll();
    }

    @Test
    public void service_retrievesItemsFromRepository() throws Exception {

        // Arrange
        //
        Repository<Bookcase> repository = mock(Repository.class);
        BookcaseServiceImpl sut = new BookcaseServiceImpl(repository);

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
        BookcaseServiceImpl sut = new BookcaseServiceImpl(repository);

        // Act
        //
        sut.save();

        // Assert
        //
        verify(repository).storeAll();
    }
}
