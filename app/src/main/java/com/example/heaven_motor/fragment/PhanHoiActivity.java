package com.example.heaven_motor.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heaven_motor.R;
import com.example.heaven_motor.adapter.PhanHoiAdapter;
import com.example.heaven_motor.database.FeedbackDao;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.model.Feedback;
import com.example.heaven_motor.model.Users;

import java.util.ArrayList;
import java.util.List;

public class PhanHoiActivity extends AppCompatActivity {
    EditText editText;
    Button gui, huy;
    FeedbackDao feedbackDao;
    Feedback f;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);
        editText = findViewById(R.id.edPhanHoi);
        gui = findViewById(R.id.btnguiPhanHoi);
        huy = findViewById(R.id.btnHuyPhanHoi);
        feedbackDao = new FeedbackDao(this);
        f = new Feedback();


        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) {
                    Toast.makeText(PhanHoiActivity.this, "Vui lòng nhập phản hồi", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getIntent();
                    String maUser = null;
                    if (getIntent().hasExtra("user")) {
                        maUser = intent.getStringExtra("user");
                    }
                    f.setUser_id(maUser);
                    f.setPhanhoi(editText.getText().toString());
                    int kq = feedbackDao.Insert(f);
                    if (kq > 0) {
                        Toast.makeText(PhanHoiActivity.this, "Gửi phản hồi thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PhanHoiActivity.this, "Gửi phàn hổi không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}