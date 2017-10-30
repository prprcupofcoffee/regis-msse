package com.threetenterprises.mylibrary.mylibraryservice.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;
import com.threetenterprises.mylibrary.mylibraryservice.Constants;
import com.threetenterprises.mylibrary.mylibraryservice.MyLibraryIntentHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Fetches a list of bookcase information from the web service.
 */

public class GetBookcasesIntentHandler extends MyLibraryIntentHandler {
    private static final String CLASS_NAME = GetBookcasesIntentHandler.class.getSimpleName();
    private static final String DATABASE_URL = "https://my-library-8c695.firebaseio.com/bookcases.json?auth=buLUXLsmpEhtVTjwP4LqdNrfI5E5MABD2X30TrWh";
    private final RepositoryAsyncTask mRepositoryAsyncTask = new RepositoryAsyncTask();

    public GetBookcasesIntentHandler(Context context) {
        super(context);
    }

    public void onHandleIntent(Intent intent) {
        Log.d(CLASS_NAME, "Requesting bookcase list");

        // Populate the repository from its backing store.
        //
        mRepositoryAsyncTask.execute();
    }

    private class RepositoryAsyncTask extends AsyncTask<String, Void, String> {

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
        protected String doInBackground(String... params) {
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
                cn.setRequestProperty("Accept", "application/json");

                Log.d(CLASS_NAME, "streaming from " + DATABASE_URL);
                InputStream in = new BufferedInputStream(cn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                Log.d(CLASS_NAME, "reading data from " + DATABASE_URL);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }

                return sb.toString();

            } catch (IOException ioe) {
                Log.e(CLASS_NAME, "I/O error reading from " + DATABASE_URL, ioe);
                this.cancel(false);
                return null;

            } finally {
                if (cn != null)
                    cn.disconnect();
            }
        }

        /**
         * <p>Runs on the main thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param s The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // no work to do if no JSON available
            //
            if (s == null || s.length() == 0)
                return;

            try {
                // see how many objects were returned
                //
                JSONObject jsonObject = new JSONObject(s);
                JSONArray names = jsonObject.names();
                int objectCount = names.length();

                // generate a Bookcase object from each one
                //
                ArrayList<Bookcase> bookcases = new ArrayList<>();
                for (int i = 0; i < objectCount; i++) {
                    JSONObject o = jsonObject.getJSONObject(names.getString(i));

                    Bookcase bc = new Bookcase();
                    bc.setId(o.getInt("id"));
                    bc.setName(o.getString("name"));
                    bc.setLocation(o.getString("location"));
                    bc.setBookcount(o.getInt("bookcount"));

                    bookcases.add(bc);
                }

                // announce the new data is available
                //
                Intent newIntent = new Intent(Constants.NOTIFY_BOOKCASE_LIST_CHANGED);
                newIntent.putParcelableArrayListExtra(Constants.EXTRA_BOOKCASES, bookcases);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(newIntent);

            } catch (JSONException je) {
                Log.e(CLASS_NAME, "Malformed JSON: " + s, je);
            }
        }
    }
}
