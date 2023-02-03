package com.example.lxy37application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GoodsFragment extends Fragment {
    private static Handler handler = new Handler();
    private static TextView labelView = null;
    private static String randomTime;

    public static void UpdateGUI(String refreshTime) {
        randomTime = refreshTime;
        handler.post(RefreshLable);
    }

    private static Runnable RefreshLable = new Runnable() {
        @Override
        public void run() {
            labelView.setText(randomTime);
        }
    };

    //
    private ListView mListView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);//这句没有菜单就出不来

        return inflater.inflate(R.layout.frag_goods, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        labelView = (TextView) view.findViewById(R.id.label);
        //Button startButton = (Button) view.findViewById(R.id.start);
        //Button stopButton = (Button) view.findViewById(R.id.stop);
        final Intent serviceIntent = new Intent(this.getActivity(), TimeThread.class);
        getActivity().startService(serviceIntent);
        /*
        startButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                getActivity().startService(serviceIntent);
            }
        });
        stopButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                getActivity().stopService(serviceIntent);
            }
        });
*/
        initView();


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {
            case R.id.main_menu_1_0:
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("lxy37app://com.call"));
                startActivity(intent3);
                return true;
            case R.id.main_menu_2_0:
                getActivity().finish();
                return true;
        }
        return false;
    }
    // 初始化
    private void initView() {
        mListView = getActivity().findViewById(R.id.listview);

        // 数据适配器
        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this.getActivity(), ViewData_Goods.data, R.layout.list_item_goods,
                new String[]{"item_image", "item_title", "item_price"},
                new int[]{R.id.item_image, R.id.item_title, R.id.item_price});

        mListView.setAdapter(mSimpleAdapter);

        // item上的点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent("com.example.lxy37application");
                intent.putExtra("message", "已加入购物车");
                intent.setPackage(getActivity().getPackageName());
                getActivity().sendBroadcast(intent);
            }

        });
    }
}
