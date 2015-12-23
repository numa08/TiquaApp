package io.realm;

public class RealmFactory {

    public static Realm getInstance(RealmConfiguration configuration) {
        return Realm.getInstance(configuration);
    }

}
