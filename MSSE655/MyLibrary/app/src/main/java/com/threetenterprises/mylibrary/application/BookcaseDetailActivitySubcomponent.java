package com.threetenterprises.mylibrary.application;

import com.threetenterprises.mylibrary.presentation.BookcaseDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link com.threetenterprises.mylibrary.presentation.BookcaseDetailActivity}.
 */
@Subcomponent
public interface BookcaseDetailActivitySubcomponent extends AndroidInjector<BookcaseDetailActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseDetailActivity> {}
}
