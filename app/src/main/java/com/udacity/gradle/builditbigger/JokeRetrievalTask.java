package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shash.myapplication.backend.jokeApi.JokeApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.smokescreem.shash.androidjokelib.DisplayJokeActivity;

import java.io.IOException;

/**
 * Created by Shash on 4/14/2017.
 */

class JokeRetrievalTask extends AsyncTask<Context, Void, String> {
    private static JokeApi mJokeApi = null;
    private Context context;
    private  ProgressBar progressBar;
    public JokeRetrievalTask(ProgressBar progressBar){
        this.progressBar = progressBar;
    }
    public JokeRetrievalTask(){
        this(null);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected String doInBackground(Context... params) {
        if(mJokeApi == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.0.110:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            mJokeApi = builder.build();
        }

        context = params[0];

        try {
            return mJokeApi.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        startJokeDisplayActivity(result);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void startJokeDisplayActivity(String joke) {
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.INTENT_JOKE, joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}