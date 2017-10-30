package com.threetenterprises.mylibrary.mylibraryapp.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

/**
 * Facilitates injection of dependencies into {@code Activity} objects
 * based on the {@link AppCompatActivity} class. Ensures the injector
 * is invoked at the proper lifecycle step.
 */

public class InjectableAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
