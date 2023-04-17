package com.anshul.socialchat.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.anshul.socialchat.Fragments.HomeFragment;
import com.anshul.socialchat.Fragments.ProfileFragment;
import com.anshul.socialchat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    Fragment active;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();

        HomeFragment homeFragment=new HomeFragment();
        ProfileFragment profileFragment=new ProfileFragment();

        active=homeFragment;

        frameLayout=findViewById(R.id.main_frame_layout);
        bottomNavigationView=findViewById(R.id.bottomNavigation);

        fm=getSupportFragmentManager();

        fm.beginTransaction().add(R.id.main_frame_layout,homeFragment).commit();
        fm.beginTransaction().add(R.id.main_frame_layout,profileFragment).hide(profileFragment).commit();


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected=null;
                switch (item.getItemId()){
                    case R.id.profile_bottom_nav:
                        fm.beginTransaction().hide(active).show(profileFragment).commit();
                        active=profileFragment;
                        break;
                    case R.id.home_bottom_nav:
                        fm.beginTransaction().hide(active).show(homeFragment).commit();
                        active=homeFragment;
                        break;
                }


                return true;
            }
        });
    }
}