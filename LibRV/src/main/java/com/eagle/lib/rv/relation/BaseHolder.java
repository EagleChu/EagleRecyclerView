package com.eagle.lib.rv.relation;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.Map;

/**
 * @author andy
 */
abstract class BaseHolder extends RecyclerView.ViewHolder {
    HolderViews viewArray;

    BaseHolder(View itemView, int[] toIds) {
        super(itemView);
        viewArray = new HolderViews(toIds);
        holderViews(itemView, toIds);
    }

    private void holderViews(View itemView, int[] toIds) {
        for (int viewId : toIds) {
            View view = itemView.findViewById(viewId);
            viewArray.add(viewId, view);
        }
    }

    abstract <Data> void onBind(Data data);

    void onBind(Map map, Object[] keys) {
        Object[] from = new Object[keys.length];
        for (int i = 0; i < keys.length; i++) {
            from[i] = map.get(keys[i]);
        }
        onBind(from);
    }

    void onBind(Object[] from) {

        if (viewArray == null) {
            throw new RuntimeException("没有指定要缓存的View");
        }
        if (from.length != viewArray.size()) {
            throw new RuntimeException("需要绑定的 viewArray 的长度和数据集的长度不同");
        }

        for (int i = 0; i < viewArray.size(); i++) {
            bindDataToView(viewArray.valueAt(i), from[i]);
        }
    }


    /**
     * 绑定数据到相应的View中
     *
     * @param v    绑定的目标
     * @param data 相应的数据
     */
    private void bindDataToView(View v, Object data) {
        String text = data == null ? "" : String.valueOf(data);
        if (v instanceof Checkable) {
            if (data instanceof Boolean) {
                ((Checkable) v).setChecked((Boolean) data);
            } else if (v instanceof TextView) {
                // Note: keep the instanceof TextView check at the bottom of these
                // ifs since a lot of viewArray are TextViews (e.g. CheckBoxes).
                setViewText((TextView) v, text);
            } else {
                throw new IllegalStateException(v.getClass().getName() +
                        " should be bound to a Boolean, not a " +
                        (data == null ? "<unknown type>" : data.getClass()));
            }
        } else if (v instanceof TextView) {
            // Note: keep the instanceof TextView check at the bottom of these
            // ifs since a lot of viewArray are TextViews (e.g. CheckBoxes).
            setViewText((TextView) v, text);
        } else if (v instanceof ImageView) {
            if (data instanceof Integer) {
                setViewImage((ImageView) v, (Integer) data);
            } else if (!TextUtils.isEmpty(text)) {
                setViewImage((ImageView) v, text);
            }
        } else {
            throw new IllegalStateException(v.getClass().getName() + " is not a " +
                    " view that can be bounds by this SingleArrayAdapter");
        }
    }

    protected void setViewImage(ImageView v, String value) {
        try {
            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }

    protected void setViewImage(ImageView v, int value) {
        v.setImageResource(value);
    }

    protected void setViewText(TextView v, String text) {
        v.setText(text);
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    /**
     * @param position {@link #getAdapterPosition()}
     */
    public void setPositionTag(int position) {
        if (itemView.hasOnClickListeners()) {
            itemView.setTag(getItemViewType(), position);
        }
    }
}
