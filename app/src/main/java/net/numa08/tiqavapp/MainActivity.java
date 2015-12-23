package net.numa08.tiqavapp;

import android.support.v7.app.AppCompatActivity;

import net.numa08.tiqavapp.list.TiqavListFragment;
import net.numa08.tiqavapp.list.TiqavListFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{

    @AfterViews
    void showListFragment() {
        final TiqavListFragment fragment = TiqavListFragment_.builder().build();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
