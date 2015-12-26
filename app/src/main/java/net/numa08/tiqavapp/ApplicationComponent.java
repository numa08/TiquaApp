package net.numa08.tiqavapp;

import net.numa08.tiqavapp.modules.RealmModule;
import net.numa08.tiqavapp.modules.TiqavModule;
import net.numa08.tiqavapp.service.LoadTiqavService;

import dagger.Component;

@Component(modules = {RealmModule.class, TiqavModule.class})
public interface ApplicationComponent {
    void inject(LoadTiqavService service);
}
