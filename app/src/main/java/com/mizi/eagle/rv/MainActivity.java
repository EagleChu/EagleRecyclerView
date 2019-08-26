package com.mizi.eagle.rv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.eagle.lib.rv.BindFuck;
import com.eagle.lib.rv.setup.RV;
import com.eagle.lib.rv.relation.RelationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        final RelationAdapter adapter = RV.listView(recyclerView)
                .vertical()
                .setup()
                .adapterBuilder()
                // 第一组 ItemType
                .addRelation(R.layout.list_item_2, R.id.tvTitle, R.id.tvDesc)
                .addClickListener(this)
                .addBindCallback(new BindFuck<SimpleBean>() {
                    @Override
                    public Object[] bindCall(SimpleBean data, View[] views) {
                        return new Object[]{data.title, data.description};
                    }
                })
                // 第二组 ItemType
                .addRelation(R.layout.list_item_1, R.id.tvTitle)
                .addBindFromKeys("text1")
                // 第三组 ItemType
                .addRelation(R.layout.list_item_3, R.id.tvTitle, R.id.tvDesc, R.id.ivCover)
                .addBindCallback(new BindFuck<SimpleBean>() {
                    @Override
                    public Object[] bindCall(SimpleBean data, View[] views) {
                        return new Object[]{data.title, data.description, R.mipmap.ic_launcher};
                    }
                })
                // 第四组 ItemType
                .addRelation(R.layout.list_item_4, R.id.tvTitle, R.id.tvDesc, R.id.ivCover)
                .addClickListener(this, R.id.rlItemView, R.id.button)
                .addBindCallback(new BindFuck<SimpleBean>() {
                    @Override
                    public Object[] bindCall(SimpleBean data, View[] views) {
                        ImageView imageView = (ImageView) views[2];
                        GlideApp.with(MainActivity.this).load("http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg").into(imageView);
                        return new Object[]{data.title, data.description, null};
                    }
                })
                .build(RelationAdapter.class);


        List<SimpleBean> beanList = new ArrayList<>();
        for (
                int i = 0;
                i < 20; i++) {
            SimpleBean bean = new SimpleBean();
            bean.title = "Title " + i + i;
            bean.description = "Desc " + i + " description" + i;
            beanList.add(bean);
        }
        adapter.putData(R.layout.list_item_2, beanList);

        List<Map> mapList = new ArrayList<>();
        for (
                int i = 0;
                i < 20; i++) {
            Map<String, String> map = new ArrayMap<>();
            map.put("text1", " Item " + (i * i));
            mapList.add(map);
        }
        adapter.addData(R.layout.list_item_1, mapList);

        adapter.addData(R.layout.list_item_3, beanList);

        adapter.addData(R.layout.list_item_4, beanList);
    }

    @Override
    public void onClick(View v) {
        Object temp = v.getTag(R.layout.list_item_2);
        int position;
        if (temp != null) {
            position = (int) temp;
            ToastUtils.showShort("点击了ItemType2的position " + position);
        }
        temp = v.getTag(R.layout.list_item_4);
        if (temp != null) {
            position = (int) temp;
            if (v.getId() == R.id.button) {
                ToastUtils.showShort("点击了ItemType4的position " + position + " 的 Button");
            }
            if (v.getId() == R.id.rlItemView) {
                ToastUtils.showShort("点击了ItemType4的position " + position + " 的 ItemView");
            }
        }
    }

    static class SimpleBean {
        String title;
        String description;
    }
}
