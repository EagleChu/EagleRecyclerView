package com.eagle.lib.rv.rvs;

import androidx.recyclerview.widget.RecyclerView;

public class Proxy {
    RecyclerView recyclerView;

    public Proxy(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public LayoutSetter layoutSetter() {
        return new LayoutSetter();
    }

    public AdapterSetter adapterSetter() {
        return new AdapterSetter();
    }

    public DecorationAdder decorationBuilder() {
        return new DecorationAdder();
    }

    public ItemTouchAdder itemTouchBuilder() {
        return new ItemTouchAdder();
    }

    public ScrollAdder scrollAdder() {
        return new ScrollAdder();
    }

    public class AdapterSetter {

    }

    public class LayoutSetter {

    }

    public class DecorationAdder {

    }

    public class ItemTouchAdder {

    }

    public class ScrollAdder {

    }
}
