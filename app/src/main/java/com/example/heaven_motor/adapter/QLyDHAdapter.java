package com.example.heaven_motor.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.QlyDonHang_Fragment;
import com.example.heaven_motor.fragment.yeu_cau_Fragment;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.util.List;

public class QLyDHAdapter extends ArrayAdapter {
    List<Orders> list;
    Context context;
    QlyDonHang_Fragment fragment;
    TextView tvTenSp,tvMa,tvThoigian,tvLoai,tvUser,tvSDT,tvDC,tvTien,tvTT;


    public QLyDHAdapter(@NonNull Context context, QlyDonHang_Fragment fragment, List<Orders> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_qldh,parent,false);
        }

        Orders o = list.get(position);
        if (o != null){
            tvMa = convertView.findViewById(R.id.item_qldh_tvid);
            tvUser = convertView.findViewById(R.id.item_qldh_tvUser_id);
            tvTenSp = convertView.findViewById(R.id.item_qldh_tvmaXe);
            tvThoigian = convertView.findViewById(R.id.item_qldh_tvThoigian);
            tvLoai = convertView.findViewById(R.id.item_qldh_tvLoai);
            tvDC = convertView.findViewById(R.id.item_qldh_tvDiachi);
            tvSDT = convertView.findViewById(R.id.item_qldh_tvSDT);
            tvTien = convertView.findViewById(R.id.item_qldh_tvTien);
            tvTT = convertView.findViewById(R.id.item_qldh_tvTT);
            OrdersDao ordersDao = new OrdersDao(context);
            VehicleDAO dao = new VehicleDAO(context);
            UserDAO userDAO = new UserDAO(context);
            String id = o.getVehicle_id();
            Vehicle v = dao.getID(id);

            String idUser = o.getUser_id();
            Users u = userDAO.getID(idUser);

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
            tvTien.setText("Tổng tiền: " + tien + " đ");
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
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
