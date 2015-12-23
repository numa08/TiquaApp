package net.numa08.tiqavapp.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import net.numa08.tiqavapp.CacheRealmConfigurator;
import net.numa08.tiqavapp.R;
import net.numa08.tiqavapp.service.LoadTiqavService_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

@EFragment
@OptionsMenu(R.menu.fragment_tiqav_list)
public class TiqavListFragment extends Fragment{

    @Bean
    CacheRealmConfigurator cacheRealmConfigurator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadTiqavService_
                .intent(getContext())
                .loadNewest()
                .start();
    }

    @OptionsItem(R.id.reload)
    void reloadTiqav() {
        LoadTiqavService_
                .intent(getContext())
                .loadNewest()
                .start();
    }
}
