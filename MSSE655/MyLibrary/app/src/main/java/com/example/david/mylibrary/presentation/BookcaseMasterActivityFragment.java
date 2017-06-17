package com.example.david.mylibrary.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.application.InjectableListFragment;
import com.example.david.mylibrary.persistence.StringRepository;

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
         * @param bookcaseName The name of the bookcase that was selected.
         */
        public void onBookcaseSelected(String bookcaseName);
    }

    OnBookcaseSelectedListener mBookcaseSelectedListener;

    // source for bookcase names
    //
    @Inject
    StringRepository mBookcaseNameRepository;

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

        // load the names of the available bookcases into an Adapter
        // so it can be bound to the view
        //
        List<String> bookcaseNames = mBookcaseNameRepository.getAll();
        ArrayAdapter<String> bookcaseNamesAdapter = new ArrayAdapter<>(
                rootView.getContext(), android.R.layout.simple_list_item_1, bookcaseNames);

        // give the list something to show
        //
        setListAdapter(bookcaseNamesAdapter);

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
        String item = (String) getListView().getItemAtPosition(position);
        mBookcaseSelectedListener.onBookcaseSelected(item);
    }
}
