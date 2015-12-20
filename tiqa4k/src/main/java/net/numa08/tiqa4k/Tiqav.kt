package net.numa08.tiqa4k

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

public open class Tiqav(val id : String,
                        val ext : String,
                        val height : Int,
                        val width : Int,
                        @SerializedName("source_url") val sourceURL : String
                  ) : RealmObject() {
}