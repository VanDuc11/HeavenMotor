package com.example.heaven_motor.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.LSDonHangAdapter;
import com.example.heaven_motor.adapter.List_LSDH_Adapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Orders;

import java.util.List;


public class LSDH_Fragment extends Fragment {
    RecyclerView view;
    List<Orders> list;
    OrdersDao dao ;
    List_LSDH_Adapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dao = new OrdersDao(getContext());
        View v = inflater.inflate(R.layout.fragment_l_s_d_h_, container, false);
        view = v.findViewById(R.id.frag_list_lsdh);
        return v;
    }
    public void LoaData(){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        list = (List<Orders>) dao.getAllDH(user);
        linearLayoutManager = new LinearLayoutManager(getContext());
        view.setLayoutManager(linearLayoutManager);
        adapter = new List_LSDH_Adapter(getContext(),list);
        view.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        LoaData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }
}