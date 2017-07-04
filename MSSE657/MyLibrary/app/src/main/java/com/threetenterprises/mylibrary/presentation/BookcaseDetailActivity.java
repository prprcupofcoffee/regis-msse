package com.threetenterprises.mylibrary.presentation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.threetenterprises.mylibrary.R;
import com.threetenterprises.mylibrary.domain.Bookcase;

public class BookcaseDetailActivity extends AppCompatActivity {

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
            Bookcase bookcase = (Bookcase) getIntent().getSerializableExtra("item");

            fragment.onSelectedBookcaseChanged(bookcase);
        }

        mNavDrawerItems = getResources().getStringArray(R.array.navdrawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // give the navigation drawer some content
        //
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mNavDrawerItems));

        // act on selections made from the navigation drawer
        //
        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), BookcaseMasterActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(view.getContext(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
                }
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        // refresh the options menu when the navigation drawer
        // is opened and closed
        //
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navdrawer_open, R.string.navdrawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        // ensure the hamburger icon is displayed so there is
        // a touch point to open the navigation drawer
        //
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
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

        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // once the layout has been fully realized,
        // make sure the navigation drawer listener
        // is aware of the drawer's open state
        //
        mActionBarDrawerToggle.syncState();
    }
}
