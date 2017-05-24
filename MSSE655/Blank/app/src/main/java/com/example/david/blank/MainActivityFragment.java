package com.example.david.blank;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List<String> thingsList = null;
    private ListView thingsListView = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        thingsList = new ArrayList<String>(
                Arrays.asList(getResources().getStringArray(R.array.things_list)));

        thingsListView = (ListView) rootView.findViewById(R.id.list_of_things);

        ArrayAdapter<String> thingsArrayAdapter = new ArrayAdapter<>(rootView.getContext(),
                android.R.layout.simple_list_item_1, thingsList);

        thingsListView.setAdapter(thingsArrayAdapter);

        thingsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String) thingsListView.getItemAtPosition(position);
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                }

        );

        return rootView;
    }
}
