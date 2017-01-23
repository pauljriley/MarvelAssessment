package uk.me.paulriley.marvelassessment.cucumber.pages;

import uk.me.paulriley.marvelassessment.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MainPage extends BasePage {
    public MainPage() {
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()));
    }
}
