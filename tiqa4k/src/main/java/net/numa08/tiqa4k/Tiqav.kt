package net.numa08.tiqa4k

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmModule

public open class Tiqav(@PrimaryKey public open var id : String = "",
                        public open var ext : String = "",
                        public open var height : Int = 0,
                        public open var width : Int = 0,
                        @SerializedName("source_url") public open var sourceURL : String = ""
                  ) : RealmObject() {}
@RealmModule(library = true, classes = arrayOf(Tiqav::class))
public open class Module{}