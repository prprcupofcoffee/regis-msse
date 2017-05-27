package com.example.david.mylibrary.presentation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.mylibrary.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookcaseDetailActivityFragment extends Fragment {

    public BookcaseDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get the view so it can be populated
        //
        View rootView = inflater.inflate(R.layout.fragment_bookcasedetail, container, false);

        // get the item that was selected from the intent
        // that started the activity
        //
        String item = getActivity().getIntent()
                                   .getStringExtra("item");

        // show something in the UI
        //
        TextView bookcaseDetailsTextView = (TextView) rootView.findViewById(R.id.detail_text);
        bookcaseDetailsTextView.setText(item);

        // provide the instantiated and initialized fragment
        // and its child views
        //
        return rootView;
    }
}
