package net.numa08.tiqavapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqavapp.list.TiqaListFragment
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, TiqaListFragment())
            .commit()
    }
}