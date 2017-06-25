package com.example.david.mylibrary.persistence;

import com.example.david.mylibrary.domain.Bookcase;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SerializedBookcaseRepositoryTests {
    @Test
    public void repository_providesStrings() throws Exception {

        // Arrange
        //
        SerializedBookcaseRepository sut = new SerializedBookcaseRepository(null);

        // Act
        //
        List<Bookcase> values = sut.getAll();

        // Assert
        //
        assertNotNull(values);
    }
}