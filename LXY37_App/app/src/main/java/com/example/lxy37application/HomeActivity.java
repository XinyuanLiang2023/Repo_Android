package com.example.lxy37application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private FragmentTabHost tabhost;
    private String texts[] = {"商品主页", "待评价商品"};
    private int imgs[] = {R.drawable.home, R.drawable.person};
    private Class fragments[] = {GoodsFragment.class, MyFragment.class};
    private TextView tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.frag_main);

        tabhost = findViewById(android.R.id.tabhost);
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < 2; i++) {
            TabHost.TabSpec spec = tabhost.newTabSpec(texts[i]).setIndicator(getView(i));
            tabhost.addTab(spec, fragments[i], null);
        }
    }
    private View getView(int i) {
        View view = View.inflate(HomeActivity.this, R.layout.item_tabcontent, null);
        ImageView tabImg = (ImageView) view.findViewById(R.id.tabImg);
        TextView tabText = (TextView) view.findViewById(R.id.tabText);
        tabImg.setImageResource(imgs[i]);
        tabText.setText(texts[i]);
        return view;
    }
}
