package com.threetenterprises.mylibrary.mylibraryservice;

import android.content.Intent;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link MyLibraryService}
 */
public class MyLibraryServiceTests {

    // Builder for MyLibraryService objects
    //
    private class MyLibraryServiceBuilder {
        private Map<String, IntentHandlerFactory> mIntentHandlerFactoryMap = null;

        private MyLibraryServiceBuilder withIntentHandlerFactory(String action, IntentHandlerFactory factory) {
            if (mIntentHandlerFactoryMap == null)
                mIntentHandlerFactoryMap = new HashMap<>();
            mIntentHandlerFactoryMap.put(action, factory);

            return this;
        }

        private MyLibraryService build() {
            MyLibraryService service = new MyLibraryService();
            service.IntentMap = mIntentHandlerFactoryMap;
            return service;
        }
    }

    // Helper method for creating Intent mocks
    //
    private Intent createMockIntent(String action) {
        Intent mock = mock(Intent.class);
        when(mock.getAction()).thenReturn(action);

        return mock;
    }

    @Test
    public void serviceDoesNotDispatchNullIntent()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Arrange
        //
        IntentHandlerFactory mockFactory = mock(IntentHandlerFactory.class);

        MyLibraryService sut =
                new MyLibraryServiceBuilder()
                        .withIntentHandlerFactory(null, mockFactory)
                        .build();

        // Act
        //
        sut.onHandleIntent(null);

        // Assert
        //
        verify(mockFactory, never()).obtain();
    }

    @Test
    public void serviceDoesNotDispatchWithNullIntentMap() {

        // Arrange
        //
        Intent intentOne = createMockIntent("actionOne");

        MyLibraryService sut =
                new MyLibraryServiceBuilder()
                        .build();

        // Act
        //
        sut.onHandleIntent(intentOne);

        // Assert
        //
        // (should just work with no exception being thrown)
    }

    @Test
    public void serviceDispatchesIntentToHandler()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Arrange
        //
        Intent intentOne = createMockIntent("actionOne");
        IntentHandlerFactory mockFactory = mock(IntentHandlerFactory.class);

        MyLibraryService sut =
                new MyLibraryServiceBuilder()
                        .withIntentHandlerFactory(intentOne.getAction(), mockFactory)
                        .build();

        // Act
        //
        sut.onHandleIntent(intentOne);

        // Assert
        //
        verify(mockFactory).obtain();
    }

    @Test
    public void serviceDispatchesIntentToCorrectHandler()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Arrange
        //
        Intent intentOne = createMockIntent("actionOne");
        IntentHandlerFactory mockFactoryOne = mock(IntentHandlerFactory.class);

        Intent intentTwo = createMockIntent("actionTwo");
        IntentHandlerFactory mockFactoryTwo = mock(IntentHandlerFactory.class);

        MyLibraryService sut =
                new MyLibraryServiceBuilder()
                        .withIntentHandlerFactory(intentOne.getAction(), mockFactoryOne)
                        .withIntentHandlerFactory(intentTwo.getAction(), mockFactoryTwo)
                        .build();

        // Act
        //
        sut.onHandleIntent(intentOne);

        // Assert
        //
        verify(mockFactoryOne).obtain();
        verify(mockFactoryTwo, never()).obtain();
    }

    @Test
    public void serviceDoesNotDispatchIntentWithNoHandler()
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Arrange
        //
        Intent intentOne = createMockIntent("actionOne");

        Intent intentTwo = createMockIntent("actionTwo");
        IntentHandlerFactory mockFactoryTwo = mock(IntentHandlerFactory.class);

        MyLibraryService sut =
                new MyLibraryServiceBuilder()
                        .withIntentHandlerFactory(intentTwo.getAction(), mockFactoryTwo)
                        .build();

        // Act
        //
        sut.onHandleIntent(intentOne);

        // Assert
        //
        verify(mockFactoryTwo, never()).obtain();
    }
}