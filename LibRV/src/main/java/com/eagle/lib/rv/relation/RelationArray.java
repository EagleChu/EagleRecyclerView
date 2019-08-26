package com.eagle.lib.rv.relation;

import android.util.SparseArray;

public class RelationArray {

    private SparseArray<Relation> relationArray;

    public RelationArray() {
        relationArray = new SparseArray<>();
    }

    public Relation get(int itemType) {
        return relationArray.get(itemType);
    }

    public void put(int itemType, Relation value) {
        relationArray.put(itemType, value);

    }
}
