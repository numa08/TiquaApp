package net.numa08.tiqavapp;

import android.support.v7.app.AppCompatActivity;

import net.numa08.tiqavapp.list.TiqavListFragment;
import net.numa08.tiqavapp.list.TiqavListFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import io.realm.Realm;
import io.realm.RealmFactory;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{

    @Bean
    CacheRealmConfigurator realmConfigurator;
    Realm realm;

    @AfterInject
    void initRealm() {
        realm = RealmFactory.getInstance(realmConfigurator.getRealmConfiguration());
    }

    @AfterViews
    void showListFragment() {
        final TiqavListFragment fragment = TiqavListFragment_.builder().build();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
