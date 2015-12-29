package net.numa08.tiqavapp.modules;

import android.content.Context;

import net.numa08.tiqavapp.TiqavApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModuole {

    @Provides
    Context providesContext() {
        return TiqavApplication.getInstance();
    }
}
