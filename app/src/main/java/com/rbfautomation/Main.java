package com.rbfautomation;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.rbfautomation.data.CardItem;
import com.rbfautomation.data.SwitchCardItem;
import com.rbfautomation.fragments.CardListViewFragment;
import com.rbfautomation.network.Request;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class Main extends ActionBarActivity implements Request.OnRequsetListener {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().setStatusBarColor(getResources().getColor(R.color.primaryAccent));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setElevation(10);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle;
        mDrawerToggle = new ActionBarDrawerToggle(
                this,  mDrawerLayout, toolbar,
                R.string.abc_action_bar_home_description, R.string.abc_action_bar_home_description
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        //Request.makeRequest("test", "test", getApplicationContext(), this);


        mFragmentManager = getFragmentManager();

        ArrayList<CardItem> cards = new ArrayList();
        cards.add(new SwitchCardItem("Front Door", "UnLock", "Lock"));

        CardListViewFragment fragment = new CardListViewFragment();
        fragment.setCardData(cards);
        mFragmentManager.beginTransaction().add(R.id.content_frame, fragment).commit();


    }


    @Override
    public void onResponse(HttpResponse response) {
        try {
            Log.e("e", EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {}
    }

    @Override
    public void onError(Exception e) {

    }
}
