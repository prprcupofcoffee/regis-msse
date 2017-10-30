package com.threetenterprises.mylibrary.mylibraryapp.persistence;

/**
 * Maintains state for domain classes intended to be
 * persistable to storage.
 */

public class PersistableObject {
    private boolean isNew = true;

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean value) {
        isNew = value;
    }
}
