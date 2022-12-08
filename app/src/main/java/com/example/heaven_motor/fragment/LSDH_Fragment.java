package com.example.heaven_motor.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.LSDonHangAdapter;
import com.example.heaven_motor.adapter.List_LSDH_Adapter;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.util.Date;
import java.util.List;


public class LSDH_Fragment extends Fragment {
    ListView view;
    List<Orders> list;
    OrdersDao dao ;
    List_LSDH_Adapter adapter;
    TextView tvTenSp,tvMa,tvThoigian,tvLoai,tvUser,tvSDT,tvDC,tvTongtien,tvTT,tvTien,tvPhatsinh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dao = new OrdersDao(getContext());
        View v = inflater.inflate(R.layout.fragment_l_s_d_h_, container, false);
        view = v.findViewById(R.id.frag_list_lsdh);
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Orders o = list.get(position);
                dialog(o);
                return false;
            }
        });
        return v;
    }
    public void LoaData(){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        list = dao.getAllDH(user);
        adapter = new List_LSDH_Adapter(getContext(),list);
        view.setAdapter(adapter);
    }
    public void dialog(Orders o){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ctdh,null);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        tvMa = dialog.findViewById(R.id.item_qldh_tvid);
        tvUser = dialog.findViewById(R.id.item_qldh_tvUser_id);
        tvTenSp = dialog.findViewById(R.id.item_qldh_tvmaXe);
        tvThoigian = dialog.findViewById(R.id.item_qldh_tvThoigian);
        tvLoai = dialog.findViewById(R.id.item_qldh_tvLoai);
        tvDC = dialog.findViewById(R.id.item_qldh_tvDiachi);
        tvSDT = dialog.findViewById(R.id.item_qldh_tvSDT);
        tvTongtien = dialog.findViewById(R.id.item_qldh_tvtongtien);
        tvTT = dialog.findViewById(R.id.item_qldh_tvTT);
        tvTien =dialog.findViewById(R.id.item_qldh_tvTien);
        tvPhatsinh =dialog.findViewById(R.id.item_qldh_tvphatSinh);


        OrdersDao ordersDao = new OrdersDao(getContext());
        VehicleDAO dao = new VehicleDAO(getContext());
        UserDAO userDAO = new UserDAO(getContext());
        String id = o.getVehicle_id();
        Vehicle v = dao.getID(id);

        String idUser = o.getUser_id();
        Users u = userDAO.getID(idUser);
        String id_dh = String.valueOf(o.getId());
        tvMa.setText("Mã đơn hàng: " + o.getId());
        tvUser.setText("Người đặt: " +idUser +" - "+ u.getName());
        tvTenSp.setText("Mã xe: " + o.getVehicle_id()+ " " + v.getBrand()
                +" "+ v.getName()+" " + v.getCapacity());
        tvLoai.setText("Loại xe: " +dao.getLoaixe());
        tvThoigian.setText("Thời gian thuê: "+ o.getStart_time() +" - "+ o.getEnd_time());
        tvDC.setText("Địa chỉ: " + u.getAddress());
        tvSDT.setText("SĐT:" + u.getPhone());
        String order_id = String.valueOf(o.getId());
        int tien = ordersDao.getPrice(order_id);
        tvTongtien.setText("Thanh toán: " + tien + " đ");
        tvTien.setText("Tiền thuê: " + o.getTotal()+ " đ");

        long a = Date.parse(o.getTimethuc());
        long b = Date.parse(o.getEnd_time());
        if ( a > b) {
            tvPhatsinh.setText("Phát sinh: " + o.getPhatsinh() + " đ\n Lý do: trả quá hạn "
                    + ordersDao.getDate2(id_dh) + " ngày" +
                    "\nTổng tiền = tiền thuê + Giá/ngày x 1.5");
            tvPhatsinh.setTextColor(Color.RED);
            tvPhatsinh.setTypeface(null, Typeface.ITALIC);
        }else {
            tvPhatsinh.setText("Phát sinh: " + 0 +"đ");
        }


        if (o.getStatus()== 1){
            tvTT.setText("Tình trạng: Đơn hàng bị hủy");
        }else if (o.getStatus() == 2){
            tvTT.setText("Tình trạng:  Đơn hàng thành công");
        }
        tvSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = u.getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: "+sdt));
                getContext().startActivity(intent);
            }
        });
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