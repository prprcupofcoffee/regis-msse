package com.example.david.mylibrary.presentation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.david.mylibrary.R;

public class BookcaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcasedetail);

        // set up the action bar
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // listen to the floating action button
        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().show();
                Snackbar.make(view, "Not ready to add books to this bookcase yet. Some day!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // locate the detail fragment
        //
        BookcaseDetailActivityFragment fragment = (BookcaseDetailActivityFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_bookcasedetail);

        // if it's here, update it with the new bookcase selection
        //
        if (fragment != null) {

            // get the item that was selected from the intent
            // that started the activity
            //
            String bookcaseName = getIntent().getStringExtra("item");

            fragment.onSelectedBookcaseChanged(bookcaseName);
        }
    }

    /**
     * Called when an item in the options menu or action bar is selected.
     *
     * @param item  The {@link MenuItem} that was selected.
     * @return      {@code true} to indicate the selection was processed;
     *              {@code false} to allow normal menu processing to continue
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_hideactionbar:
                getSupportActionBar().hide();
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
