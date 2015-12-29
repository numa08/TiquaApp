package net.numa08.tiqavapp;

import net.numa08.tiqavapp.list.TiqavListFragment;
import net.numa08.tiqavapp.modules.ContextModuole;
import net.numa08.tiqavapp.modules.PicassoModule;
import net.numa08.tiqavapp.modules.RealmModule;
import net.numa08.tiqavapp.modules.TiqavModule;
import net.numa08.tiqavapp.service.LoadTiqavService;

import dagger.Component;

@Component(modules = {ContextModuole.class ,RealmModule.class, TiqavModule.class, PicassoModule.class})
public interface ApplicationComponent {
    void inject(LoadTiqavService service);
    void inject(TiqavListFragment fragment);
}
