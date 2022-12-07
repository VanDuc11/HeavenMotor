package com.example.heaven_motor.fragment;

//-----package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.TopAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Orders;

import java.util.List;


public class ThongkeDH_Fragment extends Fragment {

    ListView lv;
    List<Orders> list;
    TopAdapter adapter;
    OrdersDao ordersDao;
    TextView tvDOntk,tvDonhuy,tvTongDH;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ordersDao = new OrdersDao(getContext());

        View v = inflater.inflate(R.layout.fragment_top_muon, container, false);
        lv = v.findViewById(R.id.lvtop);
        tvDOntk = v.findViewById(R.id.tvDontk);
        tvDonhuy = v.findViewById(R.id.tvDonhuy);
        tvTongDH = v.findViewById(R.id.tvTongDH);


        loaData();
        tvDOntk.setText("Đơn hàng thành công: "+ordersDao.getCountDonTK());
        tvDonhuy.setText("Đơn hàng bị hủy: "+ordersDao.getCountDonhuy());
        tvTongDH.setText("Tổng đơn hàng: "+ ordersDao.getCountAllDH());
        return v;
    }

    public void loaData(){
        list = ordersDao.getAll();
        adapter = new TopAdapter(getContext(), list);
        lv.setAdapter(adapter);
    }
}