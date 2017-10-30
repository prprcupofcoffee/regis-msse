package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import com.threetenterprises.mylibrary.mylibraryapp.presentation.BookcaseMasterActivityFragment;

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
