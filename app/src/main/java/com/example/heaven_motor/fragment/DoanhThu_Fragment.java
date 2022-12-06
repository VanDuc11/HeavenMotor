package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.DoanhThuAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.model.Orders;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class DoanhThu_Fragment extends Fragment {
    Button btndoanhthu;
    EditText edtungay,eddenngay;
    LottieAnimationView btntungay,btndenngay;
    EditText tvdoanhthu;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendarOne,calendarTwo;
    ListView view;
    LinearLayoutManager linearLayoutManager;
    OrdersDao dao;
    DoanhThuAdapter adapter;
    List<Orders> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        edtungay = v.findViewById(R.id.edtungay);
        eddenngay = v.findViewById(R.id.edDenNgay);
        tvdoanhthu = v.findViewById(R.id.tvdoanhthu);
        btntungay = v.findViewById(R.id.btntungay);
        btndenngay = v.findViewById(R.id.btnDenNgay);
        btndoanhthu = v.findViewById(R.id.btndoanhthu);
        view = v.findViewById(R.id.frag_doanhthu_lv);
        tvdoanhthu.setEnabled(false);
        btntungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay1();
            }
        });
        btndenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay2();
            }
        });
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tungay = edtungay.getText().toString();
                String denngay = eddenngay.getText().toString();
                OrdersDao ordersDao = new OrdersDao(getActivity());
                tvdoanhthu.setText("Doanh thu: "+ordersDao.getdoanhthu(tungay,denngay)+" đ");
                loaData();
            }
        });

        return v;
    }
    private void chonNgay1(){
        calendarOne = Calendar.getInstance();
        int mday,mMonth,mYear;
        mday = calendarOne.get(Calendar.DAY_OF_MONTH);
        mMonth = calendarOne.get(Calendar.MONDAY);
        mYear = calendarOne.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarOne.set(year,month,dayOfMonth);
                        edtungay.setText(sdf.format(calendarOne.getTime()));
                    }
                },mYear,mMonth,mday);
        datePickerDialog.show();
    }
    private void chonNgay2(){
        calendarTwo = Calendar.getInstance();
        int mday,mMonth,mYear;
        mday = calendarTwo.get(Calendar.DAY_OF_MONTH);
        mMonth = calendarTwo.get(Calendar.MONDAY);
        mYear = calendarTwo.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendarTwo.set(year,month,dayOfMonth);
                        eddenngay.setText(sdf.format(calendarTwo.getTime()));
                    }
                },mYear,mMonth,mday);
        datePickerDialog.show();
    }

    public void loaData(){
        dao = new OrdersDao(getContext());
        String date1 = edtungay.getText().toString();
        String date2 = eddenngay.getText().toString();
        list = dao.getAllDoanhthu(date1,date2);
        adapter = new DoanhThuAdapter(getContext(),this,list);
        view.setAdapter(adapter);
    }
}