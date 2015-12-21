package net.numa08.tiqa4k

import com.google.gson.*
import io.realm.RealmObject
import retrofit.Retrofit
import retrofit.GsonConverterFactory
import retrofit.RxJavaCallAdapterFactory
import kotlin.reflect.KProperty

public class TiqavServiceProperty(val retrofit: Retrofit = {
    val gson = GsonBuilder()
        .setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return  f?.declaringClass?.equals(RealmObject::class.java) ?: false
            }
        })
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
    Retrofit.Builder()
        .baseUrl(TiqavServiceProperty.END_POINT)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}()) {
    companion object{
        val END_POINT = "http://api.tiqav.com"
    }
    var tiqavService: TiqavService? = null

    operator public fun getValue(thisRef: Any?, property: KProperty<*>) : TiqavService {
        return tiqavService ?: retrofit.create(TiqavService::class.java)
    }

    operator public fun setValue(thisRef: Any?, property: KProperty<*>, value: TiqavService) {
        tiqavService = value
    }
}