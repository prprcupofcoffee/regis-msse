package com.threetenterprises.mylibrary.mylibraryservice.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;
import com.threetenterprises.mylibrary.mylibraryservice.Constants;
import com.threetenterprises.mylibrary.mylibraryservice.MyLibraryIntentHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Fetches a list of bookcase information from the web service.
 */

public class PutBookcaseIntentHandler extends MyLibraryIntentHandler {
    private static final String CLASS_NAME = PutBookcaseIntentHandler.class.getSimpleName();
    private static final String DATABASE_URL = "https://my-library-8c695.firebaseio.com/bookcases.json?auth=buLUXLsmpEhtVTjwP4LqdNrfI5E5MABD2X30TrWh";
    private final RepositoryAsyncTask mRepositoryAsyncTask = new RepositoryAsyncTask();

    public PutBookcaseIntentHandler(Context context) {
        super(context);
    }

    public void onHandleIntent(Intent intent) {
        Log.d(CLASS_NAME, "Storing bookcase");

        Bookcase bc = intent.getParcelableExtra(Constants.EXTRA_BOOKCASE);

        // Persist the bookcase object to the backing store.
        //
        mRepositoryAsyncTask.execute(bc);
    }

    private class RepositoryAsyncTask extends AsyncTask<Bookcase, Void, String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(Bookcase... params) {
            URL url;
            try {
                url = new URL(DATABASE_URL);
            } catch (MalformedURLException mue) {
                Log.e(CLASS_NAME, "Invalid URL " + params[0], mue);
                this.cancel(false);
                return null;
            }

            HttpURLConnection cn = null;
            try {
                Log.d(CLASS_NAME, "connecting to " + DATABASE_URL);
                cn = (HttpURLConnection) url.openConnection();
                cn.setRequestMethod("PUT");

                Log.d(CLASS_NAME, "streaming to " + DATABASE_URL);
                OutputStream out = new BufferedOutputStream(cn.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

                Log.d(CLASS_NAME, "writing data to " + DATABASE_URL);

                JSONObject bookcaseJson = new JSONObject();
                try {
                    bookcaseJson.put("id", params[0].getId())
                            .put("name", params[0].getName())
                            .put("location", params[0].getLocation())
                            .put("bookcount", params[0].getBookcount());

                    bw.append(bookcaseJson.toString());
                } catch (JSONException jsone) {
                    Log.e(CLASS_NAME, "Error creating JSON for bookcase", jsone);
                }

                return null;

            } catch (IOException ioe) {
                Log.e(CLASS_NAME, "I/O error writing to " + DATABASE_URL, ioe);
                this.cancel(false);
                return null;

            } finally {
                if (cn != null)
                    cn.disconnect();
            }
        }
    }
}
