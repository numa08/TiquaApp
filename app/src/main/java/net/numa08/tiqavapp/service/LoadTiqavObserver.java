package net.numa08.tiqavapp.service;

import net.numa08.tiqa4k.Tiqav;

import rx.Observer;

interface LoadTiqavObserver {

    Observer<Tiqav[]> getObserver();

}
