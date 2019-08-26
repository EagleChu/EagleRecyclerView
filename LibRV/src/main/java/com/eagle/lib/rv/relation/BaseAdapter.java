package com.eagle.lib.rv.relation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eagle.lib.rv.ItemEntity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy
 */
public abstract class BaseAdapter<BH extends BaseHolder> extends RecyclerView.Adapter<BH> {

    private List<ItemEntity> entityList;


    /**
     * 和外部的数据进行关联,之后外部数据进行增删改查时,
     * 可以和{@link RelationAdapter#notifyDataSetChanged()}等方法配合使用
     *
     * @param entities 外部的数据
     */
    public void linKData(List<ItemEntity> entities) {
        this.entityList = entities;
        notifyDataSetChanged();
    }

    /**
     * 一次加载数据, 或者说重置数据
     *
     * @param itemType 数据对应的ItemType
     * @param dataList 原始数据
     * @param <Data>   原始数据对应的泛型
     */
    public <Data> void putData(int itemType, List<Data> dataList) {
        this.entityList = new ArrayList<>();
        for (Data data : dataList) {
            ItemEntity entity = new ItemEntity();
            entity.itemType = itemType;
            entity.data = data;
            this.entityList.add(entity);
        }
        notifyDataSetChanged();
    }

    /**
     * 不断的新增一组数据
     *
     * @param itemType 数据对应的ItemType
     * @param dataList 原始数据
     * @param <Data>   原始数据对应的泛型
     */
    public <Data> void addData(int itemType, List<Data> dataList) {
        if (this.entityList == null) {
            this.entityList = new ArrayList<>();
        }

        int start = this.entityList.size();
        int count = dataList.size();

        for (Data data : dataList) {
            ItemEntity entity = new ItemEntity();
            entity.itemType = itemType;
            entity.data = data;
            this.entityList.add(entity);
        }
        notifyItemRangeChanged(start, count);

    }

    /**
     * 不断的新增单条数据
     *
     * @param itemType 数据对应的ItemType
     * @param data     原始数据
     * @param <Data>   原始数据对应的泛型
     */
    public <Data> void addData(int itemType, Data data) {
        int start = this.entityList.size();

        ItemEntity entity = new ItemEntity();
        entity.itemType = itemType;
        entity.data = data;
        this.entityList.add(entity);
        notifyItemChanged(start);
    }

    @Override
    public void onBindViewHolder(@NonNull BH holder, int position) {
        holder.setPositionTag(position);
        ItemEntity entity = entityList.get(position);
        if (entity.data instanceof Object[]) {
            holder.onBind((Object[]) entity.data);
        } else if (entity.data instanceof List) {
            List list = (List) entity.data;
            holder.onBind(list.toArray());
        } else {
            holder.onBind(entity.data);
        }

    }

    @Override
    public int getItemCount() {
        return entityList != null ? entityList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return entityList != null ? entityList.get(position).itemType : super.getItemViewType(position);
    }
}
