package com.eagle.lib.rv.setup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.lib.rv.relation.RelationAdapter;

/**
 * @author andy
 */
public class ListView extends Setup {

    private int orientation = RecyclerView.VERTICAL;

    public ListView(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public RelationAdapter.AdapterCreator setup() {
        layoutManager = getLinearManager();
        recyclerView.setLayoutManager(layoutManager);
        return new RelationAdapter.AdapterCreator(recyclerView);
    }

    public Direction.Vertical<ListView> vertical() {
        orientation = LinearLayoutManager.VERTICAL;
        return new Direction.Vertical<>(this);
    }

    public Direction.Horizontal horizontal() {
        orientation = LinearLayoutManager.HORIZONTAL;
        return new Direction.Horizontal<>(this);
    }


    private RecyclerView.LayoutManager getLinearManager() {
        return new LinearLayoutManager(recyclerView.getContext(), orientation, false);
    }

}
