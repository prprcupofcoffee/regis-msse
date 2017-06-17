package com.example.david.mylibrary.application;

import android.app.ListFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import dagger.android.AndroidInjection;

/**
 * Facilitates injection of dependencies into {@link ListFragment} objects.
 * Ensures the injector is invoked at the proper lifecycle step.
 */

public class InjectableListFragment extends ListFragment {

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
