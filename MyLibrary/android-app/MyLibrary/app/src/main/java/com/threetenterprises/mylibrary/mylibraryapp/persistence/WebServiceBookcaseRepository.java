package com.threetenterprises.mylibrary.mylibraryapp.persistence;

import android.os.AsyncTask;
import android.util.Log;

import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.BookcaseListChanged;
import com.threetenterprises.mylibrary.mylibraryapp.application.messaging.Messenger;
import com.threetenterprises.mylibrary.mylibraryapp.domain.Bookcase;

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
import java.util.List;

import javax.inject.Inject;

/**
 * Provides a repository of {@link Bookcase} objects based on a RESTful
 * web service.
 */

public class WebServiceBookcaseRepository implements Repository<Bookcase> {
    private static String CLASS_NAME = WebServiceBookcaseRepository.class.getSimpleName();
    private final Messenger<BookcaseListChanged> mMessenger;
    private final RepositoryAsyncTask mRepositoryAsyncTask = new RepositoryAsyncTask();
    private List<Bookcase> mBookcases;

    @Inject
    public WebServiceBookcaseRepository(Messenger<BookcaseListChanged> messenger) {
        mMessenger = messenger;
    }

    /**
     * Fetch all available bookcase names.
     */
    public List<Bookcase> getAll() {
        return mBookcases;
    }

    /**
     * Populate the repository from its backing store.
     */
    public void loadAll() {
        mRepositoryAsyncTask.execute("https://my-library-8c695.firebaseio.com/bookcases.json?auth=buLUXLsmpEhtVTjwP4LqdNrfI5E5MABD2X30TrWh");
    }

    /**
     * Persist the repository's contents to its backing store. Each {@link Bookcase}
     * object will be either added to or updated in the database, according to whether
     * it was created by user action or originally read from the database.
     */
    public void storeAll() {
//        for (Bookcase b : mBookcases) {
//            if (b.getIsNew()) {
//                addBookcase(b);
//                b.setIsNew(false);
//            }
//            else {
//                updateBookcase(b);
//            }
//        }
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
                url = new URL(params[0]);
            } catch (MalformedURLException mue) {
                Log.e(CLASS_NAME, "Invalid URL " + params[0], mue);
                this.cancel(false);
                return null;
            }

            HttpURLConnection cn = null;
            try {
                Log.d(CLASS_NAME, "connecting to " + params[0]);
                cn = (HttpURLConnection) url.openConnection();
                cn.setRequestProperty("Accept", "application/json");

                Log.d(CLASS_NAME, "streaming from " + params[0]);
                InputStream in = new BufferedInputStream(cn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                Log.d(CLASS_NAME, "reading data from " + params[0]);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }

                return sb.toString();

            } catch (IOException ioe) {
                Log.e(CLASS_NAME, "I/O error reading from " + params[0], ioe);
                this.cancel(false);
                return null;

            } finally {
                if (cn != null)
                    cn.disconnect();
            }
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
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

            if (s == null || s.length() == 0)
                return;

            try {
                mBookcases = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray names = jsonObject.names();
                int objectCount = names.length();

                for (int i = 0; i < objectCount; i++) {
                    JSONObject o = jsonObject.getJSONObject(names.getString(i));

                    Bookcase bc = new Bookcase();
                    bc.setName(o.getString("name"));
                    bc.setLocation(o.getString("location"));
                    bc.setBookcount(o.getInt("bookcount"));

                    mBookcases.add(bc);
                }

                mMessenger.sendMessage(new BookcaseListChanged());

            } catch (JSONException je) {
                Log.e(CLASS_NAME, "Malformed JSON: " + s, je);
            }
        }
    }
}
