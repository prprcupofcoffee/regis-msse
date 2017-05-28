package com.example.david.mylibrary.persistence;

import java.util.List;

/**
 * Allows management of a collection of {@link String} objects.
 */
public interface StringRepository {

    /**
     * Provides a list of the items in the repository.
     *
     * @return  A {@link List<String>} containing the items.
     */
    List<String> getAll();

    /**
     * Provides additional information about an item.
     *
     * @param item  The item for which additional information should be obtained.
     * @return      Additional information about the indicated item.
     */
    String getAdditionalInfo(String item);
}
