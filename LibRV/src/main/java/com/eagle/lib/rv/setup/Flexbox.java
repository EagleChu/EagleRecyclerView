package com.eagle.lib.rv.setup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.eagle.lib.rv.relation.RelationAdapter;

public class Flexbox extends Setup {
    public Flexbox(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public RelationAdapter.AdapterCreator setup() {
        layoutManager = new FlexboxLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        return new RelationAdapter.AdapterCreator(recyclerView);
    }

}
