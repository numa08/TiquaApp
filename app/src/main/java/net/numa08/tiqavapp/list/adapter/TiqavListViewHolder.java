package net.numa08.tiqavapp.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

class TiqavListViewHolder extends RecyclerView.ViewHolder {
    public final TiqavListRow content;
    public TiqavListViewHolder(TiqavListRow itemView) {
        super(itemView);
        content = itemView;
    }
}
