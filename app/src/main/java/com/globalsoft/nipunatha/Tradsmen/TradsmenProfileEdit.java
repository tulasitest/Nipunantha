package com.globalsoft.nipunatha.Tradsmen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.citiesbean;
import com.globalsoft.nipunatha.bean.customerProfile;
import com.globalsoft.nipunatha.bean.customerProfileEdit;
import com.globalsoft.nipunatha.bean.statesbean;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;
import com.globalsoft.nipunatha.customer.Customercreatejob;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class TradsmenProfileEdit extends Fragment {
    private ImageView img_profile;
    private Button btn_profilesave;
    private TextInputEditText inputnoofemp, input_pincode, input_firstname, input_lastname, input_email, input_mobilenumber, input_area;
    private String strid, strcityid, strfirstname, strspinnerstateid, strlastname, stremail, strmobileno, strstate, strcity, strarea, strphoto, strpincode, primarytradess, noofemp;
    private TextView tv_newimage;
    private Uri mCropImageUri;
    private Spinner input_spinnercity, spnr_state, spnr_primarytrade;
    ArrayList<String> primarytrades = new ArrayList<String>();
    ArrayList<String> cities = new ArrayList<String>();
    ArrayList<String> states = new ArrayList<String>();
    ArrayList<String> customerloginID = new ArrayList<String>();
    ArrayAdapter<String> adaptercity;
    int uid;
    Uri imageuri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tradsmen_profileedit, container, false);
        getActivity().setTitle(" Profile Edit");
        try {

            SharedPreferences pref = getActivity().getSharedPreferences(
                    "MyPref", 0);

            uid = pref.getInt("uidtradsmen", 0);
            strfirstname = pref.getString("firstnametradsmen", "");
            strlastname = pref.getString("lastnametradsmen", "");
            stremail = pref.getString("emailtradsmen", "");
            strmobileno = pref.getString("mobilenotradsmen", "");
            strpincode = pref.getString("pincodetradsmen", "");
            strstate = pref.getString("statetradsmen", "");
            strcity = pref.getString("citytradsmen", "");
            strarea = pref.getString("areatradsmen", "");
            primarytradess = pref.getString("primarytradestradsmen", "");
            noofemp = pref.getString("noofemptradsmen", "");
               Bundle bundle= getArguments();
        if(bundle!=null){
            strfirstname =   bundle.getString("fname");
            strlastname = bundle.getString("lname");
            stremail = bundle.getString("email");
            strmobileno = bundle.getString("mobileno");
            strstate = bundle.getString("state");
            strcity = bundle.getString("city");
            strarea = bundle.getString("area");
            strphoto = bundle.getString("photo");
            primarytradess = bundle.getString("primarytrades");
            noofemp = bundle.getString("noemp");
            Glide.with(getActivity()).load(strphoto)
                    .override(290,200)
                    .into(img_profile);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        input_email = (TextInputEditText) view.findViewById(R.id.inputemail);
        img_profile = (ImageView) view.findViewById(R.id.img_profile);
        input_firstname = (TextInputEditText) view.findViewById(R.id.input_firstname);
        inputnoofemp = (TextInputEditText) view.findViewById(R.id.inputnoofemp);
        input_pincode = (TextInputEditText) view.findViewById(R.id.input_pincode);
        input_lastname = (TextInputEditText) view.findViewById(R.id.input_lastname);
        input_mobilenumber = (TextInputEditText) view.findViewById(R.id.input_mobilenumber);
        input_area = (TextInputEditText) view.findViewById(R.id.input_area);
        btn_profilesave = (Button) view.findViewById(R.id.btn_profilesave);
        tv_newimage = (TextView) view.findViewById(R.id.tv_newimage);
        spnr_state = (Spinner) view.findViewById(R.id.spnr_state);
        spnr_primarytrade = (Spinner) view.findViewById(R.id.spnr_primarytrade);
        input_spinnercity = (Spinner) view.findViewById(R.id.input_spinnercity);
        tv_newimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);
            }
        });
        input_firstname.setText(strfirstname);
        input_lastname.setText(strlastname);
        input_email.setText(stremail);
        input_mobilenumber.setText(strmobileno);
        input_area.setText(strarea);
        inputnoofemp.setText(noofemp);
        input_pincode.setText(strpincode);
        input_email.setEnabled(false);

        ConnectivityManager cnmanager = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nfinfo = cnmanager.getActiveNetworkInfo();
        if (nfinfo != null && nfinfo.isConnected() == true) {
            loadspinnerservices();
        } else {
            Toast.makeText(getActivity(),
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }
        ConnectivityManager cn = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            loadspinnerstates();
        } else {
            Toast.makeText(getActivity(),
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }

        spnr_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strspinnerstateid = customerloginID.get(i);
                if (cities.size() > 0) {
                    cities.clear();

                }
                loadspinnercity(strspinnerstateid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       /* Glide.with(getActivity()).load(strphoto)
                .override(290,200)
                .into(img_profile);*/

        btn_profilesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfirstname = input_firstname.getText().toString().trim();
                strlastname = input_lastname.getText().toString().trim();
                stremail = input_email.getText().toString().trim();
                strmobileno = input_mobilenumber.getText().toString().trim();
                strstate = spnr_state.getSelectedItem().toString().trim();
                strcity = input_spinnercity.getSelectedItem().toString().trim();
                primarytradess = spnr_primarytrade.getSelectedItem().toString().trim();
                strarea = input_area.getText().toString().trim();
                strpincode = input_pincode.getText().toString().trim();
                noofemp = inputnoofemp.getText().toString().trim();
                ConnectivityManager cn = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nf = cn.getActiveNetworkInfo();
                if (nf != null && nf.isConnected() == true) {
                     profleeditservicedetails(Constants.APPID,Constants.APPKEY,strfirstname,strlastname,strmobileno,strstate,strcity,strarea,imageuri,uid,strpincode,noofemp);
                } else {
                    Toast.makeText(getActivity(),
                            "Network Not Available",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }
    private Tradsmen getservicedetailsedit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://labs.sslotus.com/services/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(RetrofitSSLCertificate.getUnsafeOkHttpClient())
                .build();
        final Tradsmen mInterfaceService = retrofit.create(Tradsmen.class);
        return mInterfaceService;
    }

    private void profleeditservicedetails(String appid, String appkey, String strfirstname, String strlastname, String strmobileno, String strstate, String strcity, String strarea, Uri imageuri, int uid, final String strpincode, String noofemp) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicedetailsedit();
            Call<customerProfileEdit> mService = mApiService.tradsmenProfileEdit(appid, appkey, strfirstname,strlastname,strmobileno,strstate,strcity,strarea,imageuri,uid,strpincode,noofemp);
            mService.enqueue(new Callback<customerProfileEdit>() {
                @Override
                public void onResponse(Call<customerProfileEdit> call, Response<customerProfileEdit> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if(status.equals("valid")){
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                            Fragment fragment1= new TradsmenProfileFragment();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, fragment1);
                            transaction.commit();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerProfileEdit> call, Throwable t) {
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

    /**
     * Start pick image activity with chooser.
     */
    public void onSelectImageClick(View view) {
        CropImage.startPickImageActivity(getActivity());
    }

    @Override
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(getActivity(), data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(getActivity(), imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                img_profile.setImageURI(result.getUri());
                imageuri= result.getUri();
                Log.i("imageuri", String.valueOf(imageuri));
                //((ImageButton) findViewById(R.id.quick_start_cropped_image)).setImageURI(result.getUri());
              //  Toast.makeText(getActivity(), "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // required permissions granted, start crop image activity
            startCropImageActivity(mCropImageUri);
        } else {
            Toast.makeText(getActivity(), "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Start crop image activity for the given image.
     */
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(getActivity());
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
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
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
                                String names = Items.get(i).getName(); // I want to show this when Selected
                                strid = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                primarytrades.add(names);
                            }
                            //  primarytrades.add(0, "- PRIMARY TRADES -");
                            spnr_primarytrade.setEnabled(false);
                            spnr_primarytrade.setClickable(false);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, primarytrades);
                            spnr_primarytrade.setAdapter(adapter);
                            int service = adapter.getPosition(primarytradess);
                            spnr_primarytrade.setSelection(service);
                        } else {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

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
                final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.show();

                CustomerregisterInterface mApiService = this.getservicedetails();
                Call<citiesbean> mService = mApiService.Customercities(Constants.APPID, Constants.APPKEY, strid);
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
                                    strcityid = Items.get(i).getId(); // I want to show this when Selected
                                    String names = Items.get(i).getName(); // I want to show this when Selected
                                    String statuscity = Items.get(i).getStatus(); // I want to show this when Selected
                                    cities.add(names);
                                }
                                //     cities.add(0, "- SELECT CITY -");

                                adaptercity = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, cities);
                                input_spinnercity.setAdapter(adaptercity);

                                int city = adaptercity.getPosition(strcity);
                                input_spinnercity.setSelection(city);


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

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
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
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
                                strspinnerstateid = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                states.add(names);
                                customerloginID.add(strspinnerstateid);

                            }
                            //   states.add(0, "- SELECT STATE -");
                            //   customerloginID.add(0, "0");


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, states);
                            spnr_state.setAdapter(adapter);
                            int state = adapter.getPosition(strstate);
                            spnr_state.setSelection(state);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

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
}
