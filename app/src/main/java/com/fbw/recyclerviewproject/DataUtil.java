package com.fbw.recyclerviewproject;

import com.fbw.recyclerviewproject.itemhelp.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<String> getSimpleData(){
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            datas.add("测试数据" + i);
        }
        return datas;
    }

    public static List<ItemEntity> getItemEntuty(){
        List<ItemEntity> datas = new ArrayList<>();
        for (int i=0;i<30;i++){
            ItemEntity item = new ItemEntity();
            item.tv = "测试数据"+i;
            item.type = i%3;
            datas.add(item);
        }
        return datas;
    }
}
