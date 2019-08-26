package com.eagle.lib.rv.relation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.lib.rv.BindFuck;

/**
 * @author andy
 */
public class RelationAdapter extends BaseAdapter<RelationHolder> {
    private RelationArray relationArray;

    public void setRelationArray(RelationArray relationArray) {
        this.relationArray = relationArray;
    }

    @NonNull
    @Override
    public RelationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1, inflate ItemView from ViewType(viewType = layoutId)
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        BindFuck bindFuck = relationArray.get(viewType).bindFuck;
        int[] bindToIds = relationArray.get(viewType).bindToIds;
        int[] eventToIds = relationArray.get(viewType).eventToIds;
        Object[] bindKeys = relationArray.get(viewType).bindKeys;
        View.OnClickListener listener = relationArray.get(viewType).clickListener;

        RelationHolder holder = new RelationHolder(itemView, bindFuck, bindKeys, bindToIds);
        if (listener != null && eventToIds.length > 0) {
            for (int viewId : eventToIds) {
                holder.findEventView(viewId).setOnClickListener(listener);
            }
        } else {
            holder.setOnItemClickListener(listener);
        }

        return holder;
    }

    public static class Builder {
        private RecyclerView recyclerView;
        private RelationArray relationArray;

        Builder with(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            relationArray = new RelationArray();
            return this;
        }

        /**
         * @param layoutId  Item 的 Layout Id
         * @param bindToIds 参与数据绑定的 View Id
         * @return #Buidler.this
         */
        public BuilderHelper addRelation(int layoutId, int... bindToIds) {
            BuilderHelper lastHelper = new BuilderHelper(this);
            lastHelper.addRelation(relationArray, layoutId, bindToIds);
            return lastHelper;
        }

        public <EA extends RelationAdapter> EA build(Class<EA> kind) {
            EA adapter = null;
            try {
                adapter = kind.newInstance();
                adapter.setRelationArray(this.relationArray);
                recyclerView.setAdapter(adapter);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return adapter;
        }
    }

    public static class AdapterCreator {
        private RecyclerView recyclerView;

        public AdapterCreator(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        public RelationAdapter.Builder adapterBuilder() {
            return new RelationAdapter.Builder().with(recyclerView);
        }
    }
}
