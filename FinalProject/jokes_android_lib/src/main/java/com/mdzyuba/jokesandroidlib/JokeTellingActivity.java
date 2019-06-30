package com.mdzyuba.jokesandroidlib;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeTellingActivity extends AppCompatActivity {

    public static final String JOKE = "joke";

    private TextView tvJoke;

    public static void startActivity(Context context, String joke) {
        Intent intent = new Intent(context, JokeTellingActivity.class);
        intent.putExtra(JOKE, joke);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvJoke = findViewById(R.id.tvJokeText);
        String joke = getIntent().getStringExtra(JOKE);
        if (joke != null) {
            tvJoke.setText(joke);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // This is a default case for the up button.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
