package com.e_haber;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment; // TEMEL FRAGMENT SINIFI İÇİN IMPORT
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.e_haber.Fragments.DuyuruFragment;
import com.e_haber.Fragments.HomeFragment;
import com.e_haber.Fragments.KategoriFragment;
import com.e_haber.Fragments.VideoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        frameLayout=findViewById(R.id.frameLayout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.home){
                    loadfragment(new HomeFragment());

                }else if(id==R.id.Kategori) {
                    loadfragment(new KategoriFragment());
                } else if (id == R.id.Video) {
                    loadfragment(new VideoFragment());
                } else if (id == R.id.menu_duyuru) {
                    loadfragment(new DuyuruFragment());
                }
                return true;
            }

        });
        loadfragment(new HomeFragment());

    }
    private void loadfragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}