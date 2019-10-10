package com.globalsoft.nipunatha.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.customerForgot;
import com.globalsoft.nipunatha.bean.customerLogin;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
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

public class CustomerForgotActivity extends AppCompatActivity {
    AppCompatImageButton imgback;
    TextView txttitle;
    TextInputEditText input_email;
    Button btn_resetpwd;
    String stremail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_activity);
        btn_resetpwd = (Button) findViewById(R.id.btn_resetpwd);
        input_email = (TextInputEditText) findViewById(R.id.input_email);
        txttitle = (TextView) findViewById(R.id.title);
        imgback = (AppCompatImageButton) findViewById(R.id.imgbtn_back);
        txttitle.setText("Customer Forgot Password");
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerForgotActivity.this, CustomerLoginActivity.class);
                startActivity(i);

            }
        });
        btn_resetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stremail = input_email.getText().toString().trim();
                if (!stremail.isEmpty() && stremail != null
                        && stremail != "") {
                    if (input_email.getText().toString().trim().matches(emailPattern)) {
                        ConnectivityManager cn = (ConnectivityManager) CustomerForgotActivity.this
                                .getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo nf = cn.getActiveNetworkInfo();
                        if (nf != null && nf.isConnected() == true) {
                            forgot(Constants.APPID, Constants.APPKEY, stremail);
                        } else {
                            Toast.makeText(CustomerForgotActivity.this,
                                    "Network Not Available",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CustomerForgotActivity.this,
                                "Please Enter  Valid Email",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CustomerForgotActivity.this,
                            "Please Enter Email",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
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
    private void forgot(String appid, String appkey, String stremail) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerForgot> mService = mApiService.customerForgot(appid, appkey,stremail);
            mService.enqueue(new Callback<customerForgot>() {
                @Override
                public void onResponse(Call<customerForgot> call, Response<customerForgot> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                            Toast.makeText(CustomerForgotActivity.this, message, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(CustomerForgotActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerForgot> call, Throwable t) {
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
        Intent i = new Intent(CustomerForgotActivity.this,CustomerLoginActivity.class);
        startActivity(i);
    }
}
