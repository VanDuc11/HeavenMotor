package com.example.heaven_motor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.OrdersDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.fragment.yeu_cau_Fragment;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Users;
import com.example.heaven_motor.model.Vehicle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThongBaoAdapter extends ArrayAdapter<Orders> {
    List<Orders> list;
    Context context;
    yeu_cau_Fragment fragment;
    TextView tvTenSp,tvMa,tvThoigian,tvYc,tvUser;
    Button btnXuly,btnHuy;
    Calendar calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public ThongBaoAdapter(@NonNull Context context, yeu_cau_Fragment fragment, List<Orders> list) {
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
            convertView = inflater.inflate(R.layout.item_thong_bao,parent,false);
        }

        Orders o = list.get(position);
        if (o != null){
            tvMa = convertView.findViewById(R.id.item_thong_bao_tvid);
            tvTenSp = convertView.findViewById(R.id.item_thong_bao_tvmaXe);
            tvThoigian = convertView.findViewById(R.id.item_thong_bao_tvThoigian);
            tvUser = convertView.findViewById(R.id.item_thong_bao_tvuser_id);
            tvYc = convertView.findViewById(R.id.item_thong_bao_tvYc);
            btnXuly = convertView.findViewById(R.id.item_thong_bao_btn);
            btnHuy = convertView.findViewById(R.id.item_thong_bao_btnHuy);
            OrdersDao ordersDao = new OrdersDao(context);
            VehicleDAO dao = new VehicleDAO(context);
            UserDAO userDAO = new UserDAO(context);
            String id = o.getVehicle_id();
            Vehicle v = dao.getID(id);

            String idUser = o.getUser_id();
            Users u = userDAO.getID(idUser);

            tvMa.setText("Mã đơn hàng:" + o.getId());
            tvUser.setText("Người đặt: " +idUser +" - "+ u.getName());
            tvTenSp.setText("Mã xe: " + o.getVehicle_id()+ " " + v.getBrand()
                    +" "+ v.getName()+" " + v.getCapacity());

            tvThoigian.setText("Thời gian thuê: "+ o.getStart_time() +" - "+ o.getEnd_time());
            String id_dh= String.valueOf(o.getId());
            if (list.get(position).getId() == o.getId() && v.getTrangThai() == 1){
                btnXuly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        v.setTrangThai(2);
                        dao.Update(v);
                        btnXuly.setText("Đã xử lý");
                        btnXuly.setEnabled(false);
                        btnHuy.setEnabled(false);
                        calendar = Calendar.getInstance();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        o.setStatus(1);
                        btnHuy.setText("Đã hủy");
                        btnXuly.setEnabled(false);
                        btnHuy.setEnabled(false);
                        calendar = Calendar.getInstance();
                        o.setTimethuc(sdf.format(calendar.getTime()));
                        ordersDao.Update(o);
                        long a = Date.parse(o.getTimethuc());
                        long b = Date.parse(o.getEnd_time());
                        if ( a > b){
                            int date = ordersDao.getDate1();
                            if (date !=0){
                                double date2 = ordersDao.getDate2(id_dh)*(v.getPrice()*1.5);
                                o.setPhatsinh(date2);
                                ordersDao.Update(o);
                                Toast.makeText(context, "Đơn hàng này đã trả muộn: " + ordersDao.getDate2(id_dh) + " ngày", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }
            if (o.getStatus() == 1){
                btnHuy.setText("Đã hủy");
                btnXuly.setEnabled(false);
                btnHuy.setEnabled(false);
            }

            if( v.getTrangThai() == 2){
                btnXuly.setText("Đã xử lý");
                btnHuy.setEnabled(false);
                btnXuly.setEnabled(false);

            }else if (v.getTrangThai() == 3){
                tvYc.setText("Yêu cầu trả xe");
                btnHuy.setEnabled(false);
                btnXuly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        v.setTrangThai(4);
                        dao.Update(v);
                        btnXuly.setText("Đã xử lý");
                        btnXuly.setEnabled(false);
                        calendar = Calendar.getInstance();
                        o.setTimethuc(sdf.format(calendar.getTime()));
                        ordersDao.Update(o);
                        long a = Date.parse(o.getTimethuc());
                        long b = Date.parse(o.getEnd_time());
                        if ( a > b){
                            int date = ordersDao.getDate1();
                            if (date !=0){
                                double date2 = ordersDao.getDate2(id_dh)*(v.getPrice()*1.5);
                                o.setPhatsinh(date2);
                                ordersDao.Update(o);
                                Toast.makeText(context, "Đơn hàng này đã trả muộn: " + ordersDao.getDate2(id_dh) + " ngày", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            }else if ( v.getTrangThai() == 4){
                v.setTrangThai(0);
                dao.Update(v);
                o.setStatus(2);
                ordersDao.Update(o);
                btnHuy.setEnabled(false);
            }else if (v.getTrangThai() == 0){
                btnXuly.setText("Đã hoàn thành");
                btnXuly.setEnabled(false);
            }
        }

        return convertView;
    }
}
