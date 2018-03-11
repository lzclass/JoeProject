package com.wiwj.maigcer.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.wiwj.maigcer.R;
import com.wiwj.maigcer.presenter.iview.IMainView;
import com.wiwj.maigcer.presenter.main.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    @BindView(R.id.iv_userHead)
    ImageView iv_userHead;
    private DrawerLayout rl_drawer;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rl_drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, rl_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        rl_drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        presenter = new MainPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (rl_drawer.isDrawerOpen(GravityCompat.START)) {
            rl_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_msg_setting) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_clear_cache) {

        } else if (id == R.id.nav_check_update) {

        } else if (id == R.id.nav_logout) {

        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        rl_drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
