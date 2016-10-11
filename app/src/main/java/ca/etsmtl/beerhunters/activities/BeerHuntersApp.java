package ca.etsmtl.beerhunters.activities;

import android.app.Application;

import ca.etsmtl.beerhunters.server.ApiModule;
import ca.etsmtl.beerhunters.server.AppComponent;
import ca.etsmtl.beerhunters.server.DaggerAppComponent;

/**
 * Created by admin on 10/10/2016.
 */
public class BeerHuntersApp extends Application {

    private static BeerHuntersApp sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        appComponent = DaggerAppComponent.builder().apiModule(new ApiModule(this)).build();
    }

    public static BeerHuntersApp getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void setMockMode(ApiModule module) {
        appComponent = DaggerAppComponent.builder().apiModule(module).build();
    }



}
