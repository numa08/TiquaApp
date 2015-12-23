package net.numa08.tiqavapp.service;

import android.util.Log;

import net.numa08.tiqa4k.Tiqav;
import net.numa08.tiqavapp.CacheRealmConfigurator;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Arrays;

import io.realm.Realm;
import rx.Observer;

@EBean
public class LoadTiqavObserverImpl implements LoadTiqavObserver {

    @Bean
    CacheRealmConfigurator cacheRealmConfigurator;

    @Override
    public Observer<Tiqav[]> getObserver() {
        return new Observer<Tiqav[]>() {
            @Override
            public void onCompleted() {
                Log.d("tiqav", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tiqav", "onError " , e);
            }

            @Override
            public void onNext(Tiqav[] tiqavs) {
                final Realm realm = Realm.getInstance(cacheRealmConfigurator.getRealmConfiguration());
                realm.executeTransaction(r -> r.copyToRealmOrUpdate(Arrays.asList(tiqavs)));
                realm.close();
                Log.d("tiqav", "save tiqav");
            }
        };
    }
}
