package com.threetenterprises.mylibrary.mylibraryapp.application.composition;

import com.threetenterprises.mylibrary.mylibraryapp.presentation.BookcaseMasterActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link BookcaseMasterActivity}.
 */
@Subcomponent
public interface BookcaseMasterActivitySubcomponent extends AndroidInjector<BookcaseMasterActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseMasterActivity> {}
}
