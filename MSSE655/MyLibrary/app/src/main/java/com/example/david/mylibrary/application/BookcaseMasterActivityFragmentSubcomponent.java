package com.example.david.mylibrary.application;

import com.example.david.mylibrary.presentation.BookcaseMasterActivityFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link BookcaseMasterActivityFragment}.
 */
@Subcomponent
public interface BookcaseMasterActivityFragmentSubcomponent extends AndroidInjector<BookcaseMasterActivityFragment> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseMasterActivityFragment> {}
}
