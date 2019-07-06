package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mdzyuba.jokesandroidlib.JokeTellingActivity;
import com.mdzyuba.jokeslib.Jokes;
import com.udacity.gradle.builditbigger.webapi.JokesWebApiAsyncTask;
import com.udacity.gradle.builditbigger.webapi.OnJokeReceived;

import androidx.fragment.app.Fragment;

public abstract class MainActivityBaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        initButtonWithLibJokes(root);

        initButtonWithAndroidLibJokes(root);

        initButtonWithWebApiJokes(root);

        return root;
    }

    private void initButtonWithAndroidLibJokes(View root) {
        Button btnJokeFromAndroidLib = root.findViewById(R.id.btnTellJoke_androidLib);
        btnJokeFromAndroidLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joke = new Jokes().tell();
                showJokeTellingActivity(joke);
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
                        showJokeTellingActivity(joke);
                    }
                });
                task.execute();
            }
        });
    }

    /**
     * This method is overwritten in the Free app version to display an interstitial ad.
     *
     * @param joke a joke to be displayed.
     */
    protected void showJokeTellingActivity(String joke) {
        JokeTellingActivity.startActivity(getActivity(), joke);
    }

}