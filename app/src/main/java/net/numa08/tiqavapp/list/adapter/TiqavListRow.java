package net.numa08.tiqavapp.list.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import net.numa08.tiqa4k.Tiqav;
import net.numa08.tiqa4k.UtilsKt;
import net.numa08.tiqavapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_tiqav_list_row)
public class TiqavListRow extends LinearLayout {

    @ViewById(R.id.image)
    ImageView imageView;

    private Tiqav tiqav;
    private Picasso picasso;

    private boolean isInflateFinished;

    public TiqavListRow(Context context) {
        super(context);
        init();
    }

    public TiqavListRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TiqavListRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        isInflateFinished = true;
    }

    public void setPicasso(Picasso picasso) {
        this.picasso = picasso;
    }

    public Tiqav getTiqav() {
        return tiqav;
    }

    public void setTiqav(Tiqav tiqav) {
        this.tiqav = tiqav;
        showTiqav();
    }

    @AfterViews
    void showTiqav() {
        if (!isInflateFinished) {
            return;
        }
        if (picasso == null) {
            return;
        }
        if (tiqav == null) {
            return;
        }
        final String url = UtilsKt.imageURL(tiqav);
        picasso
            .load(url)
            .into(imageView);
    }
}
