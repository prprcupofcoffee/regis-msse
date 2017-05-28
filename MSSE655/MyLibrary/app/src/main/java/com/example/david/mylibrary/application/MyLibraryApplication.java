package com.example.david.mylibrary.application;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

/**
 * The MyLibrary app entry point.
 *
 * This class maintains the dispatching injectors for application
 * Activities and Fragments. Each dispatching injector will use an
 * Activity or Fragment's concrete class to look up the injector
 * factory to be used to inject dependencies into that Activity or
 * Fragment.
 *
 * For more information, see
 * <ul>
 *     <li><a href="https://google.github.io/dagger//">Dagger &#x2021; A fast dependency injector for Android and Java.</a></li>
 *     <li><a href="https://google.github.io/dagger//users-guide.html">Dagger User's Guide.</a></li>
 *     <li><a href="https://google.github.io/dagger//android.html">Dagger &amp; Android.</a></li>
 * </ul>
 */

public class MyLibraryApplication extends Application implements HasActivityInjector, HasFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.create().inject(this);
    }

    /**
     * Provides a dispatching injector for Activities when requested
     * by the injection framework.
     *
     * @return  a dispatching injector for Activities
     */
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }

    /**
     * Provides a dispatching injector for Fragments when requested
     * by the injection framework.
     *
     * @return  a dispatching injector for Fragments
     */
    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return dispatchingFragmentInjector;
    }
}
