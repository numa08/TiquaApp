package io.realm;

import android.support.v7.widget.RecyclerView;

public abstract class RealmBaseRecyclerAdapter<T extends RealmObject, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected RealmResults<T> realmResults;
    private final RealmChangeListener listener;

    public RealmBaseRecyclerAdapter(RealmResults<T> realmResults, boolean autoRefresh) {
        this.listener = !autoRefresh?null: this::notifyDataSetChanged;
        if (listener != null && realmResults != null) {
            realmResults.realm.handlerController.addChangeListenerAsWeakReference(listener);
        }
    }
}
