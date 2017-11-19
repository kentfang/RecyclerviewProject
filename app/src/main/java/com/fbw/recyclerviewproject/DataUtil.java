package com.fbw.recyclerviewproject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：fbw
 * 邮箱：kent.fang@hotmail.com
 */

public class DataUtil {

    public static List<String> getSimpleData(){
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            datas.add("测试数据" + i);
        }
        return datas;
    }
}
