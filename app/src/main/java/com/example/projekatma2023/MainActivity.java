package com.example.projekatma2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekatma2023.baza.DBUtil;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    Dialog loginDialog;
    Dialog registerDialog;
    Dialog profileDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDialog = new Dialog(this);
        registerDialog= new Dialog(this);
        profileDialog = new Dialog(this);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    public void showLoginPopup(View v){

        ImageView closeBtn;
        TextView registerTxt;
        closeBtn = (ImageView) loginDialog.findViewById(R.id.closeButtonLogo);
        Button prijavaBtn;
        Button prijavaBtnPopup;
        loginDialog.setContentView(R.layout.activity_login);
        closeBtn = (ImageView) loginDialog.findViewById(R.id.closeButtonLogo);
        prijavaBtnPopup = (Button) loginDialog.findViewById(R.id.loginbtn);




        closeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
            }
        });
        loginDialog.show();


    }

    public void showRegisterPopup(View v){
        TextView registerTxt;
        ImageView closeBtn;
        TextView loginTxt;
        loginTxt = (TextView)registerDialog.findViewById(R.id.logIn);
        registerTxt = (TextView)loginDialog.findViewById(R.id.signUp);
        closeBtn = (ImageView) registerDialog.findViewById(R.id.closeButtonLogo);
        registerDialog.setContentView(R.layout.activity_register);


//        closeBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                registerDialog.dismiss();
//            }
//        });
        registerDialog.show();


    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Odjava!", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}