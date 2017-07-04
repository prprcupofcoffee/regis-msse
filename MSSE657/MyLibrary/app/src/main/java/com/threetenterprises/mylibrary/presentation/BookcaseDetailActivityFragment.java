package com.threetenterprises.mylibrary.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.threetenterprises.mylibrary.R;
import com.threetenterprises.mylibrary.application.InjectableFragment;
import com.threetenterprises.mylibrary.business.BookcaseService;
import com.threetenterprises.mylibrary.domain.Bookcase;

import java.util.Locale;

import javax.inject.Inject;

/**
 * A fragment containing a simple view displaying
 * detailed information about a bookcase.
 */
public class BookcaseDetailActivityFragment extends InjectableFragment {

    // view to update when the selected bookcase changes
    //
    TextView mBookcaseNameTextView;
    TextView mBookcaseLocationTextView;
    TextView mBookcaseBookCountTextView;

    // source for bookcase information
    //
    @Inject
    BookcaseService mBookcaseService;

    /**
     * Callback method to be invoked when a bookcase has been
     * selected in another view.
     *
     * @param bookcase The {@link Bookcase} bookcase that was selected.
     */
    public void onSelectedBookcaseChanged(Bookcase bookcase) {

        // show details in the UI
        //
        mBookcaseNameTextView.setText(bookcase.getName());
        mBookcaseLocationTextView.setText(bookcase.getLocation());
        mBookcaseBookCountTextView.setText(String.valueOf(bookcase.getBookCount()));
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater              The {@link LayoutInflater} to use to inflate the XML layout.
     * @param container             The {@link ViewGroup} where the fragment is being sited.
     * @param savedInstanceState    A {@link Bundle} containing saved state if the fragment is being reconstructed.
     * @return                      The {@link View} to display in the parent container.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // create the view to be displayed in the fragment
        //
        View rootView = inflater.inflate(R.layout.fragment_bookcasedetail, container, false);

        // obtain the views to be updated when the selected bookcase changes
        //
        mBookcaseNameTextView = (TextView) rootView.findViewById(R.id.name_text);
        mBookcaseLocationTextView = (TextView) rootView.findViewById(R.id.location_text);
        mBookcaseBookCountTextView = (TextView) rootView.findViewById(R.id.bookcount_text);

        // provide the instantiated fragment and its child views
        //
        return rootView;
    }
}
