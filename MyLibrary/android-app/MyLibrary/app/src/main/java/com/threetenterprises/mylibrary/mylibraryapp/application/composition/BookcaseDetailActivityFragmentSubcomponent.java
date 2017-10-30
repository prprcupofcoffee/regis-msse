package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import com.threetenterprises.mylibrary.mylibraryapp.presentation.BookcaseDetailActivityFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link BookcaseDetailActivityFragment}.
 */
@Subcomponent
public interface BookcaseDetailActivityFragmentSubcomponent extends AndroidInjector<BookcaseDetailActivityFragment> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseDetailActivityFragment> {}
}
