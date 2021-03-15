package com.example.onboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.onboard.fragments.FirstFragment;
import com.example.onboard.fragments.SecondFragment;
import com.example.onboard.fragments.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    List<Fragment>fragmentList;
    ViewPager pager;
    slideAdapter adapter;
    CircleIndicator indicator;
    TextView next,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        fragmentList = new ArrayList<>();
        fillTheArray();
        indicator = findViewById(R.id.circleIndicator);
        pager   = findViewById(R.id.pager);
        adapter = new slideAdapter(getSupportFragmentManager(),fragmentList);
        pager.setAdapter(adapter);
        visibilitySettings();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(pager.getCurrentItem()-1);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                visibilitySettings();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        indicator.setViewPager(pager);
    }

    void fillTheArray(){
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
    }
    void visibilitySettings(){
        switch (pager.getCurrentItem()){

            case 0:
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;
            case 1:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;
            case 2:
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;
        }
    }
}