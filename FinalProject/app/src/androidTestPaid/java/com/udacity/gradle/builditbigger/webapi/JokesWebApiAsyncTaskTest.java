package com.udacity.gradle.builditbigger.webapi;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class JokesWebApiAsyncTaskTest {

    private static final int TIMEOUT = 20;

    @Test
    public void getJoke() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        JokesWebApiAsyncTask task = new JokesWebApiAsyncTask(new OnJokeReceived() {
            @Override
            public void tellJoke(String joke) {
                countDownLatch.countDown();
                assertTrue(joke.contains("computer"));
            }
        });

        task.execute();

        countDownLatch.await(TIMEOUT, TimeUnit.SECONDS);

        assertEquals(0, countDownLatch.getCount());
    }
}