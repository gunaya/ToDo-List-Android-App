package com.zucc.todolist.admin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.Fragment;

import com.zucc.todolist.R;
import com.zucc.todolist.admin.fragment.DashboardFragment;
import com.zucc.todolist.admin.fragment.HomeFragment;
import com.zucc.todolist.admin.fragment.NotifFragment;

public class FragmentActivity extends AppCompatActivity {


    BottomNavigationView btn_navView;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btn_navView = findViewById(R.id.navigation);
        btn_navView.setOnNavigationItemSelectedListener(navListener);
        fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragment = null;
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = new NotifFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            return true;
//                case R.id.navigation_home:
//                    HomeFragment fragment = new HomeFragment();
//                    setTitle("Home");
//                    FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
//                    fragmentTransaction1.replace(R.id.frameLayout, fragment);
//                    fragmentTransaction1.commit();
//                    return true;
//                case R.id.navigation_dashboard:
//                    DashboardFragment dashboardFragment = new DashboardFragment();
//                    setTitle("dashboard");
//                    FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
//                    fragmentTransaction2.replace(R.id.frameLayout, DashboardFragment);
//                    fragmentTransaction2.commit();
//                    return true;
//                case R.id.navigation_notifications:
//                    NotifFragment
//                            fragment3 = new NotifFragment();
//                    setTitle("notif");
//                    FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
//                    fragmentTransaction3.replace(R.id.frameLayout, fragment3);
//                    fragmentTransaction3.commit();
//                    return true;
//            }
//            return false;
        }
    };

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure to exit?")
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FragmentActivity.super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        HomeFragment fragment = new HomeFragment();
//        setTitle("Home");
//        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
//        fragmentTransaction1.replace(R.id.frameLayout, fragment);
//        fragmentTransaction1.commit();
//
//
//    }
//
//}
