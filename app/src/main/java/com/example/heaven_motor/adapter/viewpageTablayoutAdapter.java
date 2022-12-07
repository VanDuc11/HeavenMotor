package com.example.heaven_motor.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.heaven_motor.fragment.ThongkeDH_Fragment;
import com.example.heaven_motor.fragment.Top_Fragment;

public class viewpageTablayoutAdapter extends FragmentStateAdapter {


    public viewpageTablayoutAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Top_Fragment();

            case 1:
                return new ThongkeDH_Fragment();
            default:
                return new Top_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
