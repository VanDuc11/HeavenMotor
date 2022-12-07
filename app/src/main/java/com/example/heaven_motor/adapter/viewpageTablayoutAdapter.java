package com.example.heaven_motor.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.heaven_motor.fragment.DoanhThu_Fragment;
import com.example.heaven_motor.fragment.TopMuon_Fragment;

public class viewpageTablayoutAdapter extends FragmentStateAdapter {


    public viewpageTablayoutAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new DoanhThu_Fragment();

            case 1:
                return new TopMuon_Fragment();
            default:
                return new DoanhThu_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
