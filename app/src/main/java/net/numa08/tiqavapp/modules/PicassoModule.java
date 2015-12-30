package net.numa08.tiqavapp.modules;

import android.content.Context;

import com.squareup.picasso.Picasso;

import net.numa08.tiqavapp.BuildConfig;

import dagger.Module;
import dagger.Provides;

@Module
public class PicassoModule {

    @Provides
    Picasso providePicasso(Context context) {
        final Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(BuildConfig.DEBUG);
        return picasso;
    }
}
