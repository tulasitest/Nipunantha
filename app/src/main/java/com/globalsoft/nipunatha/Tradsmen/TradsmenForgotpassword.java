package com.globalsoft.nipunatha.Tradsmen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.customerForgot;
import com.globalsoft.nipunatha.customer.CustomerForgotActivity;
import com.globalsoft.nipunatha.customer.CustomerLoginActivity;
import com.globalsoft.nipunatha.customer.Customerverifyotp;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;

public class TradsmenForgotpassword extends AppCompatActivity {
    Button btn_resetpwd;
    TextInputEditText input_email;
    String stremail,stremailvalidation;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AppCompatImageButton imgback;
    TextView txttitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_activity);
        btn_resetpwd = (Button)findViewById(R.id.btn_resetpwd);
        input_email = (TextInputEditText)findViewById(R.id.input_email);
        imgback=(AppCompatImageButton)findViewById(R.id.imgbtn_back);
        txttitle = (TextView) findViewById(R.id.title);

        txttitle.setText("Tradsmen Forgot Password");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TradsmenForgotpassword.this, TradsmenLoginActivity.class);
                startActivity(i);
            }
        });
        btn_resetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stremail = input_email.getText().toString().trim();
                if (!stremail.isEmpty() && stremail != null  && stremail != ""){
                    if (input_email.getText().toString().trim().matches(emailPattern)) {
                        forgotservice(Constants.APPID, Constants.APPKEY, stremail);
                    }else{
                        Toast.makeText(TradsmenForgotpassword.this,"Please Enter Valid Email",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(TradsmenForgotpassword.this,"Please Enter Your Email",Toast.LENGTH_LONG).show();
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
    private void forgotservice(String appid, String appkey, String stremail) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicedetails();
            Call<customerForgot> mService = mApiService.tradesmenForgot(appid, appkey,stremail);
            mService.enqueue(new Callback<customerForgot>() {
                @Override
                public void onResponse(Call<customerForgot> call, Response<customerForgot> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        Toast.makeText(TradsmenForgotpassword.this, message, Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TradsmenForgotpassword.this, e.getMessage(), Toast.LENGTH_LONG).show();

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
        Intent i = new Intent(TradsmenForgotpassword.this, TradsmenLoginActivity.class);
        startActivity(i);
    }
}
