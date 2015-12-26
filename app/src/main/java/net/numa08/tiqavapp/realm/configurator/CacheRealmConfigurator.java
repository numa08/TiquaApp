package net.numa08.tiqavapp.realm.configurator;

import android.content.Context;

import net.numa08.tiqa4k.Module;

import io.realm.RealmConfiguration;

public class CacheRealmConfigurator {
    private final RealmConfiguration realmConfiguration;

    public CacheRealmConfigurator(Context context) {
        realmConfiguration = new RealmConfiguration.Builder(context.getCacheDir())
                .setModules(new Module())
                .build();
    }

    public RealmConfiguration getRealmConfiguration() {
        return realmConfiguration;
    }

}
