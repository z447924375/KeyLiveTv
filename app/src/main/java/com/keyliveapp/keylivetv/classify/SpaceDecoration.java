package com.keyliveapp.keylivetv.classify;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dllo on 16/10/31.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.top = space;
    }

}
