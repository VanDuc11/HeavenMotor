package com.example.heaven_motor.fragment;

//-----package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.Top1Adapter;
import com.example.heaven_motor.adapter.TopAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Top;

import java.util.ArrayList;


public class Top_Fragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    Top1Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.layout_top, container, false);
        lv = v.findViewById(R.id.lvtop1);
        OrdersDao ordersDao = new OrdersDao(getContext());
        list = (ArrayList<Top>) ordersDao.getTop();
        adapter = new Top1Adapter(getContext(), this, list);
        lv.setAdapter(adapter);
        return v;
    }
}
