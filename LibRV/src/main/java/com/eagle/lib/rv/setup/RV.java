package com.eagle.lib.rv.setup;


import androidx.recyclerview.widget.RecyclerView;

/**
 * @author andy
 */
public class RV {

    public static ListView listView(RecyclerView recyclerView) {
        return new ListView(recyclerView);
    }

    public static GridView gridView(RecyclerView recyclerView, int spanCount) {
        return new GridView(recyclerView, spanCount);
    }

    public static Stagger staggeredGrid(RecyclerView recyclerView, int spanCount) {
        return new Stagger(recyclerView, spanCount);
    }

    public static Flexbox flexbox(RecyclerView recyclerView) {
        return new Flexbox(recyclerView);
    }
}
