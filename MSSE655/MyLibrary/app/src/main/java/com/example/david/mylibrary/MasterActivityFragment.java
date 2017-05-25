package com.example.david.mylibrary;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MasterActivityFragment extends Fragment {

    // storage for the list of bookcases and the associated View
    //
    private List<String> bookcaseNames = null;
    private ListView bookcasesListView = null;

    public MasterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get the view so it can be populated
        //
        View rootView = inflater.inflate(R.layout.fragment_master, container, false);

        // load the names of the available bookcases into an Adapter
        // so it can be bound to the view
        //
        bookcaseNames = new ArrayList<String>(
                Arrays.asList(getResources().getStringArray(R.array.bookcases_list)));
        ArrayAdapter<String> bookcaseNamesAdapter = new ArrayAdapter<>(
                rootView.getContext(), android.R.layout.simple_list_item_1, bookcaseNames);

        // grab the view and give it something to show
        //
        bookcasesListView = (ListView) rootView.findViewById(R.id.bookcases_listView);
        bookcasesListView.setAdapter(bookcaseNamesAdapter);

        // provide the instantiated list view
        //
        return rootView;
    }
}
