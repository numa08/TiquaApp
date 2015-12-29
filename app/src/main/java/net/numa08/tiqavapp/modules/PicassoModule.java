package net.numa08.tiqavapp.modules;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class PicassoModule {

    @Provides
    Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }
}
