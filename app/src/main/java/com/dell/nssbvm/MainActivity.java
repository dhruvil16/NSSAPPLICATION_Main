package com.dell.nssbvm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.Toolbar_Id);

        toolbar.setTitle(R.string.home_activity_string);

        drawerLayout = findViewById(R.id.Drawer_Layout_Id);

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);

        if(drawerLayout!=null)
        {
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
        }
        actionBarDrawerToggle.syncState();

        nav = findViewById(R.id.Navigation_View_Id);

        if(nav!=null)
        {
            nav.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.Home_Menu:
                toolbar.setTitle(R.string.home_activity_string);
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();
                break;
            case R.id.Acrivity_Report_Menu:
                toolbar.setTitle(R.string.report_activty_string);
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new ActivityReport()).commit();
                break;
            case R.id.Activity_Photos_Menu:
                toolbar.setTitle(R.string.photo_activity_string);
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new Activity_Photo()).commit();
                break;
            case R.id.Program_Officier_Menu:
                toolbar.setTitle(R.string.Program_officier_activity_string);
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new ProgramOfficerActivity()).commit();
                break;
            case R.id.About_Us_Menu:
                toolbar.setTitle(R.string.about_us_activity_string);
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new AboutUsFragment()).commit();
                break;
            case R.id.Logout_Menu:
                Intent intent = new Intent(this,Login_Activity.class);

                SharedPreferences.Editor editor = getSharedPreferences("CheckForAlredyLogin",MODE_PRIVATE).edit();
                editor.putBoolean("AlreadyLogin",false);
                editor.apply();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START,true);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{

            List fragmentList = getSupportFragmentManager().getFragments();
            boolean handle = false;

            for(Object f : fragmentList)
            {
                if(f instanceof FragmentThatRespondToBackButton){
                    handle = ((FragmentThatRespondToBackButton)f).onBackPressed();
                    if(handle)
                        break;
                }
            }

            if(!handle)
                super.onBackPressed();
        }


    }

}
