package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.mdzyuba.jokesandroidlib.JokeTellingActivity;
import com.mdzyuba.jokeslib.Jokes;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String WEB_API_URL = "http://jokes-web-service.appspot.com/_ah/api/";
    private Button btnJokeFromJavaLib;
    private Button btnJokeFromAndroidLib;
    private Button btnJokeFromWebApi;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        initButtonWithLibJokes(root);

        initButtonWithAndroidLibJokes(root);

        initButtonWithWebApiJokes(root);

        initAds(root);

        return root;
    }

    private void initAds(View root) {
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void initButtonWithAndroidLibJokes(View root) {
        btnJokeFromAndroidLib = root.findViewById(R.id.btnTellJoke_androidLib);
        btnJokeFromAndroidLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joke = new Jokes().tell();
                JokeTellingActivity.startActivity(getActivity(), joke);
            }
        });
    }

    private void initButtonWithLibJokes(View root) {
        btnJokeFromJavaLib = root.findViewById(R.id.btnTellJoke_javaLib);
        btnJokeFromJavaLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joke = new Jokes().tell();
                Toast.makeText(getContext(), joke, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initButtonWithWebApiJokes(View root) {
        btnJokeFromJavaLib = root.findViewById(R.id.btnTellJoke_webApi);
        btnJokeFromJavaLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokesWebApiAsyncTask task = new JokesWebApiAsyncTask();
                task.execute();
            }
        });
    }

    private class JokesWebApiAsyncTask extends AsyncTask<Void, Void, String> {
        private MyApi api = null;

        @Override
        protected String doInBackground(Void... voids) {
            try {
                MyApi api = buildWebApi();
                return api.sayHi("Nick").execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String joke) {
            JokeTellingActivity.startActivity(getActivity(), joke);
        }

        private MyApi buildWebApi() {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                                      new AndroidJsonFactory(), null)
                    .setRootUrl(WEB_API_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(
                                AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws
                                                                                            IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            MyApi api = builder.build();
            return api;
        }
    }

}
