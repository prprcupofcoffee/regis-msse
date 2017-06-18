package com.example.david.mylibrary;

import com.example.david.mylibrary.persistence.BookcaseRepository;
import com.example.david.mylibrary.persistence.StringRepository;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RepositoryTests {
    @Test
    public void repository_providesStrings() throws Exception {

        // Arrange
        //
        StringRepository sut = new BookcaseRepository(null);

        // Act
        //
        List<String> values = sut.getAll();

        // Assert
        //
        assertNotNull(values);
    }
}