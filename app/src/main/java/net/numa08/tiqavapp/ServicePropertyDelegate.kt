package net.numa08.tiqavapp

import android.content.Context
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.realm.RealmConfiguration
import io.realm.RealmObject
import net.numa08.tiqa4k.TiqavService
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.GsonConverterFactory
import kotlin.reflect.KProperty

class RetrofitLocator {

    companion object Constant {
        val END_POINT = "http://api.tiqav.com"
    }

    var retrofit : Retrofit? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : Retrofit {
        val gson = GsonBuilder()
                    .setExclusionStrategies(object :ExclusionStrategy{
                        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                            return false
                        }

                        override fun shouldSkipField(f: FieldAttributes?): Boolean {
                            return f?.declaringClass?.equals(RealmObject::class) ?: false
                        }

                    })
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return retrofit ?: Retrofit.Builder()
                .baseUrl(END_POINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, retrofit: Retrofit) {
        this.retrofit = retrofit
    }
}

class TiqavServiceLocator(val retrofit: Retrofit) {

    var service : TiqavService? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : TiqavService {
        return service ?: retrofit.create(TiqavService::class.java)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, service: TiqavService) {
        this.service = service
    }
}

class RealmConfigLocator(val context: Context) {
    var realmConfig : RealmConfiguration? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : RealmConfiguration {
        return realmConfig ?: RealmConfiguration.Builder(context.cacheDir).build()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, realmConfig : RealmConfiguration) {
        this.realmConfig = realmConfig
    }
}