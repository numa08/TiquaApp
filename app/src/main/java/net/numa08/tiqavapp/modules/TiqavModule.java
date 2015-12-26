package net.numa08.tiqavapp.modules;

import com.google.gson.Gson;

import net.numa08.tiqa4k.TiqavGson;
import net.numa08.tiqa4k.TiqavService;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class TiqavModule {
    public static final String END_POINT = "http://api.tiqav.com/";

    final Gson gson = TiqavGson.GSON;
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(END_POINT)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @Provides
    public Retrofit provideRetrofit() {
        return retrofit;
    }

    @Provides
    public TiqavService provideTiqavService(Retrofit retrofit) {
        return retrofit.create(TiqavService.class);
    }
}
