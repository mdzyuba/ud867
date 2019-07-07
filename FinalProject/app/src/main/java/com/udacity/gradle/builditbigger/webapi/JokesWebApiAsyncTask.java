package com.udacity.gradle.builditbigger.webapi;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.BuildConfig;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokesWebApiAsyncTask extends AsyncTask<Void, Void, String> {
    private final OnJokeReceived jokeReceivedCallback;

    public JokesWebApiAsyncTask(OnJokeReceived jokeReceivedCallback) {
        this.jokeReceivedCallback = jokeReceivedCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            return getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        jokeReceivedCallback.tellJoke(joke);
    }

    private String getJoke() throws IOException {
        MyApi api = buildWebApi();
        return api.tellJoke().execute().getData();
    }

    private MyApi buildWebApi() {
        GoogleClientRequestInitializer initializer = new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                abstractGoogleClientRequest.setDisableGZipContent(true);
            }
        };
        Log.d("JokesWebApiAsyncTask", "WEB_API_URL: " + BuildConfig.WEB_API_URL);
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                                  new AndroidJsonFactory(), null)
                        .setRootUrl(BuildConfig.WEB_API_URL)
                        .setGoogleClientRequestInitializer(initializer);
        return builder.build();
    }
}
