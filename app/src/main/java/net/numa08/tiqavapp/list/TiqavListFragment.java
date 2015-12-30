package net.numa08.tiqavapp.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.squareup.picasso.Picasso;

import net.numa08.tiqa4k.Tiqav;
import net.numa08.tiqavapp.R;
import net.numa08.tiqavapp.TiqavApplication;
import net.numa08.tiqavapp.list.adapter.TiqavListAdapter;
import net.numa08.tiqavapp.realm.configurator.CacheRealmConfigurator;
import net.numa08.tiqavapp.service.LoadTiqavService_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmFactory;
import io.realm.RealmResults;

@EFragment(R.layout.fragment_tiqav_list)
@OptionsMenu(R.menu.fragment_tiqav_list)
public class TiqavListFragment extends Fragment {

    @ViewById(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    CacheRealmConfigurator cacheRealmConfigurator;
    @Inject
    Picasso picasso;
    Realm cacheRealm;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadTiqavService_
                .intent(getContext())
                .loadNewest()
                .start();
       TiqavApplication.getApplication().getComponent().inject(this);
        cacheRealm = RealmFactory.getInstance(cacheRealmConfigurator.getRealmConfiguration());
    }

    @AfterViews
    void initRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final RealmResults<Tiqav> tiqavs = cacheRealm.allObjects(Tiqav.class);
        final TiqavListAdapter adapter = new TiqavListAdapter(tiqavs, true, picasso);
        recyclerView.setAdapter(adapter);
    }

    @OptionsItem(R.id.reload)
    void reloadTiqav() {
        LoadTiqavService_
                .intent(getContext())
                .loadNewest()
                .start();
    }

    @Override
    public void onDestroy() {
        cacheRealm.close();
        super.onDestroy();
    }
}
