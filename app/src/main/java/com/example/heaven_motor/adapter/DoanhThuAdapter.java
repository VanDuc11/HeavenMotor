package com.example.heaven_motor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.DoanhThu_Fragment;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.util.List;

public class DoanhThuAdapter extends ArrayAdapter{
    TextView tvMadh,tvName,tvMaxe,tvTongTien;
    Context context;
    List<Orders> list;
    OrdersDao dao ;
    DoanhThu_Fragment doanhThu_fragment;
    public DoanhThuAdapter(Context context,DoanhThu_Fragment doanhThu_fragment , List<Orders> list) {
        super(context,0,list);
        this.context = context;
        this.doanhThu_fragment = doanhThu_fragment;

        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View itemView, @NonNull ViewGroup parent) {
        if (itemView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item_doanhthu,null);
        }
        Orders o = list.get(position);
        if(o != null){
            tvMadh = itemView.findViewById(R.id.item_doanhthu_mahd);
            tvName = itemView.findViewById(R.id.item_doanhthu_tvTenxe);
            tvMaxe = itemView.findViewById(R.id.item_doanhthu_tvMaxe);
            tvTongTien = itemView.findViewById(R.id.item_doanhthu_tvTongtien);

            dao = new OrdersDao(context);
            VehicleDAO vehicleDAO = new VehicleDAO(context);
            String id_xe = o.getVehicle_id();
            Vehicle v = vehicleDAO.getID(id_xe);

            String id = String.valueOf(o.getId());

            tvMadh.setText(id);
            tvName.setText(v.getName());
            tvMaxe.setText(o.getVehicle_id());
            tvTongTien.setText(String.valueOf(dao.getPrice(id)));

        }
        return itemView;
    }


}
