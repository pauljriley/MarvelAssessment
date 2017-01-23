package uk.me.paulriley.marvelassessment;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import uk.me.paulriley.marvelassessment.injection.modules.ApplicationModule;
import uk.me.paulriley.marvelassessment.injection.modules.PresenterModule;

public class MarvelAssessmentApplication extends ApplicationExtension {
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        installLeakCanary();

        createInitialObjectGraph();
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new ApplicationModule(),
                new PresenterModule(this)
        );
    }

    public void inject(Object object) {
        graph.inject(object);
    }

    public void createInitialObjectGraph() {
        setObjectGraph(ObjectGraph.create(getModules().toArray()));
    }

    public void setObjectGraph(ObjectGraph objectGraph) {
        graph = objectGraph;
    }

    public ObjectGraph getObjectGraph() {
        return graph;
    }

    protected RefWatcher installLeakCanary() {
        return !BuildConfig.BUILD_TYPE.equals("debug")
                ? RefWatcher.DISABLED
                : LeakCanary.install(this);
    }
}
