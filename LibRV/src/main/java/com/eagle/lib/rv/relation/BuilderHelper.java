package com.eagle.lib.rv.relation;

import android.view.View;

import com.eagle.lib.rv.BindFuck;

/**
 * @author andy
 */
public class BuilderHelper {
    RelationAdapter.Builder builder;
    private Relation relation;

    public BuilderHelper(RelationAdapter.Builder builder) {
        this.builder = builder;
    }

    /**
     * 添加第一条关联
     */
    void addRelation(RelationArray relationArray, int layoutId, int... bindToIds) {
        relation = new Relation();
        relation.bindToIds = bindToIds;
        relationArray.put(layoutId, relation);
    }

    public BuilderHelper addRelation(int layoutId, int... bindToIds) {
        return builder.addRelation(layoutId, bindToIds);
    }

    public BuilderHelper addBindFromKeys(Object... keys) {
        relation.bindKeys = keys;
        return this;
    }

    /**
     * 新增点击事件和 View 之间的关系
     *
     * @param listener 点击事件
     * @param viewIds  listener 需要绑定的 view 的 ids
     * @return this
     */
    public BuilderHelper addClickListener(View.OnClickListener listener, int... viewIds) {
        relation.clickListener = listener;
        relation.eventToIds = viewIds;
        return this;
    }

    public <Data> BuilderHelper addBindCallback(BindFuck<Data> callback) {
        relation.bindFuck = callback;
        return this;
    }

    public <EA extends RelationAdapter> EA build(Class<EA> kind) {
        return builder.build(kind);
    }
}
