package net.numa08.tiqavapp.service;

import net.numa08.tiqavapp.TiqavServiceConfiguration;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.api.support.app.AbstractIntentService;

import rx.schedulers.Schedulers;

@EIntentService
public class LoadTiqavService extends AbstractIntentService{

    @Bean
    TiqavServiceConfiguration tiqavServiceConfiguration;
    @Bean(LoadTiqavObserverImpl.class)
    LoadTiqavObserver loadTiqavObserver;


    public LoadTiqavService() {
        super("LoadTiqavService");
    }

    @ServiceAction
    void loadNewest() {
        tiqavServiceConfiguration
                .getService()
                .newest()
                .observeOn(Schedulers.newThread())
                .subscribe(loadTiqavObserver.getObserver());
    }
}
