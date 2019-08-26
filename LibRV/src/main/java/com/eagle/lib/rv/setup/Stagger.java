package com.eagle.lib.rv.setup;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.eagle.lib.rv.relation.RelationAdapter;

public class Stagger extends Setup {

    private int spanCount;
    private int orientation;

    public Stagger(RecyclerView recyclerView, int spanCount) {
        super(recyclerView);
        this.spanCount = spanCount;
    }

    public Direction.Vertical<Stagger> vertical() {
        this.orientation = GridLayoutManager.VERTICAL;
        return new Direction.Vertical<>(this);
    }

    public Direction.Horizontal<Stagger> horizontal() {
        this.orientation = GridLayoutManager.HORIZONTAL;
        return new Direction.Horizontal<>(this);
    }

    @Override
    public RelationAdapter.AdapterCreator setup() {
        layoutManager = getStaggeredManager();
        recyclerView.setLayoutManager(layoutManager);
        return new RelationAdapter.AdapterCreator(recyclerView);
    }


    private RecyclerView.LayoutManager getStaggeredManager() {
        return new StaggeredGridLayoutManager(spanCount, orientation);
    }
}
