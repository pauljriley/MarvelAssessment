package uk.me.paulriley.marvelassessment.injection.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.marvelassessment.MarvelAssessmentApplication;
import uk.me.paulriley.marvelassessment.view.main.MainPresenter;
import uk.me.paulriley.marvelassessment.view.main.MainActivity;
import uk.me.paulriley.marvelassessment.view.main.MainPresenterImpl;

@Module(
        injects = {
                MainActivity.class
        },
        complete = false,
        library = true)
public class PresenterModule {

    private final Context mContext;
    private MainPresenter mMainPresenter;

    public PresenterModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter() {
        if (mMainPresenter == null) {
            mMainPresenter = new MainPresenterImpl((MarvelAssessmentApplication) mContext);
        }

        return mMainPresenter;
    }
}
