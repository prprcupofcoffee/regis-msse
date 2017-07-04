package com.threetenterprises.mylibrary.application;

import com.threetenterprises.mylibrary.presentation.BookcaseMasterActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * This is a Dagger subcomponent. It aggregates providers for injection into
 * {@link com.threetenterprises.mylibrary.presentation.BookcaseMasterActivity}.
 */
@Subcomponent
public interface BookcaseMasterActivitySubcomponent extends AndroidInjector<BookcaseMasterActivity> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookcaseMasterActivity> {}
}
