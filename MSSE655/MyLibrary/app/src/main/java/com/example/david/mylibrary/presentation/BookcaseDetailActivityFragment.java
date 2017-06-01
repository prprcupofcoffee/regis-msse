package com.example.david.mylibrary.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.application.InjectableFragment;
import com.example.david.mylibrary.persistence.StringRepository;

import javax.inject.Inject;

/**
 * A fragment containing a simple view displaying
 * detailed information about a bookcase.
 */
public class BookcaseDetailActivityFragment extends InjectableFragment {
    // source for bookcase names
    //
    @Inject
    StringRepository bookcaseNameRespository;

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

        // find additional information about the item, if any
        //
        String additionalInfo = bookcaseNameRespository.getAdditionalInfo(item);
        if (additionalInfo != null && additionalInfo.length() > 0) {
            item = String.format("%s (%s)", item, additionalInfo);
        }

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
