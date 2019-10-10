package com.globalsoft.nipunatha.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.customerProfile;
import com.globalsoft.nipunatha.bean.verifyotp;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.button.MaterialButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerProfileFragment extends Fragment {
    TextView txt_profilename, txt_email, txt_mobileno, txt_state, txt_city, txt_area;
    ImageView btneditprofile;
    TextView txt_pincode;
    ImageView imageView;
    String fname, lname, email, mobile_no, state, city, area, image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_customerprofile, container, false);
        getActivity().setTitle("My Profile");
        btneditprofile = (ImageView) view.findViewById(R.id.btn_edit);
        txt_profilename = (TextView) view.findViewById(R.id.txt_profilename);
        txt_email = (TextView) view.findViewById(R.id.txt_email);
        txt_mobileno = (TextView) view.findViewById(R.id.txt_mobileno);
        txt_state = (TextView) view.findViewById(R.id.txt_state);
        txt_city = (TextView) view.findViewById(R.id.txt_city);
        txt_area = (TextView) view.findViewById(R.id.txt_area);
        txt_pincode = (TextView) view.findViewById(R.id.txt_pincode);
        imageView = (ImageView) view.findViewById(R.id.img_profile);

        SharedPreferences pref = getActivity().getSharedPreferences(
                "MyPref", 0);

        final int uid = pref.getInt("uid", 0);

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = new CustomerProfileEdit();
                Bundle args = new Bundle();
                args.putString("fname", fname);
                args.putString("lname", lname);
                args.putString("email", email);
                args.putString("mobileno", mobile_no);
                args.putString("state", state);
                args.putString("city", city);
                args.putString("area", area);
                args.putString("photo", image);
                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.content_frame, fragment1);
                transaction1.addToBackStack(null);
                fragment1.setArguments(args);

                transaction1.commit();
            }
        });
        ConnectivityManager cn = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            loadprofiledata(Constants.APPID, Constants.APPKEY, uid);
        } else {
            Toast.makeText(getActivity(),
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }

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

    private void loadprofiledata(String appid, String appkey, int struid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerProfile> mService = mApiService.customerProfile(appid, appkey, struid);
            mService.enqueue(new Callback<customerProfile>() {
                @Override
                public void onResponse(Call<customerProfile> call, Response<customerProfile> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            String struid = response.body().getResult().getUid();
                            String reg_id = response.body().getResult().getReg_id();
                            fname = response.body().getResult().getFname();
                            lname = response.body().getResult().getLname();
                            email = response.body().getResult().getEmail();
                            mobile_no = response.body().getResult().getMobile_no();
                            state = response.body().getResult().getState();
                            city = response.body().getResult().getCity();
                            area = response.body().getResult().getArea();
                            image = response.body().getResult().getImage();

                            String verify_email = response.body().getResult().getVerify_email();
                            String user_active = response.body().getResult().getUser_active();
                            String created = response.body().getResult().getCreated();
                            String updated = response.body().getResult().getUpdated();
                            String profilename = fname + "." + lname;
                            txt_profilename.setText(profilename);
                            txt_email.setText(email);
                            txt_mobileno.setText(mobile_no);
                            txt_state.setText(state);
                            txt_city.setText(city);
                            txt_area.setText(area);
                            txt_pincode.setText("Null");
                            Glide.with(getActivity()).load(image)
                                    .override(290, 200)
                                    .into(imageView);


                        } else {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerProfile> call, Throwable t) {
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
