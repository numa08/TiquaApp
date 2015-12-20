package net.numa08.mock.tiqa4k

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavService
import net.numa08.tiqa4k.TiqavServiceTest
import org.apache.maven.artifact.ant.shaded.IOUtil
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.mock.MockRetrofit
import retrofit.mock.NetworkBehavior
import retrofit.mock.RxJavaBehaviorAdapter
import rx.Observable

class MockNewst(val retrofit: Retrofit,val json : String) : TiqavService {
    override fun newest(): Observable<Array<Tiqav>> {
        val gson = GsonBuilder()
            .setExclusionStrategies(object:ExclusionStrategy {
                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    return f?.declaringClass?.equals(RealmObject::class.java) ?: true
                }
            }).create()
        val tiqavs = gson.fromJson(json, Array<Tiqav>::class.java)
        return Observable.just(tiqavs)
    }
}

object MockTiqavService {

    val ENDPOINT  = "http://api.tiqav.com"

    val newest = {
        val retrofit = Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val stream = TiqavServiceTest::class.java.classLoader.getResourceAsStream("json/newest.json")
        val json = IOUtil.toString(stream)
        stream.close()

        val behavior = NetworkBehavior.create()

        val mockRetrofit = MockRetrofit(behavior, RxJavaBehaviorAdapter.create())
        val mockService = MockNewst(retrofit, json)

        mockRetrofit.create(TiqavService::class.java, mockService)
    }()

}