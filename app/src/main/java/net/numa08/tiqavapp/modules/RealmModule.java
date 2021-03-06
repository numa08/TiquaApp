package net.numa08.tiqavapp.modules;

import android.content.Context;

import net.numa08.tiqavapp.realm.configurator.CacheRealmConfigurator;

import dagger.Module;
import dagger.Provides;

@Module
public class RealmModule {

    @Provides
    CacheRealmConfigurator cacheRealmConfiguration(Context context) {
        return new CacheRealmConfigurator(context);
    }
}
