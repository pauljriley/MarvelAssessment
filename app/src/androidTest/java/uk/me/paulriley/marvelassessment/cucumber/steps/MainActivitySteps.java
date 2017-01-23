package uk.me.paulriley.marvelassessment.cucumber.steps;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dagger.ObjectGraph;
import uk.me.paulriley.marvelassessment.MarvelAssessmentApplication;
import uk.me.paulriley.marvelassessment.cucumber.pages.BasePage;
import uk.me.paulriley.marvelassessment.cucumber.pages.MainPage;
import uk.me.paulriley.marvelassessment.injection.modules.PresenterModule;
import uk.me.paulriley.marvelassessment.utility.ActivityFinisher;
import uk.me.paulriley.marvelassessment.utility.CountingIdlingResourceListenerImpl;
import uk.me.paulriley.marvelassessment.view.main.MainActivity;

import static org.junit.Assert.assertNotNull;

@SuppressWarnings("JUnitTestCaseWithNoTests")
@RunWith(AndroidJUnit4.class)
public class MainActivitySteps {

    private MainActivity mMainActivity;
    private CountingIdlingResourceListenerImpl mCountingIdlingResourceListener;
    private PowerManager.WakeLock mFullWakeUpLock;
    private BasePage mCurrentPage;
    private TestModule mTestModule;

    @Rule
    private ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        mTestModule = new TestModule();

        Context testContext = InstrumentationRegistry.getTargetContext()
                .getApplicationContext();

        MarvelAssessmentApplication application
                = (MarvelAssessmentApplication) testContext;

        application.setObjectGraph(ObjectGraph.create(
                mTestModule,
                new PresenterModule(testContext)));

        registerIdlingResources();
        mMainActivity = mActivityTestRule.launchActivity(new Intent());
        assertNotNull(mMainActivity);
        turnOnScreenOfTestDevice();
    }

    private void registerIdlingResources() {
        mCountingIdlingResourceListener = new CountingIdlingResourceListenerImpl("MainActivityStarter");
        MainActivity.setIdlingNotificationListener(mCountingIdlingResourceListener);
        Espresso.registerIdlingResources(mCountingIdlingResourceListener.getCountingIdlingResource());
    }

    private void turnOnScreenOfTestDevice() {
        final PowerManager powerManager = (PowerManager) mMainActivity.getSystemService(Context.POWER_SERVICE);
        mFullWakeUpLock = powerManager.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "Do Not Dim Screen");
        mFullWakeUpLock.acquire();
    }

    @After
    public void tearDown() throws Exception {
        MainActivity.setIdlingNotificationListener(null);
        if (mCountingIdlingResourceListener != null) {
            Espresso.unregisterIdlingResources(mCountingIdlingResourceListener.getCountingIdlingResource());
        }
        ActivityFinisher.finishOpenActivities();
        letScreenOfTestDeviceTurnOff();
    }

    private void letScreenOfTestDeviceTurnOff() {
        if (mFullWakeUpLock != null) {
            mFullWakeUpLock.release();
        }
    }

    @When("^I open the main activity$")
    public void i_open_the_main_activity() {
        mCurrentPage = new MainPage();
    }

    @Then("^I should see the main activity$")
    public void i_should_see_the_posts_details() {
        mCurrentPage.is(MainPage.class);
    }
}
