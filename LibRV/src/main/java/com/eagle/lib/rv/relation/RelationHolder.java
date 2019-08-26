package com.eagle.lib.rv.relation;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.eagle.lib.rv.BindFuck;

import java.util.Map;

/**
 * @author andy
 */
public class RelationHolder extends BaseHolder {
    private BindFuck callback;
    private Object[] fromKeys;
    private SparseArray<View> eventViews;

    protected RelationHolder(View itemView, BindFuck callback, Object[] fromKeys, int[] toIds) {
        super(itemView, toIds);
        this.callback = callback;
        this.fromKeys = fromKeys;
    }

    @SuppressWarnings("unchecked")
    @Override
    <Data> void onBind(Data data) {
        if (data instanceof Map && fromKeys != null) {
            onBind(((Map) data), fromKeys);
        } else {
            Object[] from = callback.bindCall(data, viewArray.values);
            if (from != null) {
                onBind(from);
            }
        }
    }

    public View findEventView(int viewId) {
        if (eventViews == null) {
            eventViews = new SparseArray<>();
        }
        View view = viewArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
        }
        eventViews.put(viewId, view);
        return view;
    }

    @Override
    public void setPositionTag(int position) {
        super.setPositionTag(position);
        if (eventViews != null && eventViews.size() > 0) {
            for (int i = 0; i < eventViews.size(); i++) {
                eventViews.valueAt(i).setTag(getItemViewType(), position);
            }
        }
    }
}
