package com.bit2016.myapp.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.bit2016.myapp.R;

public class MainActivity extends AppCompatActivity {

    private  MainTabsAdapter mainTabsAdapter;
    private  int indexTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //importclass 로 추가 따로 만들었으므로

        indexTab = MainTabsConfig.TABINDEX.FIRST;

        mainTabsAdapter = new MainTabsAdapter(this, (TabHost)findViewById(android.R.id.tabhost),
                (ViewPager)findViewById(R.id.pager));
        if(indexTab != MainTabsConfig.TABINDEX.FIRST){
            mainTabsAdapter.selectTab(indexTab);
        }
    }
}
