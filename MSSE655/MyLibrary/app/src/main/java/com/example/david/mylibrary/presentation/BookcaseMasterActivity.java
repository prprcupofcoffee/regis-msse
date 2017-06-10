package com.example.david.mylibrary.presentation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.application.InjectableAppCompatActivity;
import com.example.david.mylibrary.persistence.StringRepository;

import javax.inject.Inject;

public class BookcaseMasterActivity extends InjectableAppCompatActivity implements BookcaseMasterActivityFragment.OnBookcaseSelectedListener {
    @Inject
    StringRepository mBookcaseRepository;

    private String[] mNavDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bookcasemaster);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().show();
                Snackbar.make(view, String.format("There are %d items in the repository.", mBookcaseRepository.getAll().size()), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mNavDrawerItems = getResources().getStringArray(R.array.navdrawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mNavDrawerItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navdrawer_open, R.string.navdrawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (getActionBar() != null)
                    getActionBar().setTitle("One");
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                if (getActionBar() != null)
                    getActionBar().setTitle("Two");
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bookcasemaster, menu);
        return true;
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

        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        switch (id) {
            case R.id.action_hideactionbar:
                getSupportActionBar().hide();
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    /**
     * Announces that a bookcase has been selected.
     *
     * @param bookcaseName The name of the bookcase that was selected.
     */
    @Override
    public void onBookcaseSelected(String bookcaseName) {

        // locate the detail fragment
        //
        BookcaseDetailActivityFragment fragment = (BookcaseDetailActivityFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_bookcasedetail);

        // if it's here, update it with the new bookcase selection
        //
        if (fragment != null) {
            fragment.onSelectedBookcaseChanged(bookcaseName);
        }

        // otherwise, call out to the detail activity with the
        // selection
        //
        else {
            Intent intent = new Intent(this.getBaseContext(), BookcaseDetailActivity.class);
            intent.putExtra("item", bookcaseName);

            startActivity(intent);
        }
    }
}
