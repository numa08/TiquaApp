package net.numa08.tiqavapp;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import net.numa08.tiqavapp.modules.ContextModule;

public class TiqavApplication extends Application {
    private static TiqavApplication self;

    public static TiqavApplication getApplication() {
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
        applicationComponent = DaggerApplicationComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
