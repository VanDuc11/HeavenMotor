package com.example.heaven_motor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.database.VehicleDAO;
import com.example.heaven_motor.model.Orders;
import com.example.heaven_motor.model.Vehicle;

import java.util.List;

public class TopAdapter extends ArrayAdapter<Orders> {
    private Context context;
    List<Orders> lists;
    TextView tvid,tvten,tvTK,tvHuy,tvMaxe;

    public TopAdapter(@NonNull Context context, List<Orders> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top_muon,null);
        }
        final  Orders item = lists.get(position);
        if (item != null){
            tvid =v.findViewById(R.id.tvid);
            tvten =v.findViewById(R.id.tvten);
            tvTK =v.findViewById(R.id.tvTK);
            tvMaxe = v.findViewById(R.id.tvMaxe);
            tvHuy = v.findViewById(R.id.tvhuy);

            VehicleDAO  dao = new VehicleDAO(context);
            Vehicle vehicle = dao.getID(item.getVehicle_id());
            String name = vehicle.getName();




            tvid.setText(String.valueOf(item.getId()));
            tvten.setText(name);
            tvMaxe.setText(vehicle.getId());
            if (item.getStatus() == 1){
                tvHuy.setText("X");
                tvTK.setText("");
                tvHuy.setTextColor(Color.RED);
            }else if (item.getStatus() == 2){
                tvTK.setText("X");
                tvHuy.setText("");
                tvTK.setTextColor(Color.RED);
            }

        }
        return v;
    }

}