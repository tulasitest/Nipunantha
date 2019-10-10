package com.globalsoft.nipunatha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.globalsoft.nipunatha.Tradsmen.TradsmenDashboard;
import com.globalsoft.nipunatha.Tradsmen.TradsmenLoginActivity;
import com.globalsoft.nipunatha.Tradsmen.TradsmenRegister;
import com.globalsoft.nipunatha.customer.CustomerDashboardActivity;
import com.globalsoft.nipunatha.customer.CustomerLoginActivity;
import com.globalsoft.nipunatha.customer.CustomerRegistrationActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity {
    Button btn_customer, btn_tradsmen, btn_skip;
    String email, password, regid, firstname, lastname, mobileno, pincode, state, city, area, Tradsmen, customer;
    int uid,uidtradsmen;
    String  primarytrades, noofemp,emailtradsmen,pwdtradsmen,regidtradsmen,
            firstnametradsmen,lastnametradsmen,mobilenotradsmen,pincodetradsmen,statetradsmen,citytradsmen,areatradsmen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_main);
        btn_customer = (Button) findViewById(R.id.btn_customer);
        btn_skip = (Button) findViewById(R.id.btn_guest);
        btn_tradsmen = (Button) findViewById(R.id.btn_tradesmen);
        final SharedPreferences pref = SelectActivity.this.getSharedPreferences(
                "MyPref", 0);
        Tradsmen = pref.getString("Tradsmen", "");
        customer = pref.getString("customer", "");
        if(!Tradsmen.isEmpty() && Tradsmen != null
                && Tradsmen != ""){
            regidtradsmen = pref.getString("regidtradsmen", "");
            emailtradsmen = pref.getString("emailtradsmen", "");
            pwdtradsmen = pref.getString("passwordtradsmen", "");
            firstnametradsmen = pref.getString("firstnametradsmen", "");
            lastnametradsmen = pref.getString("lastnametradsmen", "");
            mobilenotradsmen = pref.getString("mobilenotradsmen", "");
            pincodetradsmen = pref.getString("pincodetradsmen", "");
            statetradsmen = pref.getString("statetradsmen", "");
            citytradsmen = pref.getString("citytradsmen", "");
            areatradsmen = pref.getString("areatradsmen", "");
            primarytrades = pref.getString("primarytradestradsmen", "");
            noofemp = pref.getString("noofemptradsmen", "");
            uidtradsmen = pref.getInt("uidtradsmen", 0);


        }
        if(customer!=null && !customer.isEmpty()
                && customer != ""){
            regid = pref.getString("regid", "");
            email = pref.getString("email", "");
            password = pref.getString("password", "");
            firstname = pref.getString("firstname", "");
            lastname = pref.getString("lastname", "");
            mobileno = pref.getString("mobileno", "");
            pincode = pref.getString("pincode", "");
            state = pref.getString("state", "");
            city = pref.getString("city", "");
            area = pref.getString("area", "");
            uid = pref.getInt("uid", 0);

        }
            btn_customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (email == null || email.isEmpty()
                                || email.equalsIgnoreCase("") || email.length() == 0 && password == null || password.isEmpty()
                                || password.equalsIgnoreCase("") || password.length() == 0&& customer == null || customer.isEmpty()
                                || customer.equalsIgnoreCase("") || customer.length() == 0) {

                            Intent i = new Intent(SelectActivity.this, CustomerLoginActivity.class);
                            startActivity(i);

                        } else {

                            Intent i = new Intent(SelectActivity.this, CustomerDashboardActivity.class);
                            startActivity(i);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent i = new Intent(SelectActivity.this, CustomerRegistrationActivity.class);
                startActivity(i);*/
            }
        });
        btn_tradsmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (emailtradsmen == null || emailtradsmen.isEmpty()
                            || emailtradsmen.equalsIgnoreCase("") || emailtradsmen.length() == 0 && pwdtradsmen == null || pwdtradsmen.isEmpty()
                            || pwdtradsmen.equalsIgnoreCase("") || pwdtradsmen.length() == 0&& Tradsmen == null || Tradsmen.isEmpty()
                            || Tradsmen.equalsIgnoreCase("") || Tradsmen.length() == 0) {

                        Intent i = new Intent(SelectActivity.this, TradsmenLoginActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SelectActivity.this, TradsmenDashboard.class);
                        startActivity(i);

                    }
                } catch (Exception e) {
                    e.printStackTrace();


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}
