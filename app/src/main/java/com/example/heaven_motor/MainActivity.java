package com.example.heaven_motor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.heaven_motor.database.UserDAO;
import com.example.heaven_motor.fragment.DatHang_Fragment;
import com.example.heaven_motor.fragment.DoanhThu_Fragment;
import com.example.heaven_motor.fragment.Doi_Mat_Khau_Fragment;
import com.example.heaven_motor.fragment.DonHangCB_Fragment;
import com.example.heaven_motor.fragment.HomeFragment;
import com.example.heaven_motor.fragment.LSDH_Fragment;
import com.example.heaven_motor.fragment.PhanHoiFragment;
import com.example.heaven_motor.fragment.QLyLoaiXe_Fragment;
import com.example.heaven_motor.fragment.QLyNguoi_Dung_Fragment;
import com.example.heaven_motor.fragment.QLyXe_Fragment;
import com.example.heaven_motor.fragment.QlyDonHang_Fragment;
import com.example.heaven_motor.fragment.TinTucFragment;
import com.example.heaven_motor.fragment.ToiFragment;
import com.example.heaven_motor.fragment.yeu_cau_Fragment;
import com.example.heaven_motor.model.Users;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    NavigationView navigationView;
    TextView nameUser;
    ImageView imgUser;
    UserDAO userDAO;
    View mHeaderView;
    Users u;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u = new Users();
        NavigationView nv = findViewById(R.id.navtion);
        mHeaderView = nv.getHeaderView(0);
        navigationView = findViewById(R.id.navtion);

        fragmentManager = getSupportFragmentManager();
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDWR, R.string.closeDWR);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        onContextMenuClosed();

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        if (user.equals("Admin")) {

            navigationView.getMenu().findItem(R.id.DatHang).setVisible(false);
            navigationView.getMenu().findItem(R.id.DH).setVisible(false);
            navigationView.getMenu().findItem(R.id.LSDH).setVisible(false);


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
        HomeFragment frag10 = new HomeFragment();


        fragmentManager.beginTransaction().replace(R.id.container_fragMain, frag10).commit();
        toolbar.setTitle("Home");
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.QLLX:
                        replaceFragment(new QLyLoaiXe_Fragment());
                        toolbar.setTitle("Quản lí loại xe");

                        break;
                    case R.id.QLX:
                        toolbar.setTitle("Quản lí xe");
                        replaceFragment(new QLyXe_Fragment());


                        break;
                    case R.id.QLDH:
                        toolbar.setTitle("Quản lí đơn hàng");
                        replaceFragment(new QlyDonHang_Fragment());


                        break;
                    case R.id.DatHang:
                        toolbar.setTitle("Đặt hàng");
                        replaceFragment(new DatHang_Fragment());


                        break;
                    case R.id.DH:
                        toolbar.setTitle("Những đơn đang đặt");
                        replaceFragment(new DonHangCB_Fragment());


                        break;
                    case R.id.topXe:
                        toolbar.setTitle("Thống kê");
                        replaceFragment(new TinTucFragment());


                        break;
                    case R.id.doanhThu:
                        toolbar.setTitle("Doanh thu");
                        replaceFragment(new DoanhThu_Fragment());


                        break;
                    case R.id.QLND:
                        toolbar.setTitle("Quản lí người dùng");
                        replaceFragment(new QLyNguoi_Dung_Fragment());


                        break;
                    case R.id.doiPass:
                        toolbar.setTitle("Đổi mật khẩu");
                        replaceFragment(new Doi_Mat_Khau_Fragment());


                        break;

                    case R.id.home:
                        toolbar.setTitle("Home");
                        replaceFragment(new HomeFragment());


                        break;
                    case R.id.toi:
                        toolbar.setTitle("Tài Khoản");
                        replaceFragment(new ToiFragment());


                        break;
                    case R.id.TB:
                        toolbar.setTitle("Thông báo");
                        replaceFragment(new yeu_cau_Fragment());


                        break;
                    case R.id.LSDH:
                        toolbar.setTitle("Lịch sử đặt hàng");
                        replaceFragment(new LSDH_Fragment());


                        break;
                    case R.id.phanHoi:
                        toolbar.setTitle("Phản hồi của khách hàng");
                        replaceFragment(new PhanHoiFragment());


                        break;
                    case R.id.dangXuat:
                        startActivity(new Intent(MainActivity.this, Login_MainActivity2.class));
                        finish();


                        break;
                }
                drawerLayout.closeDrawers();

                return false;
            }
        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_fragMain, fragment).commit();
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
                            ==PackageManager.PERMISSION_GRANTED&&
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    ==PackageManager.PERMISSION_GRANTED&&
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    ==PackageManager.PERMISSION_GRANTED
            ){

                return true;
            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.INTERNET,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                                },1);
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
    private void onContextMenuClosed() {
    }
}


