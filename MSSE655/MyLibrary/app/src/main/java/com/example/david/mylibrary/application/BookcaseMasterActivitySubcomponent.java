package com.example.david.mylibrary.application;

import com.example.david.mylibrary.presentation.BookcaseMasterActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It allows dependencies to
 * be injected into {@link com.example.david.mylibrary.presentation.BookcaseMasterActivity}.
 */
@Subcomponent
public interface BookcaseMasterActivitySubcomponent extends AndroidInjector<BookcaseMasterActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseMasterActivity> {}
}
