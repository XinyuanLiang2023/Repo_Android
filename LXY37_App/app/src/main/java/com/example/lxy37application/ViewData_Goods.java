package com.example.lxy37application;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewData_Goods {
    public static   List<HashMap<String,Object>> data = addData();
    // 获取静态数据
    public static List<HashMap<String,Object>> addData() {
        Map<String, Object> map = new HashMap<>();
        map.put("item_image", R.drawable.apple);
        map.put("item_title","苹果");
        map.put("item_price","￥1.00");

        Map<String, Object> map1 = new HashMap<>();
        map1.put("item_image",R.drawable.cake);
        map1.put("item_title","蛋糕");
        map1.put("item_price","￥10.00");


        Map<String, Object> map2 = new HashMap<>();
        map2.put("item_image",R.drawable.milk);
        map2.put("item_title","牛奶");
        map2.put("item_price","￥5.00");

        List<HashMap<String, Object>> hashMaps = new ArrayList<>();
        hashMaps.add((HashMap<String, Object>) map);
        hashMaps.add((HashMap<String, Object>) map1);
        hashMaps.add((HashMap<String, Object>) map2);


        return  hashMaps;
    }


}

