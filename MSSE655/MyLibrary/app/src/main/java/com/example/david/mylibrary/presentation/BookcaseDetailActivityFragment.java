package com.example.david.mylibrary.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.david.mylibrary.R;
import com.example.david.mylibrary.application.InjectableFragment;
import com.example.david.mylibrary.business.BookcaseService;
import com.example.david.mylibrary.domain.Bookcase;
import com.example.david.mylibrary.persistence.BookcaseRepository;

import java.util.Locale;

import javax.inject.Inject;

/**
 * A fragment containing a simple view displaying
 * detailed information about a bookcase.
 */
public class BookcaseDetailActivityFragment extends InjectableFragment {

    // view to update when the selected bookcase changes
    //
    TextView mBookcaseDetailsView;

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

        // construct the information to display
        //
        String bookcaseInfo = String.format(Locale.getDefault(), "%s (%s books)", bookcase.getName(), bookcase.getBookCount());

        // show something in the UI
        //
        mBookcaseDetailsView.setText(bookcaseInfo);
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

        // obtain the view to be updated when the selected bookcase changes
        //
        mBookcaseDetailsView = (TextView) rootView.findViewById(R.id.detail_text);

        // provide the instantiated fragment and its child views
        //
        return rootView;
    }
}
