package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.PhanHoiAdapter;
import com.example.heaven_motor.adapter.ThongBaoAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;

import java.util.List;

public class yeu_cau_Fragment extends Fragment {
    ListView listView;

    List<Orders> list;
    OrdersDao ordersDao;
    ThongBaoAdapter adapter;

    PhanHoiAdapter phanHoiAdapter;
    UserDAO userDAO;
    List<Users> listPhanHoi;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_yeu_cau_, container, false);
        ordersDao = new OrdersDao(getContext());
        userDAO  = new UserDAO(getContext());
        listView = v.findViewById(R.id.frag_yeu_cau);
        return v;
    }
    public void loadDialog(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(getContext());

    }
    public void loadData(){
        list = ordersDao.getAll1();
        adapter = new ThongBaoAdapter(getContext(),this,list);

        listView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }
}