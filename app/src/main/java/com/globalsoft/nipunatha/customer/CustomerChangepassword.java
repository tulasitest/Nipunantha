package com.globalsoft.nipunatha.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.customerChangePwd;
import com.globalsoft.nipunatha.bean.customerProfile;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerChangepassword extends Fragment {
    TextInputEditText input_currenpwd,input_newpwd,input_confirmpwd;
    Button btn_savechanges;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_changepwd, container, false);
        getActivity().setTitle("Change Your Password");
        input_currenpwd= (TextInputEditText) view.findViewById(R.id.input_currenpwd);
        input_newpwd= (TextInputEditText) view.findViewById(R.id.input_newpwd);
        input_confirmpwd= (TextInputEditText) view.findViewById(R.id.input_confirmpwd);
        btn_savechanges = (Button)view.findViewById(R.id.btn_savechanges);
        SharedPreferences pref = getActivity().getSharedPreferences(
                "MyPref", 0);

        final int uid= pref.getInt("uid",0);
        btn_savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currnetpwd = input_currenpwd.getText().toString().trim();
                String newpwd = input_newpwd.getText().toString().trim();
                String confirmpwd = input_confirmpwd.getText().toString().trim();
                ConnectivityManager cn = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nf = cn.getActiveNetworkInfo();
                if (nf != null && nf.isConnected() == true) {
                    if (!currnetpwd.isEmpty() && currnetpwd != null
                            && currnetpwd != "") {
                        if (!newpwd.isEmpty() && newpwd != null
                                && newpwd != "") {
                            if (!confirmpwd.isEmpty() && confirmpwd != null
                                    && confirmpwd != "") {
                                if(newpwd.equals(confirmpwd))
                                {
                                    changepwdservice(Constants.APPID, Constants.APPKEY, currnetpwd, newpwd, uid);

                                    //are equal
                                }
                                else {
                                    Toast.makeText(getActivity(),
                                            "Password do not match",
                                            Toast.LENGTH_SHORT).show();
                                    //are different
                                }
                            } else {
                                Toast.makeText(getActivity(),
                                        "Please Enter Password",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(),
                                    "Please Enter Password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(),
                                "Please Your Current Password",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),
                            "Network Not Available",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
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
    private void changepwdservice(String appid, String appkey, String currnetpwd, String newpwd, int uid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerChangePwd> mService = mApiService.customerChangePwd(appid, appkey,currnetpwd,newpwd,uid);
            mService.enqueue(new Callback<customerChangePwd>() {
                @Override
                public void onResponse(Call<customerChangePwd> call, Response<customerChangePwd> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();

                            Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();



                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerChangePwd> call, Throwable t) {
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
