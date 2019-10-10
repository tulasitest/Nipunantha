package com.globalsoft.nipunatha.Tradsmen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.SelectActivity;
import com.globalsoft.nipunatha.bean.customerLogin;
import com.globalsoft.nipunatha.bean.tradsmenLogin;
import com.globalsoft.nipunatha.customer.CustomerDashboardActivity;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TradsmenLoginActivity extends AppCompatActivity {
    TextInputEditText email, password;
    TextView txttitle, txtregister, txtforgotpassword;
    Button btnlogin;
    String stremail, strpassword;
    AppCompatImageButton imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin = (Button) findViewById(R.id.btn_login);
        txttitle = (TextView) findViewById(R.id.title);
        email = (TextInputEditText) findViewById(R.id.input_email);
        password = (TextInputEditText) findViewById(R.id.input_password);
        imgback = (AppCompatImageButton) findViewById(R.id.imgbtn_back);
        txtregister = (TextView) findViewById(R.id.txt_register);
        txtforgotpassword = (TextView) findViewById(R.id.txt_forgotpassword);
        txttitle.setText("Tradsmen Login");
        email.setText("susmitha.adaveni@gmail.com");
        password.setText("123456");
        txtforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenLoginActivity.this, TradsmenForgotpassword.class);
                startActivity(i);
            }
        });
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenLoginActivity.this, TradsmenRegister.class);
                startActivity(i);
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenLoginActivity.this, SelectActivity.class);
                startActivity(i);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stremail = email.getText().toString().trim();
                strpassword = password.getText().toString().trim();
                if (!stremail.isEmpty() && stremail != null
                        && stremail != "") {
                    if (!strpassword.isEmpty() && strpassword != null
                            && strpassword != "") {
                        ConnectivityManager cn = (ConnectivityManager) TradsmenLoginActivity.this
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo nf = cn.getActiveNetworkInfo();
                        if (nf != null && nf.isConnected() == true) {
                            login(Constants.APPID, Constants.APPKEY, stremail, strpassword);
                        } else {
                            Toast.makeText(TradsmenLoginActivity.this,
                                    "Network Not Available",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TradsmenLoginActivity.this,
                                "Please Enter Password",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TradsmenLoginActivity.this,
                            "Please Enter Email",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Tradsmen getservicedetails() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://labs.sslotus.com/services/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitSSLCertificate.getUnsafeOkHttpClient())
                .build();
        final Tradsmen mInterfaceService = retrofit.create(Tradsmen.class);
        return mInterfaceService;
    }

    private void login(String appid, String appkey, final String stremail, final String strpassword) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicedetails();
            Call<tradsmenLogin> mService = mApiService.tradesmenLogin(appid, appkey, stremail, strpassword);
            mService.enqueue(new Callback<tradsmenLogin>() {
                @Override
                public void onResponse(Call<tradsmenLogin> call, Response<tradsmenLogin> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            String uid = response.body().getResult().getUid();
                            String reg_id = response.body().getResult().getReg_id();
                            String email = response.body().getResult().getEmail();
                            String name = response.body().getResult().getName();
                            String service_id = (String) response.body().getResult().getService_id();
                            String service_name = (String) response.body().getResult().getService_name();

                            Toast.makeText(TradsmenLoginActivity.this, "Successfully Logged On", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(TradsmenLoginActivity.this, TradsmenDashboard.class);
                            SharedPreferences pref = getApplicationContext()
                                    .getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("regidtradsmen", reg_id);
                            editor.putString("emailtradsmen",stremail);
                            editor.putString("passwordtradsmen",strpassword);
                            editor.putString("serviceid",service_id);
                            editor.putString("servicename",service_name);

                            editor.putInt("uidtradsmen", Integer.parseInt(uid));
                            editor.commit();
                            startActivity(i);

                        } else {
                            Toast.makeText(TradsmenLoginActivity.this, message, Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TradsmenLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<tradsmenLogin> call, Throwable t) {
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
        Intent i = new Intent(TradsmenLoginActivity.this, SelectActivity.class);
        startActivity(i);
    }
}
