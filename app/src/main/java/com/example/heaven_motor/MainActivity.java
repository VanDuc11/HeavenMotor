package com.example.heaven_motor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.heaven_motor.adapter.ViewpageAdapter;
import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.fragment.DatHang_Fragment;
import com.example.heaven_motor.fragment.DoanhThu_Fragment;
import com.example.heaven_motor.fragment.Doi_Mat_Khau_Fragment;
import com.example.heaven_motor.fragment.HomeFragment;
import com.example.heaven_motor.fragment.LSDH_Fragment;
import com.example.heaven_motor.fragment.DonHangCB_Fragment;
import com.example.heaven_motor.fragment.PhanHoiFragment;
import com.example.heaven_motor.fragment.QLyLoaiXe_Fragment;
import com.example.heaven_motor.fragment.QLyNguoi_Dung_Fragment;
import com.example.heaven_motor.fragment.QLyXe_Fragment;
import com.example.heaven_motor.fragment.QlyDonHang_Fragment;
import com.example.heaven_motor.fragment.TinTucFragment;
import com.example.heaven_motor.fragment.ToiFragment;
import com.example.heaven_motor.fragment.ThongkeDH_Fragment;
import com.example.heaven_motor.fragment.yeu_cau_Fragment;
import com.example.heaven_motor.model.Users;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ViewPager pager;
    ViewpageAdapter adapter;
    TextView nameUser;
    ImageView imgUser;
    UserDAO userDAO;
    View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navtion);
        mHeaderView = navigationView.getHeaderView(0);
        nameUser = mHeaderView.findViewById(R.id.nameUser);
        imgUser = mHeaderView.findViewById(R.id.imageUser);
        Intent i = getIntent();
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);


        String user2 = i.getStringExtra("user");
        userDAO = new UserDAO(this);
        Users users = userDAO.getID(user2);
        String name = users.getName();
        nameUser.setText("Welcome " + name + " !");

        try {
            byte[] imguser = users.getImg();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imguser, 0, imguser.length);
            if (users.getImg() == null) {
                imgUser.setImageResource(R.drawable.ic_user);
            } else {
                imgUser.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





        pager = findViewById(R.id.pagerTrangchu);
        addFragment(pager);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDWR, R.string.closeDWR);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        onContextMenuClosed();

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");


        navigationView.getMenu().findItem(R.id.home).setChecked(true);

        pager.setCurrentItem(9);
        if (user.equals("Admin")) {

            navigationView.getMenu().findItem(R.id.QLLX).setVisible(true);
            navigationView.getMenu().findItem(R.id.QLX).setVisible(true);
            navigationView.getMenu().findItem(R.id.QLDH).setVisible(true);
            navigationView.getMenu().findItem(R.id.topXe).setVisible(true);
            navigationView.getMenu().findItem(R.id.doanhThu).setVisible(true);
            navigationView.getMenu().findItem(R.id.QLND).setVisible(true);
            navigationView.getMenu().findItem(R.id.TB).setVisible(true);
            navigationView.getMenu().findItem(R.id.phanHoi).setVisible(true);

        } else {
            navigationView.getMenu().findItem(R.id.QLLX).setVisible(false);
            navigationView.getMenu().findItem(R.id.QLX).setVisible(false);
            navigationView.getMenu().findItem(R.id.QLDH).setVisible(false);
            navigationView.getMenu().findItem(R.id.topXe).setVisible(false);
            navigationView.getMenu().findItem(R.id.doanhThu).setVisible(false);
            navigationView.getMenu().findItem(R.id.QLND).setVisible(false);
            navigationView.getMenu().findItem(R.id.phanHoi).setVisible(false);
            navigationView.getMenu().findItem(R.id.TB).setVisible(false);

        }

    }

    private void onContextMenuClosed() {
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.QLLX) {
            pager.setAdapter(adapter);
            pager.setCurrentItem(0);
            toolbar.setTitle("Quản lí loại xe");

        } else if (id == R.id.QLX) {
            pager.setAdapter(adapter);
            pager.setCurrentItem(1);
            toolbar.setTitle("Quản lí xe");
        } else if (id == R.id.QLDH) {
            pager.setAdapter(adapter);
            pager.setCurrentItem(2);
            toolbar.setTitle("Quản lí đơn hàng");

        } else if (id == R.id.DatHang) {
            toolbar.setTitle("Đặt hàng");
            pager.setAdapter(adapter);
            pager.setCurrentItem(3);
        } else if (id == R.id.DH) {
            toolbar.setTitle("Đơn hàng đang đặt");
            pager.setAdapter(adapter);
            pager.setCurrentItem(4);

        } else if (id == R.id.topXe) {
            toolbar.setTitle("Thống kê");
            pager.setAdapter(adapter);
            pager.setCurrentItem(10);
        } else if (id == R.id.doanhThu) {
            toolbar.setTitle("Doanh thu");
            pager.setAdapter(adapter);
            pager.setCurrentItem(6);
        } else if (id == R.id.QLND) {
            toolbar.setTitle("Quản lí người dùng");
            pager.setAdapter(adapter);
            pager.setCurrentItem(7);
        } else if (id == R.id.doiPass) {
            toolbar.setTitle("Đổi mật khẩu");
            pager.setAdapter(adapter);
            pager.setCurrentItem(8);
        } else if (id == R.id.home) {
            toolbar.setTitle("Home");
            pager.setAdapter(adapter);
            pager.setCurrentItem(9);
        } else if (id == R.id.toi) {
            toolbar.setTitle("Tài khoản");
            pager.setAdapter(adapter);
            pager.setCurrentItem(11);
        }
        if (id == R.id.TB) {
            toolbar.setTitle("Thông báo");
            pager.setAdapter(adapter);
            pager.setCurrentItem(12);
        } else if (id == R.id.dangXuat) {

            startActivity(new Intent(MainActivity.this, Login_MainActivity2.class));
        } else if (id == R.id.LSDH) {
            toolbar.setTitle("Lịch sử đơn hàng");
            pager.setAdapter(adapter);
            pager.setCurrentItem(13);
        } else if (id == R.id.phanHoi) {
            toolbar.setTitle("Phản hồi của khách hàng");
            pager.setAdapter(adapter);
            pager.setCurrentItem(14);
        }
        drawerLayout.closeDrawer(navigationView);
        return false;
    }


    public void addFragment(ViewPager viewPager) {
        adapter = new ViewpageAdapter(getSupportFragmentManager());
        adapter.addFragment(new QLyLoaiXe_Fragment(), "Quản lý loại xe");
        adapter.addFragment(new QLyXe_Fragment(), "Quản lý xe");
        adapter.addFragment(new QlyDonHang_Fragment(), "Quản lý đơn hàng");
        adapter.addFragment(new DatHang_Fragment(), "Đặt hàng");
        adapter.addFragment(new DonHangCB_Fragment(), "Những đơn đã đặt");
        adapter.addFragment(new ThongkeDH_Fragment(), "Top xe được thuê nhiều nhất");
        adapter.addFragment(new DoanhThu_Fragment(), "Doanh thu");
        adapter.addFragment(new QLyNguoi_Dung_Fragment(), "Quản lý người dùng");
        adapter.addFragment(new Doi_Mat_Khau_Fragment(), "Đổi mật khẩu");
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new TinTucFragment(), "Tin tức");
        adapter.addFragment(new ToiFragment(), "Tài Khoản");
        adapter.addFragment(new yeu_cau_Fragment(), "Yêu Cầu");
        adapter.addFragment(new LSDH_Fragment(), "LSDH");
        adapter.addFragment(new PhanHoiFragment(), "Phản hồi của khách hàng");
        pager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    public boolean phanQuyen(){
        if (Build.VERSION.SDK_INT >= 23){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CALL_PHONE)
                            == PackageManager.PERMISSION_GRANTED&&
                    checkSelfPermission(Manifest.permission.INTERNET)
                            ==PackageManager.PERMISSION_GRANTED){

                return true;
            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.INTERNET,
                                    Manifest.permission.CALL_PHONE},1);
            }
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        phanQuyen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onStart();
    }
}