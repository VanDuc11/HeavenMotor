package com.example.heaven_motor.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.CategorisDao;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Categoris;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.util.Date;
import java.util.List;

public class List_LSDH_Adapter extends ArrayAdapter {

    Context context;
    List<Orders> list;
    OrdersDao dao ;
    TextView tvMa, tvUser,tvXe,tvLoai,tvDate,tvTTDH,tvPrice,tvCT;


    public List_LSDH_Adapter(Context context, List<Orders> list) {
        super(context,0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_lsdh,parent,false);
        }
        Orders o = list.get(position);
        if (o != null){
            tvMa = view.findViewById(R.id.item_list_dh_tvid);
            tvUser = view.findViewById(R.id.item_list_dh_tvUser_id);
            tvXe = view.findViewById(R.id.item_list_dh_tvmaXe);
            tvLoai = view.findViewById(R.id.item_list_dh_tvLoai);
            tvDate = view.findViewById(R.id.item_list_dh_tvThoigian);
            tvTTDH = view.findViewById(R.id.item_list_dh_tvTT);
            tvPrice = view.findViewById(R.id.item_list_dh_tvPrice);
            dao = new OrdersDao(context);

            UserDAO userDAO = new UserDAO(context);
        Users u = userDAO.getID(o.getUser_id());
        VehicleDAO vehicleDAO = new VehicleDAO(context);
        Vehicle v = vehicleDAO.getID(o.getVehicle_id());
        CategorisDao categorisDao = new CategorisDao(context);
        String id = String.valueOf(v.getCategorie_id());
        Categoris c = categorisDao.getID(id);

        tvMa.setText("Mã đơn hàng: " + o.getId());
        tvUser.setText("Khách hàng: " + o.getUser_id()+ " - "+u.getName());
        tvXe.setText("Tên xe: " + v.getName() + " - " + o.getVehicle_id());
        tvLoai.setText("Loại xe: "+c.getName());
        tvDate.setText("Ngày thuê: từ "+o.getStart_time() + " - "+o.getEnd_time());

        // gạch chân
//        SpannableString content = new SpannableString("Xem chi tiết -->");
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        tvCT.setText(content);

        String order_id = String.valueOf(o.getId());
        int tien = dao.getPrice(order_id);
        tvPrice.setText("Tổng tiền: " + tien + " đ");
        if (o.getStatus() == 0){
            tvTTDH.setText("Tình trạng: Đang xử lý");
        }else if (o.getStatus()== 1){
            tvTTDH.setText("Tình trạng: Đơn hàng bị hủy");
        }else if (o.getStatus() == 2){
            tvTTDH.setText("Tình trạng:  Đơn hàng thành công");
        }
        }
        return view;
    }

}
