package com.keyliveapp.keylivetv.baseclass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/11/10.
 */

public abstract class BaseExpandableAdapter<GH extends RecyclerView.ViewHolder, CH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    private static final int TYPE_GROUP = -1;
    private static final int TYPE_CHILD = 1;
    private final SparseIntArray types;
    private final SparseIntArray groups;
    private final SparseIntArray children;

    public BaseExpandableAdapter() {
        types = new SparseIntArray();
        groups = new SparseIntArray();
        children = new SparseIntArray();

    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup(final GridLayoutManager manager) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return types.get(position) == TYPE_GROUP ? manager.getSpanCount() : 1;
            }
        };
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("BaseExpandableAdapter", "++index:" + ++index);

        switch (viewType) {
            case TYPE_GROUP:
                return createGroupHolder(parent);
            case TYPE_CHILD:
                return createChildHolder(parent);
        }
        return null;
    }

    protected abstract GH createGroupHolder(ViewGroup parent);

    protected abstract CH createChildHolder(ViewGroup parent);

    int index;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int groupPosition = groups.get(position);
        int childPosition = children.get(position);
        switch (viewType) {
            case TYPE_GROUP:
                GH groupHolder = (GH) holder;
                onBindGroupHolder(groupHolder, groupPosition);
                break;
            case TYPE_CHILD:
                CH childHolder = (CH) holder;
                onBindChildHolder(childHolder, groupPosition, childPosition);
                break;
        }
    }

    protected abstract void onBindGroupHolder(GH groupHolder, int groupPosition);

    protected abstract void onBindChildHolder(CH childHolder, int groupPosition, int childPosition);

    @Override
    public int getItemCount() {
        return mTotalCount;
    }

    protected int mTotalCount;

    protected int getItemTotalCount() {
        int count = 0;
        int position = 0;
        int groupCount = getGroupCount();
        int group = 0;
        for (int i = 0; i < groupCount; i++) {
            groups.put(position, group);
            types.put(position++, TYPE_GROUP);
            int childCount = getChildCount(i);
            int child = 0;
            for (int j = 0; j < childCount; j++) {
                children.put(position, child++);
                groups.put(position, group);
                position++;
            }
            group++;
            count += childCount;
        }
        count += groupCount;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position) == TYPE_GROUP ? TYPE_GROUP : TYPE_CHILD;
    }

    public abstract Object getGroupItem(int groupPosition);

    public abstract Object getChildItem(int groupPosition, int childPosition);

    protected abstract int getGroupCount();

    protected abstract int getChildCount(int groupPosition);
}