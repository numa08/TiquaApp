package net.numa08.tiqavapp.list

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import net.numa08.tiqavapp.service.LoadTiqavService

public class TiqaListFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val intent = Intent(context, LoadTiqavService::class.java)
                .setAction(LoadTiqavService.ACTION_LOAD)
        context.startService(intent)

    }
}