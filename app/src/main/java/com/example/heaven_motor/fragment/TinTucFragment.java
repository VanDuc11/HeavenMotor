package com.example.heaven_motor.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.viewpageTablayoutAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TinTucFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 pager2;
    viewpageTablayoutAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tin_tuc, container, false);
        tabLayout = v.findViewById(R.id.frag_tablayout);
        pager2 = v.findViewById(R.id.frag_viewPage);
        adapter = new viewpageTablayoutAdapter(this);
        pager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,pager2,(tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText("Top xe");
                    break;
                }
                case 1: {
                    tab.setText("Thống kê ĐH");
                    break;
                }
            }
        }).attach();


        return v;

    }


}