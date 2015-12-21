package net.numa08.tiqavapp.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import io.realm.Realm
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavServiceProperty
import net.numa08.tiqavapp.realmConfigurator
import rx.Observer
import rx.schedulers.Schedulers

class LoadTiqavService : IntentService("LoadTiqavService") {

    companion object {
        val ACTION_LOAD = LoadTiqavService::class.java.simpleName + "ACTION_LOAD"
    }

    var tiqavService by TiqavServiceProperty()
    val realmConfig by realmConfigurator()

    var observer = object :Observer<Array<Tiqav>> {
        override fun onCompleted() {}

        override fun onNext(t: Array<Tiqav>?) {
            t?.let { t ->
                Realm.getInstance(realmConfig).use { r ->
                    r.executeTransaction { r ->
                        r.copyToRealmOrUpdate(t.asList())
                    }
                }
            }
        }

        override fun onError(e: Throwable?) {
            Log.e("tiqav", "fail fetch tiqav ", e)
        }

    }

    override public fun onHandleIntent(intent: Intent?) {
        intent?.let { i ->
            if(i.action == ACTION_LOAD) {
                loadNewest()
            }
        }
    }

    fun loadNewest() {
        tiqavService
            .newest()
            .subscribeOn(Schedulers.newThread())
            .subscribe(observer)
    }

}