package com.example.lxy37application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {


    //
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    // 初始化
    private void initView() {
        mListView = getActivity().findViewById(R.id.lv);
        // 数据适配器
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(), ViewData_Orders.data, R.layout.list_item_orders,
                new String[] {"item_image","item_title","item_price"},
                new int[] {R.id.item_image,R.id.item_title,R.id.item_price});
        mListView.setAdapter(mSimpleAdapter);
        // item上的点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView item_title = view.findViewById(R.id.item_title);
                String title =item_title.getText().toString().trim();
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("lxy37app://com.photo"));
                intent3.putExtra("message", title);
                startActivity(intent3);
            }

        });
    }



}
