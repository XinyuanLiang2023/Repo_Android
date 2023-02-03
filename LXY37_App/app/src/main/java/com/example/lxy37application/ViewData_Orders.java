package com.example.lxy37application;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewData_Orders {
    public static   List<HashMap<String,Object>> data = addData();
    // 获取静态数据
    public static List<HashMap<String,Object>> addData() {
        Map<String, Object> map = new HashMap<>();
        map.put("item_image", R.drawable.cola);
        map.put("item_title","可乐");
        map.put("item_price","￥3.00");

        List<HashMap<String, Object>> hashMaps = new ArrayList<>();
        hashMaps.add((HashMap<String, Object>) map);

        return  hashMaps;
    }


}

