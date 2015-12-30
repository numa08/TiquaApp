package io.realm

public fun initHandlerController(realm: Realm) {
    realm.handlerController = HandlerController(realm)
}

public fun setRealmForResult(result :RealmResults<*>, realm: Realm) {
    result.realm = realm
}