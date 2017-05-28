package com.example.david.mylibrary.presentation;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.persistence.StringRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookcaseDetailActivityFragment extends Fragment {
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

    /**
     * Called when this fragment is first attached to an {@link android.app.Activity}.
     * Injects the fragment with its dependencies.
     *
     * @param context   The application context of the fragment.
     */
    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }
}
