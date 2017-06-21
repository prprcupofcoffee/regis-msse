package com.example.david.mylibrary.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.application.InjectableListFragment;
import com.example.david.mylibrary.business.BookcaseService;
import com.example.david.mylibrary.domain.Bookcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * A fragment containing a simple view displaying a list of bookcases.
 */
public class BookcaseMasterActivityFragment extends InjectableListFragment {

    /**
     * Interface definition for a callback to be invoked
     * when a bookcase has been selected in another view.
     */
    public interface OnBookcaseSelectedListener {

        /**
         * Callback method to be invoked when a bookcase has been
         * selected in another view.
         *
         * @param bookcase  The {@link Bookcase} that was selected.
         */
        public void onBookcaseSelected(Bookcase bookcase);
    }

    OnBookcaseSelectedListener mBookcaseSelectedListener;

    // source for bookcase information
    //
    @Inject
    BookcaseService mBookcaseService;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The {@link LayoutInflater} to use to inflate the XML layout.
     * @param container          The {@link ViewGroup} where the fragment is being sited.
     * @param savedInstanceState A {@link Bundle} containing saved state if the fragment is being reconstructed.
     * @return The {@link View} to display in the parent container.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get the view so it can be populated
        //
        View rootView = inflater.inflate(R.layout.fragment_bookcasemaster, container, false);

        // give the list something to show
        //
        setListAdapter(createListAdapter(rootView.getContext()));

        // provide the instantiated and initialized fragment
        // and its child views
        //
        return rootView;
    }

    /**
     * Called when the fragment is attached to a context.
     *
     * @param context The {@link Context} to which the fragment is being attached.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // containing activity needs to implement the listener interface
        // so it can be told when the user selects a bookcase
        //
        if (!(context instanceof OnBookcaseSelectedListener)) {
            throw new ClassCastException(context.toString()
                    + " must implement BookcaseMasterActivityFragment.OnBookcaseSelectedListener");
        }

        // grab the interface for later use
        //
        mBookcaseSelectedListener = (OnBookcaseSelectedListener) context;
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();

        // save any changed or added Bookcase instances
        //
        mBookcaseService.save();
    }

    /**
     * Called when an item in the list is selected.
     *
     * @param l         The {@link ListView} where the click happened
     * @param v         The {@link View} that was clicked within the ListView
     * @param position  The position of the view in the list
     * @param id        The row ID of the item that was clicked
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        // get the entry at the indicated position
        // and send it to the detail activity
        //
        Bookcase item = (Bookcase) getListView().getItemAtPosition(position);
        mBookcaseSelectedListener.onBookcaseSelected(item);
    }

    /**
     * Builds a {@link ListAdapter} around a list of {@link Bookcase} information.
     *
     * @param context   The context where the View associated with this SimpleAdapter is running
     * @return          A {@link SimpleAdapter} containing data to display in a {@link ListView}
     */
    private ListAdapter createListAdapter(Context context) {

        // load the names of the available bookcases into an Adapter
        // so it can be bound to the view
        //
        List<Bookcase> bookcases = mBookcaseService.retrieveAll();

        // map data to list of Maps where each entry in the list supplies
        // data for one ListView entry
        //
        List<HashMap<String, String>> maps = new ArrayList<>();

        for (Bookcase b : bookcases) {
            HashMap<String, String> entry = new HashMap<>();
            entry.put("name", String.format("Name: %s", b.getName()));
            entry.put("location", String.format("Location: %s", b.getLocation()));
            maps.add(entry);
        }

        // indicate correspondence between map keys and View IDs
        //
        String[] from = new String[] { "name", "location" };
        int[] to = new int[] { android.R.id.text1, android.R.id.text2 };

        // build a ListAdapter showing how to extract data from a Bookcase list
        //
        return new SimpleAdapter(context, maps, android.R.layout.simple_list_item_2, from, to);
    }

}
