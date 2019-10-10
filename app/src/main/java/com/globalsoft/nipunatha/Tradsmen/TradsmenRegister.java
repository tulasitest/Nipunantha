package com.globalsoft.nipunatha.Tradsmen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.SelectActivity;
import com.globalsoft.nipunatha.bean.Tradsmenregister;
import com.globalsoft.nipunatha.bean.citiesbean;
import com.globalsoft.nipunatha.bean.customerregister;
import com.globalsoft.nipunatha.bean.statesbean;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;
import com.globalsoft.nipunatha.customer.CustomerLoginActivity;
import com.globalsoft.nipunatha.customer.CustomerRegistrationActivity;
import com.globalsoft.nipunatha.customer.Customerverifyotp;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TradsmenRegister extends AppCompatActivity {
    TextInputLayout firstnameinputLayout, lastnameinputLayout, emailinputlayout, mobilenumberinputlayout, cityinputlayout, pincodeinputlayout;
    Button register;
    TextInputEditText edtfirstname, edtlastname, edtemail, edtmobile, edtpincode, edtarea, inputnoOfEmployees;
    Spinner spinnercities, spinnerstates, spnrprimaryTrade;
    String strspinnercities,strcityidservice, primarytradesid,strinputnoOfEmployees, stringspinnerstates, stringprimarytrades, firstname, lastname, mobilenumber, email, strpincode, stridd, strcity, strcityid, strspinnerstateid;
    ArrayList<String> primarytrades = new ArrayList<String>();
    ArrayList<String> cities = new ArrayList<String>();
    ArrayList<String> states = new ArrayList<String>();
    ArrayList<String> customerloginID = new ArrayList<String>();
    AppCompatImageButton imgback;
    TextView txt_login, txttitle;
    ArrayAdapter<String> adaptercity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tradamenregister);
        txttitle = (TextView) findViewById(R.id.title);
        txt_login = (TextView) findViewById(R.id.txt_login);
        imgback = (AppCompatImageButton) findViewById(R.id.imgbtn_back);
        firstnameinputLayout = (TextInputLayout) findViewById(R.id.inputLayout_firstName);
        inputnoOfEmployees = (TextInputEditText) findViewById(R.id.input_noOfEmployees);
        lastnameinputLayout = (TextInputLayout) findViewById(R.id.inputLayout_lastName);
        emailinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_email);
        mobilenumberinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_mobile);
        cityinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_area);
        pincodeinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_pincode);
        register = (Button) findViewById(R.id.btn_register);
        spinnercities = (Spinner) findViewById(R.id.spnr_city);
        spinnerstates = (Spinner) findViewById(R.id.spnr_state);
        spnrprimaryTrade = (Spinner) findViewById(R.id.spnr_primaryTrade);
        edtfirstname = (TextInputEditText) findViewById(R.id.input_firstName);
        edtlastname = (TextInputEditText) findViewById(R.id.input_lastName);
        edtemail = (TextInputEditText) findViewById(R.id.input_email);
        edtmobile = (TextInputEditText) findViewById(R.id.input_mobile);
        edtpincode = (TextInputEditText) findViewById(R.id.input_pincode);
        edtarea = (TextInputEditText) findViewById(R.id.input_area);
        txttitle.setText("Tradsmen Register");
        cities.add(0, "- SELECT CITY -");
        adaptercity= new ArrayAdapter<String>(TradsmenRegister.this, R.layout.my_spinner, cities);
        spinnercities.setAdapter(adaptercity);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenRegister.this, TradsmenLoginActivity.class);
                startActivity(i);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenRegister.this, SelectActivity.class);
                startActivity(i);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstname = edtfirstname.getText().toString().trim();
                    lastname = edtlastname.getText().toString().trim();
                    email = edtemail.getText().toString().trim();
                    strinputnoOfEmployees = inputnoOfEmployees.getText().toString().trim();
                    mobilenumber = edtmobile.getText().toString().trim();
                    strpincode = edtpincode.getText().toString().trim();
                    strspinnercities = spinnercities.getSelectedItem().toString().trim();
                    stringspinnerstates = spinnerstates.getSelectedItem().toString().trim();
                    stringprimarytrades = spnrprimaryTrade.getSelectedItem().toString().trim();
                    strcity = edtarea.getText().toString().trim();
                    if(firstname == null||firstname.isEmpty()
                            || firstname.equalsIgnoreCase("") || firstname.length() == 0){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Your First Name",
                                Toast.LENGTH_SHORT).show();
                    }else if (lastname.isEmpty() || lastname == null
                            || lastname .equalsIgnoreCase("")||lastname.length()==0){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Your Last Name",
                                Toast.LENGTH_SHORT).show();
                    } else if(email.isEmpty() || email == null || email.equalsIgnoreCase("")||email.length()==0){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Email",
                                Toast.LENGTH_SHORT).show();
                    }else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()==false){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter valid Email",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(mobilenumber.isEmpty() || mobilenumber == null
                            || mobilenumber.equalsIgnoreCase("") || mobilenumber.length()==0){

                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Mobile Number",
                                Toast.LENGTH_SHORT).show();
                    }else if(Patterns.PHONE.matcher(mobilenumber).matches()==false || mobilenumber.length()!=10){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter valid Mobile number",
                                Toast.LENGTH_SHORT).show();
                    } else if(stringprimarytrades .equalsIgnoreCase("- SELECT SERVICE -")){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Select Services", Toast.LENGTH_SHORT).show();
                    }  else if(strinputnoOfEmployees .isEmpty() || strinputnoOfEmployees  == null
                            || strinputnoOfEmployees .equalsIgnoreCase("") || strinputnoOfEmployees .length()==0) {

                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter No of Employees",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(stringspinnerstates.equalsIgnoreCase("- SELECT STATE -")){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Select State", Toast.LENGTH_SHORT).show();
                    }else if(strspinnercities.equalsIgnoreCase("- SELECT CITY -")){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Select City", Toast.LENGTH_SHORT).show();
                    }else if(strcity.isEmpty() || strcity == null || strcity.equalsIgnoreCase("")||strcity.length()==0){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Your Area", Toast.LENGTH_SHORT).show();
                    }
                    else if(strpincode.isEmpty() || strpincode == null || strpincode.equalsIgnoreCase("")||strpincode.length()==0){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter Pincode",
                                Toast.LENGTH_SHORT).show();
                    }else if(strpincode.length()!=6){
                        Toast.makeText(TradsmenRegister.this,
                                "Please Enter valid Pincode",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        ConnectivityManager cn = (ConnectivityManager) TradsmenRegister.this
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo nf = cn.getActiveNetworkInfo();
                        if (nf != null && nf.isConnected() == true) {
                            registerservice(Constants.APPID,Constants.APPKEY, firstname, lastname, email, mobilenumber, strspinnerstateid, strcityidservice, strcity, strpincode, stridd, strinputnoOfEmployees);
                        } else {
                            Toast.makeText(TradsmenRegister.this,
                                    "Network Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        ConnectivityManager cnmanager = (ConnectivityManager) TradsmenRegister.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nfinfo = cnmanager.getActiveNetworkInfo();
        if (nfinfo != null && nfinfo.isConnected() == true) {
            loadspinnerservices();
        } else {
            Toast.makeText(TradsmenRegister.this,
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }
        ConnectivityManager cn = (ConnectivityManager) TradsmenRegister.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            loadspinnerstates();
        } else {
            Toast.makeText(TradsmenRegister.this,
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }

        spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              /*  strspinnerstateid = customerloginID.get(i);
                loadspinnercity(strspinnerstateid);
                cities.clear();*/
                strspinnerstateid = customerloginID.get(i);
                if (cities.size() > 0) {
                    cities.clear();

                }
                cities.add(0, "- SELECT CITY -");

                loadspinnercity(strspinnerstateid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private Tradsmen getservicelogindetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://labs.sslotus.com/services/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitSSLCertificate.getUnsafeOkHttpClient())
                .build();
        final Tradsmen mInterfaceService = retrofit.create(Tradsmen.class);
        return mInterfaceService;
    }

    private void registerservice(String appid, String appkey, final String firstname, final String lastname, final String email, final String mobilenumber, final String stringspinnerstates, final String strspinnercities, final String strcity, final String strpincode, final String stringprimarytrades, final String strinputnoOfEmployees) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicelogindetails();
            Call<Tradsmenregister> mService = mApiService.tradsmenregister(appid, appkey, firstname, lastname, email, mobilenumber, stringspinnerstates, strspinnercities, strcity, strpincode, stringprimarytrades, strinputnoOfEmployees);
            mService.enqueue(new Callback<Tradsmenregister>() {
                @Override
                public void onResponse(Call<Tradsmenregister> call, Response<Tradsmenregister> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            final String regid = response.body().getReg_id();
                            final int uid = response.body().getUid();
                            final String otp = response.body().getOtp();
                            Intent i = new Intent(TradsmenRegister.this, Tradsmenrverifyotp.class);
                            SharedPreferences pref = getApplicationContext()
                                    .getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("regidtradsmen", regid);
                            editor.putString("otptradsmen", otp);
                            editor.putInt("uidtradsmen", uid);
                            editor.putString("firstnametradsmen",firstname);
                            editor.putString("lastnametradsmen",lastname);
                            editor.putString("emailtradsmen",email);
                            editor.putString("mobilenotradsmen",mobilenumber);
                            editor.putString("pincodetradsmen",strpincode);
                            editor.putString("statetradsmen",stringspinnerstates);
                            editor.putString("citytradsmen",strspinnercities);
                            editor.putString("primarytradestradsmen",stringprimarytrades);
                            editor.putString("noofemptradsmen",strinputnoOfEmployees);
                            editor.putString("areatradsmen",strcity);
                            editor.putString("Tradsmen","Tradsmen");
                            editor.commit();
                            startActivity(i);


                        } else {
                            Toast.makeText(TradsmenRegister.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TradsmenRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Tradsmenregister> call, Throwable t) {
                    call.cancel();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private CustomerregisterInterface getservicedetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://labs.sslotus.com/services/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitSSLCertificate.getUnsafeOkHttpClient())
                .build();
        final CustomerregisterInterface mInterfaceService = retrofit.create(CustomerregisterInterface.class);
        return mInterfaceService;
    }

    private void loadspinnerservices() {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<tradsmenprimarytrades> mService = mApiService.servicestradsmenregister(Constants.APPID, Constants.APPKEY);
            mService.enqueue(new Callback<tradsmenprimarytrades>() {
                @Override
                public void onResponse(Call<tradsmenprimarytrades> call, Response<tradsmenprimarytrades> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            List<tradsmenprimarytrades.ResponseBean> Items = response.body().getResponse();

                            for (int i = 0; i < Items.size(); i++) {
                                 primarytradesid = Items.get(i).getName(); // I want to show this when Selected
                                stridd = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                primarytrades.add(primarytradesid);
                            }
                            primarytrades.add(0, "- PRIMARY TRADES -");

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TradsmenRegister.this, R.layout.my_spinner, primarytrades);
                            spnrprimaryTrade.setAdapter(adapter);
                        } else {
                            Toast.makeText(TradsmenRegister.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TradsmenRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<tradsmenprimarytrades> call, Throwable t) {
                    call.cancel();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void loadspinnercity(String strid) {
        {
            try {
                final ProgressDialog mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                CustomerregisterInterface mApiService = this.getservicedetails();
                Call<citiesbean> mService = mApiService.Customercities(Constants.APPID,Constants.APPKEY, strid);
                mService.enqueue(new Callback<citiesbean>() {
                    @Override
                    public void onResponse(Call<citiesbean> call, Response<citiesbean> response) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        try {
                            String status = response.body().getStatus();
                            String message = response.body().getMessage();
                            if (status.equals("valid")) {
                                List<citiesbean.ResponseBean> Items = response.body().getResponse();

                                for (int i = 0; i < Items.size(); i++) {
                                    strcityidservice = Items.get(i).getId(); // I want to show this when Selected
                                    String names = Items.get(i).getName(); // I want to show this when Selected
                                    String statuscity = Items.get(i).getStatus(); // I want to show this when Selected
                                    cities.add(names);
                                }
                                //     cities.add(0, "- SELECT CITY -");

                               adaptercity = new ArrayAdapter<String>(TradsmenRegister.this, R.layout.my_spinner, cities);
                                spinnercities.setAdapter(adaptercity);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(TradsmenRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<citiesbean> call, Throwable t) {
                        call.cancel();
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    private void loadspinnerstates() {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<statesbean> mService = mApiService.CustomerLogin(Constants.APPID,Constants.APPKEY);
            mService.enqueue(new Callback<statesbean>() {
                @Override
                public void onResponse(Call<statesbean> call, Response<statesbean> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            List<statesbean.ResponseBean> Items = response.body().getResponse();

                            for (int i = 0; i < Items.size(); i++) {
                                String names = Items.get(i).getName(); // I want to show this when Selected
                                strspinnerstateid = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                states.add(names);
                                customerloginID.add(strspinnerstateid);

                            }
                            states.add(0, "- SELECT STATE -");
                            customerloginID.add(0, "0");


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TradsmenRegister.this, R.layout.my_spinner, states);
                            spinnerstates.setAdapter(adapter);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TradsmenRegister.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<statesbean> call, Throwable t) {
                    call.cancel();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(TradsmenRegister.this, SelectActivity.class);
        startActivity(i);
    }
}
