package com.example.david.mylibrary.presentation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.mylibrary.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookcaseMasterActivityFragment extends Fragment {

    // storage for the list of bookcases and the associated View
    //
    private List<String> bookcaseNames = null;
    private ListView bookcasesListView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get the view so it can be populated
        //
        View rootView = inflater.inflate(R.layout.fragment_bookcasemaster, container, false);

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

        // handle tap/click on an item
        //
        bookcasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // get the entry at the indicated position
                // and send it to the detail activity
                //
                String item = (String) bookcasesListView.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), BookcaseDetailActivity.class);
                intent.putExtra("item", item);

                startActivity(intent);
            }
        });

        // provide the instantiated and initialized fragment
        // and its child views
        //
        return rootView;
    }
}
