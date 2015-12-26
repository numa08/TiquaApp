package net.numa08.tiqavapp.service;

import android.util.Log;

import net.numa08.tiqa4k.Tiqav;
import net.numa08.tiqa4k.TiqavService;
import net.numa08.tiqavapp.TiqavApplication;
import net.numa08.tiqavapp.realm.configurator.CacheRealmConfigurator;

import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.api.support.app.AbstractIntentService;

import java.util.Arrays;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmFactory;
import rx.Observer;

@EIntentService
public class LoadTiqavService extends AbstractIntentService{

    @Inject
    TiqavService tiqavService;
    @Inject
    CacheRealmConfigurator cacheRealmConfigurator;


    public LoadTiqavService() {
        super("LoadTiqavService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TiqavApplication.getInstance().getComponent().inject(this);
    }

    @ServiceAction
    void loadNewest() {
        tiqavService
                .newest()
                .subscribe(new Observer<Tiqav[]>() {
                    @Override
                    public void onCompleted() {
                        Log.d("tiqav", "complete TiqavService#newest");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tiqav", "catch error TiqavService#newest", e);
                    }

                    @Override
                    public void onNext(Tiqav[] tiqavs) {
                        final Realm realm = RealmFactory.getInstance(cacheRealmConfigurator.getRealmConfiguration());
                        realm.executeTransaction(r -> r.copyToRealmOrUpdate(Arrays.asList(tiqavs)));
                        realm.close();
                    }
                });
    }
}
