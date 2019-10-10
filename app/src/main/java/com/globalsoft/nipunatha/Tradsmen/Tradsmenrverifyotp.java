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
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.verifyotp;
import com.globalsoft.nipunatha.customer.CustomerLoginActivity;
import com.globalsoft.nipunatha.customer.CustomerRegistrationActivity;
import com.globalsoft.nipunatha.customer.Customerverifyotp;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tradsmenrverifyotp extends AppCompatActivity {
    Button btn_verifyotp;
    TextInputEditText input_otp;
    String strotp,regid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerverifyotp);
        btn_verifyotp = (Button)findViewById(R.id.btn_verifyotp);
        input_otp = (TextInputEditText)findViewById(R.id.input_otp);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(
                "MyPref", 0);

        strotp = pref.getString("otptradsmen", "");
        regid = pref.getString("regidtradsmen", "");
        final int uid= pref.getInt("uidtradsmen",0);
        btn_verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strotp =input_otp.getText().toString().trim();
                if (!strotp.isEmpty() && strotp != null
                        && strotp != "") {
                    ConnectivityManager cn = (ConnectivityManager) Tradsmenrverifyotp.this
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo nf = cn.getActiveNetworkInfo();
                    if (nf != null && nf.isConnected() == true) {
                        verifyotp(strotp, uid);
                    }else {
                        Toast.makeText(Tradsmenrverifyotp.this,
                                "Network Not Available",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(Tradsmenrverifyotp.this,
                            "Please Enter Your Otp",
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
    private void verifyotp(String strotp, int uid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicedetails();
            Call<verifyotp> mService = mApiService.tradesmenVerify(Constants.APPID,Constants.APPKEY,uid,strotp);
            mService.enqueue(new Callback<verifyotp>() {
                @Override
                public void onResponse(Call<verifyotp> call, Response<verifyotp> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if(status.equals("valid")){
                            String uid = response.body().getUid();
                            String regid = response.body().getReg_id();
                            Intent i = new Intent(Tradsmenrverifyotp.this, TradsmenLoginActivity.class);
                            SharedPreferences pref = getApplicationContext()
                                    .getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putInt("uidtradsmen", Integer.parseInt(uid));
                            editor.putString("regidtradsmen", regid);
                            editor.commit();
                            startActivity(i);
                        }else{
                            Toast.makeText(Tradsmenrverifyotp.this,message, Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Tradsmenrverifyotp.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<verifyotp> call, Throwable t) {
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
        Intent i = new Intent(Tradsmenrverifyotp.this, TradsmenRegister.class);
        startActivity(i);
    }
}
