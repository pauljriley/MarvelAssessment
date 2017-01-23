package uk.me.paulriley.marvelassessment.utility;

import android.support.test.espresso.idling.CountingIdlingResource;

import uk.me.paulriley.marvelassessment.support.utility.CountingIdlingResourceListener;

public class CountingIdlingResourceListenerImpl implements CountingIdlingResourceListener {

    private final CountingIdlingResource mCountingIdlingResource;

    public CountingIdlingResourceListenerImpl(final String idlingResourceName) {
        mCountingIdlingResource = new CountingIdlingResource(idlingResourceName);
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return mCountingIdlingResource;
    }

    @Override
    public void increment() {
        mCountingIdlingResource.increment();
    }

    @Override
    public void decrement() {
        mCountingIdlingResource.decrement();
    }
}