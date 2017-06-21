package com.developers.nilmahadevvidhyalaya;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.developers.nilmahadevvidhyalaya.Fragments.OneFragment;
import com.developers.nilmahadevvidhyalaya.Fragments.ThreeFragment;
import com.developers.nilmahadevvidhyalaya.Fragments.TwoFragment;


/**
 * Created by Developers on 11-05-2017.
 */

public class School_Facilitys extends AppCompatActivity {

    private TabLayout tabLayout;
    private LinearLayout container;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_facility);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        container = (LinearLayout) findViewById(R.id.fragment_container);

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });
        //create tabs title
        tabLayout.addTab(tabLayout.newTab().setText("Class Room"));
        tabLayout.addTab(tabLayout.newTab().setText("Computer Room"));
        tabLayout.addTab(tabLayout.newTab().setText("Dancing Room"));

        //replace default fragment
        replaceFragment(new OneFragment());

        //handling tab click event
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    replaceFragment(new OneFragment());
                } else if (tab.getPosition() == 1) {
                    replaceFragment(new TwoFragment());
                } else if (tab.getPosition() == 2) {
                    replaceFragment(new ThreeFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        transaction.commit();
    }
}
