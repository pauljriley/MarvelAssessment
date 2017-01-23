package uk.me.paulriley.marvelassessment.cucumber.steps;

import org.mockito.MockitoAnnotations;

import dagger.Module;
import uk.me.paulriley.marvelassessment.injection.modules.ApplicationModule;
import uk.me.paulriley.marvelassessment.view.main.MainPresenterImpl;

@Module(includes = {
            ApplicationModule.class
        },
        injects = {
                MainPresenterImpl.class
        },
        overrides = true,
        complete = false
)
class TestModule {

    public TestModule() {
        MockitoAnnotations.initMocks(this);
   }
}