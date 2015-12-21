package net.numa08.tiqavapp

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import net.numa08.tiqa4k.Module
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.TiqavModule
import kotlin.reflect.KProperty

public fun Context.realmConfigurator() : Injector<RealmConfiguration> {
    val ctx = this
    return object:Injector<RealmConfiguration> {
        operator override fun getValue(thisRef: Any, prop: KProperty<*>): RealmConfiguration {
            return RealmConfiguration.Builder(ctx.codeCacheDir).setModules(Realm.getDefaultModule(), Module()).build()
        }
    }
}