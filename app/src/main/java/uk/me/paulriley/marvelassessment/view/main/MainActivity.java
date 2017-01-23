package uk.me.paulriley.marvelassessment.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.me.paulriley.marvelassessment.MarvelAssessmentApplication;
import uk.me.paulriley.marvelassessment.R;
import uk.me.paulriley.marvelassessment.support.utility.CountingIdlingResourceListener;

public class MainActivity extends AppCompatActivity implements MainView {

    private static CountingIdlingResourceListener sIdlingNotificationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MarvelAssessmentApplication) getApplication()).inject(this);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static void setIdlingNotificationListener(CountingIdlingResourceListener idlingNotificationListener) {
        sIdlingNotificationListener = idlingNotificationListener;
    }
}
