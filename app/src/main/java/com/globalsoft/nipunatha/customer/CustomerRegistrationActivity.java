package com.globalsoft.nipunatha.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.SelectActivity;
import com.globalsoft.nipunatha.bean.citiesbean;
import com.globalsoft.nipunatha.bean.customerregister;
import com.globalsoft.nipunatha.bean.statesbean;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
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

public class CustomerRegistrationActivity extends AppCompatActivity {
    TextInputLayout firstnameinputLayout, lastnameinputLayout, emailinputlayout, mobilenumberinputlayout, cityinputlayout, pincodeinputlayout;
    Button register;
    TextInputEditText edtfirstname, edtlastname, edtemail, edtmobile, edtpincode, edtarea;
    Spinner spinnercities, spinnerstates;
    String strspinnercities, stringspinnerstates, strcityidservice,firstname, lastname, mobilenumber, email, strpincode, strstateid, strcity, strcityid;
    ArrayList<String> customerlogin = new ArrayList<String>();
    ArrayList<String> customerloginID = new ArrayList<String>();
    ArrayList<String> customercities = new ArrayList<String>();
    AppCompatImageButton imgback;
    TextView txt_login, txttitle;
    String regid,names;
    String otp;
    int uid;
    ArrayAdapter<String> adapterstatets;
    ArrayAdapter<String> adaptercity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerregister_main);
        txttitle = (TextView) findViewById(R.id.title);
        txt_login = (TextView) findViewById(R.id.txt_login);
        imgback = (AppCompatImageButton) findViewById(R.id.imgbtn_back);
        firstnameinputLayout = (TextInputLayout) findViewById(R.id.inputLayout_firstName);
        lastnameinputLayout = (TextInputLayout) findViewById(R.id.inputLayout_lastName);
        emailinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_email);
        mobilenumberinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_mobile);
        cityinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_area);
        pincodeinputlayout = (TextInputLayout) findViewById(R.id.inputLayout_pincode);
        register = (Button) findViewById(R.id.btn_register);
        spinnercities = (Spinner) findViewById(R.id.spnr_city);
        spinnerstates = (Spinner) findViewById(R.id.spnr_state);
        edtfirstname = (TextInputEditText) findViewById(R.id.input_firstName);
        edtlastname = (TextInputEditText) findViewById(R.id.input_lastName);
        edtemail = (TextInputEditText) findViewById(R.id.input_email);
        edtmobile = (TextInputEditText) findViewById(R.id.input_mobile);
        edtpincode = (TextInputEditText) findViewById(R.id.input_pincode);
        edtarea = (TextInputEditText) findViewById(R.id.input_area);
        txttitle.setText("Customer Register");
        customercities.add(0, "- SELECT CITY -");
        adaptercity= new ArrayAdapter<String>(CustomerRegistrationActivity.this, R.layout.my_spinner, customercities);
        spinnercities.setAdapter(adaptercity);
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerRegistrationActivity.this, CustomerLoginActivity.class);
                SharedPreferences pref = getApplicationContext()
                        .getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("regid", regid);
                editor.putString("otp", otp);
                editor.putInt("uid", uid);
                editor.commit();
                startActivity(i);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerRegistrationActivity.this, SelectActivity.class);
                startActivity(i);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    firstname = edtfirstname.getText().toString().trim();
                    lastname = edtlastname.getText().toString().trim();
                    mobilenumber = edtmobile.getText().toString().trim();
                    email = edtemail.getText().toString().trim();
                    strpincode = edtpincode.getText().toString().trim();
                    strspinnercities = spinnercities.getSelectedItem().toString().trim();
                    stringspinnerstates = spinnerstates.getSelectedItem().toString().trim();
                    strcity = edtarea.getText().toString().trim();

                    if(firstname == null||firstname.isEmpty()
                            || firstname.equalsIgnoreCase("") || firstname.length() == 0){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Your First Name",
                                Toast.LENGTH_SHORT).show();
                    }else if (lastname.isEmpty() || lastname == null
                            || lastname .equalsIgnoreCase("")||lastname.length()==0){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Your Last Name",
                                Toast.LENGTH_SHORT).show();
                    } else if(email.isEmpty() || email == null || email.equalsIgnoreCase("")||email.length()==0){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Email",
                                Toast.LENGTH_SHORT).show();
                    }else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()==false){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter valid Email",
                                Toast.LENGTH_SHORT).show();
                    } else if(mobilenumber.isEmpty() || mobilenumber == null
                            || mobilenumber.equalsIgnoreCase("") || mobilenumber.length()==0){

                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Mobile Number",
                                Toast.LENGTH_SHORT).show();
                    }else if(Patterns.PHONE.matcher(mobilenumber).matches()==false || mobilenumber.length()!=10){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter valid Mobile number",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(strpincode.isEmpty() || strpincode == null || strpincode.equalsIgnoreCase("")||strpincode.length()==0){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Pincode",
                                Toast.LENGTH_SHORT).show();
                    }else if(strpincode.length()!=6){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter valid Pincode",
                                Toast.LENGTH_SHORT).show();
                    }else if(stringspinnerstates.equalsIgnoreCase("- SELECT STATE -")){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Select State", Toast.LENGTH_SHORT).show();
                    }else if(strspinnercities.equalsIgnoreCase("- SELECT CITY -")){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Select City", Toast.LENGTH_SHORT).show();
                    }
                    else if(strcity.isEmpty() || strcity == null || strcity.equalsIgnoreCase("")){
                        Toast.makeText(CustomerRegistrationActivity.this,
                                "Please Enter Your Area", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ConnectivityManager cn = (ConnectivityManager) CustomerRegistrationActivity.this
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo nf = cn.getActiveNetworkInfo();
                        if (nf != null && nf.isConnected() == true) {
                            registerservice(Constants.APPID, Constants.APPKEY, firstname, strcity, lastname, mobilenumber, email, strpincode, strstateid, strcityidservice);
                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this,
                                    "Network Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {

                }
            }
        });

        ConnectivityManager cn = (ConnectivityManager) CustomerRegistrationActivity.this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            loadspinnerstates();
        } else {
            Toast.makeText(CustomerRegistrationActivity.this,
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }
        spinnerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               /* strstateid = customerloginID.get(i);
                loadspinnercity(strstateid);
                customercities.clear();*/
                strstateid = customerloginID.get(i);
                if(customercities.size()>0){
                    customercities.clear();
                }
                customercities.add(0, "- SELECT CITY -");

                loadspinnercity(strstateid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void registerservice(String appid, String appkey, final String firstname, final String strcity, final String lastname, final String mobilenumber, final String email, final String strpincode, final String strspinnercities, final String stringspinnerstates) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerregister> mService = mApiService.login(appid, appkey, firstname, lastname, email, mobilenumber, stringspinnerstates, strspinnercities, strcity, strpincode);
            mService.enqueue(new Callback<customerregister>() {
                @Override
                public void onResponse(Call<customerregister> call, Response<customerregister> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            regid = response.body().getReg_id();
                            uid = response.body().getUid();
                            otp = response.body().getOtp();
                            Intent i = new Intent(CustomerRegistrationActivity.this, Customerverifyotp.class);
                            SharedPreferences pref = getApplicationContext()
                                    .getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("regid", regid);
                            editor.putString("otp", otp);
                            editor.putString("firstname",firstname);
                            editor.putString("lastname",lastname);
                            editor.putString("email",email);
                            editor.putString("mobileno",mobilenumber);
                            editor.putString("pincode",strpincode);
                            editor.putString("state",stringspinnerstates);
                            editor.putString("city",strspinnercities);
                            editor.putString("area",strcity);
                            editor.putString("customer","customer");
                            editor.putInt("uid", uid);
                            editor.commit();
                            startActivity(i);


                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CustomerRegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerregister> call, Throwable t) {
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

    private void loadspinnercity(String strcityid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<citiesbean> mService = mApiService.Customercities(Constants.APPID, Constants.APPKEY, strcityid);
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
                                names = Items.get(i).getName(); // I want to show this when Selected
                                String statuscity = Items.get(i).getStatus(); // I want to show this when Selected
                                customercities.add(names);
                            }
                          //  customercities.add(0, "- SELECT CITY -");

                            adaptercity= new ArrayAdapter<String>(CustomerRegistrationActivity.this, R.layout.my_spinner, customercities);
                            spinnercities.setAdapter(adaptercity);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CustomerRegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

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


    private CustomerregisterInterface getservicedetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://labs.sslotus.com/services/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitSSLCertificate.getUnsafeOkHttpClient())
                .build();
        final CustomerregisterInterface mInterfaceService = retrofit.create(CustomerregisterInterface.class);
        return mInterfaceService;
    }

    private void loadspinnerstates() {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<statesbean> mService = mApiService.CustomerLogin(Constants.APPID, Constants.APPKEY);
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
                                strstateid = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                customerlogin.add(names);
                                customerloginID.add(strstateid);
                            }
                            customerlogin.add(0, "- SELECT STATE -");
                            customerloginID.add(0, "0");

                            adapterstatets = new ArrayAdapter<String>(CustomerRegistrationActivity.this, R.layout.my_spinner, customerlogin);
                            spinnerstates.setAdapter(adapterstatets);
                        } else {
                            Toast.makeText(CustomerRegistrationActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CustomerRegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

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
        Intent i = new Intent(CustomerRegistrationActivity.this, SelectActivity.class);
        startActivity(i);
    }
}
