package com.example.heaven_motor.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.PhanHoiAdapter;
import com.example.heaven_motor.adapter.ThongBaoAdapter;
import com.example.heaven_motor.database.FeedbackDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Feedback;
import com.example.heaven_motor.model.Users;

import java.util.List;


public class PhanHoiFragment extends Fragment {
    ListView listView;
    PhanHoiAdapter phanHoiAdapter;
    FeedbackDao feedbackDAO;
    List<Feedback> listPhanHoi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phan_hoi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feedbackDAO  = new FeedbackDao(getContext());
        listView = view.findViewById(R.id.frag_phan_hoi);
    }
    public void loadData(){
        listPhanHoi = feedbackDAO.getAll();
        phanHoiAdapter = new PhanHoiAdapter(listPhanHoi,getContext());
        listView.setAdapter(phanHoiAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onStart();
    }
}