package net.numa08.tiqavapp.list.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.numa08.tiqa4k.Tiqav;

import io.realm.RealmBaseRecyclerAdapter;
import io.realm.RealmResults;

public class TiqavListAdapter extends RealmBaseRecyclerAdapter<Tiqav, TiqavListViewHolder>{
    private final Picasso picasso;

    public TiqavListAdapter(RealmResults<Tiqav> realmResults, boolean autoRefresh, Picasso picasso) {
        super(realmResults, autoRefresh);
        this.picasso = picasso;
    }

    @Override
    public TiqavListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ImageView imageView = new ImageView(parent.getContext());
        return new TiqavListViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(TiqavListViewHolder holder, int position) {
        final Tiqav tiqav = realmResults.get(position);
        picasso
            .load(tiqav.getSourceURL())
            .into(holder.content);

    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }
}
