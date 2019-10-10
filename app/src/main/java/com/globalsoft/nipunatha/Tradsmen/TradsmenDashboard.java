package com.globalsoft.nipunatha.Tradsmen;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.customer.CustomerChangepassword;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class TradsmenDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String primarytrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tradsmendashboard_main);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences pref = getSharedPreferences(
                "MyPref", 0);
        primarytrades = pref.getString("primarytradestradsmen", "");
        Menu menu = navigationView.getMenu();
        menu.add(R.id.group1, Menu.NONE, Menu.NONE, primarytrades);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // toolbar.setTitle("SERVICES");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.group1);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tradsmen_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profile:
                Fragment fragment = new TradsmenProfileFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                // Toast.makeText(getApplicationContext(),"Android Clicked",Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_profilesetting:
                Fragment fragment1 = new TradsmenProfileEdit();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.content_frame, fragment1);
                transaction1.addToBackStack(null);
                transaction1.commit();
                return true;

            case R.id.action_pwd:
                Fragment fragmentchangepwd = new TradsmenChangepassword();
                FragmentTransaction transactionchangepwd = getSupportFragmentManager().beginTransaction();
                transactionchangepwd.replace(R.id.content_frame, fragmentchangepwd);
                transactionchangepwd.addToBackStack(null);
                transactionchangepwd.commit();
                return true;

            case R.id.action_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure do you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        Intent intent = new Intent(
                                                TradsmenDashboard.this, TradsmenLoginActivity.class);

                                        startActivity(intent);
                                        finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            default:

                super.onOptionsItemSelected(item);

        }
        return true;

    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.group1:
                Fragment fragment1= new Tradsmenjobs();
                FragmentTransaction transactionchangepwd = getSupportFragmentManager().beginTransaction();
                transactionchangepwd.replace(R.id.content_frame, fragment1);
                //transactionchangepwd.addToBackStack(null);
                transactionchangepwd.commit();
                break;
            case R.id.nav_tradsmenclosedjobs:
                fragment = new Tradsmenclosedjobs();
                break;
            case R.id.nav_tradsmenconfirmedjobs:
                fragment = new TradsmenConfirmedjobs();
                break;
            case R.id.nav_tradsmenmsgs:
                fragment = new Tradsmenmsgs();
                break;
            case R.id.nav_tredsmenmatchingjobs:
                fragment = new TradsmenMatchingjobs();
                break;
            case R.id.nav_tradsmencontacts:
                fragment = new Trdsmencontacts();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
           ft.addToBackStack(null);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
