package com.eagle.lib.rv.setup;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.lib.rv.relation.RelationAdapter;

public class GridView extends Setup {

    private int spanCount;
    private int orientation = RecyclerView.VERTICAL;
    ;

    public GridView(RecyclerView recyclerView, int spanCount) {
        super(recyclerView);
        this.spanCount = spanCount;
    }

    @Override
    public RelationAdapter.AdapterCreator setup() {
        layoutManager = getGridManager();
        recyclerView.setLayoutManager(layoutManager);
        return new RelationAdapter.AdapterCreator(recyclerView);
    }

    public Direction.Vertical vertical() {
        this.orientation = GridLayoutManager.VERTICAL;
        return new Direction.Vertical<GridView>(this);
    }

    public Direction.Horizontal horizontal() {
        this.orientation = GridLayoutManager.HORIZONTAL;
        return new Direction.Horizontal<GridView>(this);
    }


    private RecyclerView.LayoutManager getGridManager() {
        return new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, false);
    }


}
