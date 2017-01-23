package uk.me.paulriley.marvelassessment.injection.modules;

import dagger.Module;
import uk.me.paulriley.marvelassessment.view.main.MainPresenterImpl;

@Module(
        injects = {
                MainPresenterImpl.class
        },
        complete = false,
        library = true)
public class ApplicationModule {
}
