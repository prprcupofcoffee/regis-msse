package com.example.david.mylibrary.application;

import com.example.david.mylibrary.presentation.BookcaseDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link com.example.david.mylibrary.presentation.BookcaseDetailActivity}.
 */
@Subcomponent
public interface BookcaseDetailActivitySubcomponent extends AndroidInjector<BookcaseDetailActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseDetailActivity> {}
}
