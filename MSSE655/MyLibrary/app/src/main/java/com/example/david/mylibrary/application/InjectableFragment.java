package com.example.david.mylibrary.application;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * Facilitates injection of dependencies into {@link AppCompatActivity} objects.
 * Ensures the injector is invoked at the proper lifecycle step.
 */

public class InjectableFragment extends Fragment {

    /**
     * Called when this fragment is first attached to an {@link android.app.Activity}.
     * Injects the fragment with its dependencies.
     *
     * @param context   application context of the fragment
     */
    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }
}
