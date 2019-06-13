package com.example.nssapplication;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

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
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new home()).commit();
                Toast.makeText( this,"Home is called", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Acrivity_Report_Menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new ActivityReport()).commit();
                Toast.makeText( this," Acitivity Report is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Activity_Photos_Menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_Id,new Activity_Photo()).commit();
                Toast.makeText( this," Activity Photos is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Program_Officier_Menu:
                Toast.makeText( this," Program Officer is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.About_Us_Menu:
                Toast.makeText( this," About Us is clicked", Toast.LENGTH_SHORT).show();
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
            super.onBackPressed();
        }
    }

}
