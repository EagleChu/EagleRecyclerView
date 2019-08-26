package com.eagle.lib.rv;

import android.view.View;

/**
 * @author andy
 */
public interface BindFuck<Data> {
    /**
     * @param data Item 对应的数据
     */
    Object[] bindCall(Data data, View[] views);
}
