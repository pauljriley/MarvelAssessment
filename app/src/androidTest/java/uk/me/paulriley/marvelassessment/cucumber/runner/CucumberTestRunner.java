package uk.me.paulriley.marvelassessment.cucumber.runner;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import uk.me.paulriley.marvelassessment.BuildConfig;

import cucumber.api.android.CucumberInstrumentationCore;

public class CucumberTestRunner extends AndroidJUnitRunner {
    private static final String CUCUMBER_TAGS_KEY = "tags";

    private final CucumberInstrumentationCore instrumentationCore
            = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        final String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            arguments.putString(CUCUMBER_TAGS_KEY, tags.replaceAll(",", "--").replaceAll("\\s",""));
        }

        instrumentationCore.create(arguments);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
