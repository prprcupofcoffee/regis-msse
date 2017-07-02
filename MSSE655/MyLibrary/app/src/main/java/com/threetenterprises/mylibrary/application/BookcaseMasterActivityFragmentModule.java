package com.threetenterprises.mylibrary.application;

import android.app.Fragment;

import com.threetenterprises.mylibrary.presentation.BookcaseMasterActivityFragment;

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
@Module(subcomponents = BookcaseMasterActivityFragmentSubcomponent.class)
public abstract class BookcaseMasterActivityFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(BookcaseMasterActivityFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
        bindBookcaseMasterActivityFragmentInjectorFactory(BookcaseMasterActivityFragmentSubcomponent.Builder builder);
}
