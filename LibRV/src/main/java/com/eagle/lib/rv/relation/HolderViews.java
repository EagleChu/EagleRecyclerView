package com.eagle.lib.rv.relation;

import android.view.View;

public class HolderViews {

    int[] mKeys;
    View[] values;

    HolderViews(int[] keys) {
        this.mKeys = keys;
        values = new View[keys.length];
    }

    View get(int key) {
        int i = ContainerHelpers.binarySearch(mKeys, mKeys.length, key);
        if (i < 0) {
            return null;
        }
        return values[i];
    }

    View valueAt(int index) {
        return values[index];
    }

    public int keyAt(int index) {
        return mKeys[index];
    }

    public int[] getKeys() {
        return mKeys;
    }

    public View[] getValues() {
        return values;
    }

    void add(int key, View value) {
        int i = ContainerHelpers.binarySearch(mKeys, mKeys.length, key);
        values[i] = value;
    }

    int size() {
        return mKeys.length;
    }
}
