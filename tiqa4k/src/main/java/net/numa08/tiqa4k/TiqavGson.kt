package net.numa08.tiqa4k

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.realm.RealmObject

object TiqavGson {
    @JvmField
    val GSON = GsonBuilder()
            .setExclusionStrategies(object: ExclusionStrategy{
                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }

                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    return f?.declaringClass?.equals(RealmObject::class.java) ?: false
                }

            }).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
}