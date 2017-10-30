package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import com.threetenterprises.mylibrary.mylibraryapp.presentation.BookcaseDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link BookcaseDetailActivity}.
 */
@Subcomponent
public interface BookcaseDetailActivitySubcomponent extends AndroidInjector<BookcaseDetailActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseDetailActivity> {}
}
