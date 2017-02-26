package com.dealmate.xiaohui.dealmate.activity;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.config.CategoryEnum;
import com.dealmate.xiaohui.dealmate.config.CategoryMap;
import com.dealmate.xiaohui.dealmate.fragment.DealListFragment;

import java.util.List;

public class DealListActivity extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener{
    // declare views
    private NavigationView navigationMenu;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private int currentItemId = R.id.computers;
    private CategoryEnum categoryEnum = CategoryEnum.COMPUTER;

    FragmentManager fragmentManager;
    DealListFragment dealListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigationdrawer);
        initView();
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationMenu = (NavigationView) findViewById(R.id.nav_view);
        tabLayout = (TabLayout) findViewById(R.id.submenuTab);

        navigationMenu.setNavigationItemSelectedListener(this);
        actionBar();
        addFragment();
        tabLayout();
        dealListFragment.invokeCategory(categoryEnum, CategoryMap.getCategory(categoryEnum.toString()).get(0));
    }

    @Override
    protected void actionBar() {
        super.actionBar();
        toolbar.setTitle(getResources().getString(R.string.menu_computer));
        toolbar.setNavigationIcon(R.drawable.option);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void tabLayout() {
        String key = categoryEnum.toString();
        List<String> list = CategoryMap.getCategory(key);
        tabLayout.removeAllTabs();
        for(String value : list)
            tabLayout.addTab(tabLayout.newTab().setText(value));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dealListFragment.invokeCategory(categoryEnum, tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addFragment() {
        String listFragmentTag = "list fragment";
        fragmentManager = getSupportFragmentManager();
        dealListFragment = new DealListFragment();
        fragmentManager.beginTransaction().add(R.id.deal_fragment_holder,dealListFragment,
                listFragmentTag).commit();
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        drawerLayout.closeDrawers();
        if(currentItemId == id && id != R.id.favorite)
            return true;
        currentItemId = id;
        switch (id) {
            case R.id.computers:
                categoryEnum = CategoryEnum.COMPUTER;
                break;
            case R.id.tablets:
                categoryEnum = CategoryEnum.TABLET;
                break;
            case R.id.electronics:
                categoryEnum = CategoryEnum.ELECTRONICS;
                break;
            case R.id.lifestyle:
                categoryEnum = CategoryEnum.LIFESTYLE;
                break;
            case R.id.gaming:
                categoryEnum = CategoryEnum.GAMING;
                break;
            case R.id.favorite:
                invokeActivity(DealListActivity.this, ListFavoriteActivity.class);
                break;
        }
        tabLayout();
        return true;
    }
}
