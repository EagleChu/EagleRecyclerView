package com.eagle.lib.rv.setup;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.eagle.lib.rv.relation.RelationAdapter;


/**
 * @author andy
 */
public abstract class Setup {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Context context;

    protected Setup() { }

    public Setup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        context = recyclerView.getContext();
    }

    public Setup hasFixedSize() {
        recyclerView.setHasFixedSize(true);
        return this;
    }

    public abstract RelationAdapter.AdapterCreator setup();

}
