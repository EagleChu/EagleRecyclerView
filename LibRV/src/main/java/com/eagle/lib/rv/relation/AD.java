package com.eagle.lib.rv.relation;

import androidx.recyclerview.widget.RecyclerView;


public class AD {
    public static RelationAdapter.Builder adapterBuilder(RecyclerView recyclerView) {
        return new RelationAdapter.Builder().with(recyclerView);
    }
}
