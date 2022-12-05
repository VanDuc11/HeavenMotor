package com.example.heaven_motor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.util.List;

public class List_LSDH_Adapter extends RecyclerView.Adapter<List_LSDH_Adapter.ListDHViewHoder> {

    Context context;
    List<Orders> list;
    OrdersDao dao ;
    Orders o;

    public List_LSDH_Adapter(Context context, List<Orders> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListDHViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_lsdh,parent,false);
        return new ListDHViewHoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListDHViewHoder holder, int position) {
        o = list.get(position);
        dao = new OrdersDao(context);
        UserDAO userDAO = new UserDAO(context);
        Users u = userDAO.getID(o.getUser_id());
        VehicleDAO vehicleDAO = new VehicleDAO(context);
        Vehicle v = vehicleDAO.getID(o.getVehicle_id());
        CategorisDao categorisDao = new CategorisDao(context);
        String id = String.valueOf(v.getCategorie_id());
        Categoris c = categorisDao.getID(id);

        holder.tvMa.setText("Mã đơn hàng: " + o.getId());
        holder.tvUser.setText("Khách hàng: " + o.getUser_id()+ " - "+u.getName());
        holder.tvXe.setText("Tên xe: " + v.getName() + " - " + o.getVehicle_id());
        holder.tvLoai.setText("Loại xe: "+c.getName());
        holder.tvDate.setText("Ngày thuê: từ "+o.getStart_time() + " - "+o.getEnd_time());
        String order_id = String.valueOf(o.getId());
        int tien = dao.getPrice(order_id);
        holder.tvPrice.setText("Tổng tiền: " + tien + " đ");
        if (o.getStatus()== 1){
            holder.tvTTDH.setText("Tình trạng: Đơn hàng bị hủy");
        }else if (o.getStatus() == 2){
            holder.tvTTDH.setText("Tình trạng:  Đơn hàng thành công");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class ListDHViewHoder extends RecyclerView.ViewHolder{
        TextView tvMa, tvUser,tvXe,tvLoai,tvDate,tvTTDH,tvPrice;

        public ListDHViewHoder(@NonNull View view) {
            super(view);
            tvMa = view.findViewById(R.id.item_list_dh_tvid);
            tvUser = view.findViewById(R.id.item_list_dh_tvUser_id);
            tvXe = view.findViewById(R.id.item_list_dh_tvmaXe);
            tvLoai = view.findViewById(R.id.item_list_dh_tvLoai);
            tvDate = view.findViewById(R.id.item_list_dh_tvThoigian);
            tvTTDH = view.findViewById(R.id.item_list_dh_tvTT);
            tvPrice = view.findViewById(R.id.item_list_dh_tvPrice);
        }
    }
}
