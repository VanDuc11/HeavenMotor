package com.example.heaven_motor.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.LSDonHangAdapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.util.List;


public class DonHangCB_Fragment extends Fragment {
    ListView recyclerView;
    OrdersDao dao;
    List<Orders> list;
    LinearLayoutManager linearLayoutManager;
    LSDonHangAdapter adapter;
    Dialog dialog;
    TextView tvid , tvUser, tvxe,tvloai,tvdate,tvtien;
    Button btnHuydon;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_l_s_don_hang, container, false);
        dao = new OrdersDao(getContext());
        recyclerView  = v.findViewById(R.id.frag_LS_thue_xe_rey);
        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Orders o = list.get(position);
                OpenDialogInsert(o);
                return false;
            }
        });
        return v;
    }
    public void LoaData(){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        list = dao.getDonhang(user);
        adapter = new LSDonHangAdapter(getContext(),this,list);
        recyclerView.setAdapter(adapter);
    }
    public void OpenDialogInsert(Orders o) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ls_order, null);
        dialog = new Dialog(getContext());
        dialog.setContentView(view);
        tvid = dialog.findViewById(R.id.dialog_tvMadon);
        tvUser = dialog.findViewById(R.id.dialog_tvUser_id);
        tvxe = dialog.findViewById(R.id.dialog_tvXe);
        tvloai = dialog.findViewById(R.id.dialog_tvLoaiXe);
        tvdate = dialog.findViewById(R.id.dialog_tvdate);
        tvtien = dialog.findViewById(R.id.dialog_tvTien);
        btnHuydon = dialog.findViewById(R.id.dialog_btnHuyDon);

        UserDAO userDAO= new UserDAO(getContext());
        Users s = userDAO.getID(o.getUser_id());

        VehicleDAO vehicleDAO = new VehicleDAO(getContext());
        Vehicle v = vehicleDAO.getID(o.getVehicle_id());

        tvid.setText("Mã đơn hàng:" + o.getId());
        tvUser.setText("Người thuê: " +o.getUser_id() + " "+ s.getName());
        tvxe.setText("Tên xe: "+v.getBrand() +" "+ v.getName() +" " + v.getCapacity());
        tvdate.setText("Ngày thuê: " + o.getStart_time()+ " - " +o.getEnd_time());
        tvtien.setText("Tổng tiền: " + o.getTotal()+"đ");
        btnHuydon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o.setStatus(1);
                dao.Update(o);
                dialog.dismiss();
            }
        });
        if (o.getStatus() == 1){
            btnHuydon.setText("đơn hàng đã bị hủy");
            btnHuydon.setEnabled(false);
        }
        if (v.getTrangThai() > 1){
            btnHuydon.setEnabled(false);
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        // set kích thước dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        dialog.show();

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