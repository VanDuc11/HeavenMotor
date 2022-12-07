package com.example.heaven_motor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.heaven_motor.R;
import com.example.heaven_motor.fragment.Top_Fragment;
import com.example.heaven_motor.model.Top;

import java.util.ArrayList;

public class Top1Adapter extends ArrayAdapter<Top> {
    private Context context;
    Top_Fragment top_fragment;
    ArrayList<Top> lists;
    TextView tvid,tvten,tvdungtich,tvbienso,tvsoluong;


    public Top1Adapter(@NonNull Context context, Top_Fragment top_fragment, ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context = context;
        this.top_fragment = top_fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_top,null);
        }
        final  Top item =lists.get(position);
        if (item != null){
            tvid =v.findViewById(R.id.tvid);
            tvid.setText(""+item.getId());

            tvten =v.findViewById(R.id.tvten);
            tvten.setText(""+item.getName());

//            tvloai =v.findViewById(R.id.tvloai);
//            tvloai.setText("Loại xe: "+item.getCategorie_id());

//           tvnhanhieu =v.findViewById(R.id.tvnhanhieu);
//           tvnhanhieu.setText("Hẵng "+"        "+item.getBrand());

            tvdungtich =v.findViewById(R.id.tvdungtich);
            tvdungtich.setText(""+item.getCapacity());

            tvbienso =v.findViewById(R.id.tvbienso);
            tvbienso.setText(""+item.getBKS());

            tvsoluong =v.findViewById(R.id.tvsoluong);
            tvsoluong.setText(""+item.getSoluong());

        }
        return v;
    }

}
