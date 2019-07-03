package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mdzyuba.jokesandroidlib.JokeTellingActivity;
import com.mdzyuba.jokeslib.Jokes;
import com.udacity.gradle.builditbigger.webapi.JokesWebApiAsyncTask;
import com.udacity.gradle.builditbigger.webapi.OnJokeReceived;

import androidx.fragment.app.Fragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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
        Button btnJokeFromAndroidLib = root.findViewById(R.id.btnTellJoke_androidLib);
        btnJokeFromAndroidLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joke = new Jokes().tell();
                JokeTellingActivity.startActivity(getActivity(), joke);
            }
        });
    }

    private void initButtonWithLibJokes(View root) {
        Button btnJokeFromJavaLib = root.findViewById(R.id.btnTellJoke_javaLib);
        btnJokeFromJavaLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joke = new Jokes().tell();
                Toast.makeText(getContext(), joke, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initButtonWithWebApiJokes(View root) {
        Button btnJokeFromWebApi = root.findViewById(R.id.btnTellJoke_webApi);
        final ProgressBar progressBar = root.findViewById(R.id.progress_circular);
        btnJokeFromWebApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                JokesWebApiAsyncTask task = new JokesWebApiAsyncTask(new OnJokeReceived() {
                    @Override
                    public void tellJoke(String joke) {
                        progressBar.setVisibility(View.GONE);
                        JokeTellingActivity.startActivity(getActivity(), joke);
                    }
                });
                task.execute();
            }
        });
    }

}
