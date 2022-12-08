
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
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    ViewpageAdapter adapter;
    NavigationView navigationView;
    TextView nameUser;
    ImageView imgUser;
    UserDAO userDAO;
    View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //imgUser.set
        //Log.d("zzzz", name);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDWR, R.string.closeDWR);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        onContextMenuClosed();


    public void addFragment(ViewPager viewPager) {
        

        QLyLoaiXe_Fragment frag01 = new QLyLoaiXe_Fragment();
        QLyXe_Fragment frag02 = new QLyXe_Fragment();
        QlyDonHang_Fragment frag03 = new QlyDonHang_Fragment();
        DatHang_Fragment frag04 = new DatHang_Fragment();
        LSDonHang_Fragment frag05 = new LSDonHang_Fragment();
        ThongkeDH_Fragment frag06 = new ThongkeDH_Fragment();
        DoanhThu_Fragment frag07 = new DoanhThu_Fragment();
        QLyNguoi_Dung_Fragment frag08 = new QLyNguoi_Dung_Fragment();
        Doi_Mat_Khau_Fragment frag09 = new Doi_Mat_Khau_Fragment();
        HomeFragment frag10 = new HomeFragment();
        TinTucFragment frag11 = new TinTucFragment();
        ToiFragment frag12 = new ToiFragment();
        yeu_cau_Fragment frag13 = new yeu_cau_Fragment();
        LSDH_Fragment frag14 = new LSDH_Fragment();
        PhanHoiFragment frag15 = new PhanHoiFragment();

        fragmentManager.beginTransaction().replace(R.id.container_fragMain, frag10).commit();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()) {

                    case R.id.QLLX:
                        setTitle("Quản lí loại xe");
                        Toast.makeText(MainActivity.this, "Quản lí loại xe", Toast.LENGTH_SHORT).show();

                        QLyLoaiXe_Fragment qLyLoaiXe_fragment = new QLyLoaiXe_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, qLyLoaiXe_fragment)
                                .commit();

                        break;
                    case R.id.QLX:
                        setTitle("Quản lí xe");
                        Toast.makeText(MainActivity.this, "Quản lí xe", Toast.LENGTH_SHORT).show();

                        QLyXe_Fragment qLyXe_fragment = new QLyXe_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, qLyXe_fragment)
                                .commit();

                        break;
                    case R.id.QLDH:
                        setTitle("Quản lí đơn hàng");
                        Toast.makeText(MainActivity.this, "Quản lí đơn hàng", Toast.LENGTH_SHORT).show();

                        QlyDonHang_Fragment qlyDonHang_fragment = new QlyDonHang_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, qlyDonHang_fragment)
                                .commit();

                        break;
                    case R.id.DatHang:
                        setTitle("Đặt hàng");
                        Toast.makeText(MainActivity.this, "Đặt hàng", Toast.LENGTH_SHORT).show();

                        DatHang_Fragment datHang_fragment1 = new DatHang_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, datHang_fragment1)
                                .commit();

                        break;
                    case R.id.DH:
                        setTitle("Những đơn đã đặt");
                        Toast.makeText(MainActivity.this, "Những đơn đã đặt", Toast.LENGTH_SHORT).show();

                        LSDonHang_Fragment lsDonHang_fragment = new LSDonHang_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, lsDonHang_fragment)
                                .commit();

                        break;
                    case R.id.topXe:
                        setTitle("Top xe được thuê nhiều nhất");
                        Toast.makeText(MainActivity.this, "Top xe được thuê nhiều nhất", Toast.LENGTH_SHORT).show();

                        TinTucFragment tinTucFragment = new TinTucFragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, tinTucFragment)
                                .commit();

                        break;
                    case R.id.doanhThu:
                        setTitle("Doanh thu");
                        Toast.makeText(MainActivity.this, "Doanh thu", Toast.LENGTH_SHORT).show();

                        DoanhThu_Fragment doanhThu_fragment = new DoanhThu_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, doanhThu_fragment)
                                .commit();

                        break;
                    case R.id.QLND:
                        setTitle("Quản lí người dùng");
                        Toast.makeText(MainActivity.this, "Quản lí người dùng", Toast.LENGTH_SHORT).show();

                        QLyNguoi_Dung_Fragment qLyNguoi_dung_fragment = new QLyNguoi_Dung_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, qLyNguoi_dung_fragment)
                                .commit();

                        break;
                    case R.id.doiPass:
                        setTitle("Đổi mật khẩu");
                        Toast.makeText(MainActivity.this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();

                        Doi_Mat_Khau_Fragment doi_mat_khau_fragment = new Doi_Mat_Khau_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, doi_mat_khau_fragment)
                                .commit();

                        break;

                    case R.id.home:
                        setTitle("Home");
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();

                        HomeFragment homeFragment = new HomeFragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, homeFragment)
                                .commit();

                        break;
                    case R.id.toi:
                        setTitle("Tài Khoản");
                        Toast.makeText(MainActivity.this, "Tài Khoản", Toast.LENGTH_SHORT).show();

                        ToiFragment toiFragment = new ToiFragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, toiFragment)
                                .commit();

                        break;
                    case R.id.TB:
                        setTitle("Yêu Cầu");
                        Toast.makeText(MainActivity.this, "Yêu Cầu", Toast.LENGTH_SHORT).show();

                        yeu_cau_Fragment yeu_cau_fragment = new yeu_cau_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, yeu_cau_fragment)
                                .commit();

                        break;
                    case R.id.LSDH:
                        setTitle("LSDH");
                        Toast.makeText(MainActivity.this, "LSDH", Toast.LENGTH_SHORT).show();

                        LSDH_Fragment lsdh_fragment = new LSDH_Fragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, lsdh_fragment)
                                .commit();

                        break;
                    case R.id.phanHoi:
                        setTitle("Phản hồi của khách hàng");
                        Toast.makeText(MainActivity.this, "Phản hồi của khách hàng", Toast.LENGTH_SHORT).show();

                        PhanHoiFragment phanHoiFragment = new PhanHoiFragment();
                        manager.beginTransaction()
                                .replace(R.id.container_fragMain, phanHoiFragment)
                                .commit();

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
    private void onContextMenuClosed() {

    }
}