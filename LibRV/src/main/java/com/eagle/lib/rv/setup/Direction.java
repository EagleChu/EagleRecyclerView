package com.eagle.lib.rv.setup;

import com.eagle.lib.rv.relation.RelationAdapter;

public interface Direction {

    class Vertical<V extends Setup> extends Setup {

        V v;

        public Vertical(V v) {
            this.v = v;
        }

        @Override
        public RelationAdapter.AdapterCreator setup() {
            return v.setup();
        }
    }

    class Horizontal<V extends Setup> extends Setup {
        V v;

        public Horizontal(V v) {
            this.v = v;
        }

        @Override
        public RelationAdapter.AdapterCreator setup() {
            return v.setup();
        }
    }
}
