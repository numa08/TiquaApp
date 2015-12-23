package net.numa08.tiqavapp;

import android.content.Context;

import net.numa08.tiqa4k.Module;

import org.androidannotations.annotations.EBean;

import io.realm.RealmConfiguration;

@EBean
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
