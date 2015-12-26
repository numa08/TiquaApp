package net.numa08.tiqavapp;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

public class TiqavApplication extends Application {
    private static TiqavApplication self;

    public static TiqavApplication getInstance() {
        return self;
    }

    @VisibleForTesting
    public ApplicationComponent applicationComponent;

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;

        applicationComponent = DaggerApplicationComponent.create();
    }
}
