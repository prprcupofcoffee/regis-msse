package com.threetenterprises.mylibrary.application;

import android.app.Fragment;

import com.threetenterprises.mylibrary.presentation.BookcaseDetailActivityFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;

/**
 * This is a Dagger module. It provides implementations of injectables.
 *
 * It provides a builder method connecting
 * the activity with its injector.
 */
@Module(subcomponents = BookcaseDetailActivityFragmentSubcomponent.class)
public abstract class BookcaseDetailActivityFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(BookcaseDetailActivityFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
        bindBookcaseDetailActivityFragmentInjectorFactory(BookcaseDetailActivityFragmentSubcomponent.Builder builder);
}
