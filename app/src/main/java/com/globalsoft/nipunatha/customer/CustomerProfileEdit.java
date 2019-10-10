package com.globalsoft.nipunatha.customer;

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
import android.widget.EditText;
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
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class CustomerProfileEdit extends Fragment {
    private ImageView img_profile;
    private Button btn_profilesave;
    private TextInputEditText input_pincode, input_lastname, input_firstname, input_email, input_mobilenumber, input_state, input_city, input_area;
    private String strpincode, strstateid, strfirstname, strlastname, stremail, strmobileno, strstate, strcity, strarea, strphoto, names;
    private TextView tv_newimage;
    private Uri mCropImageUri;
    private Spinner input_spinnercity, spnr_state;
    ArrayAdapter<String> adapterstatets;
    ArrayAdapter<String> adaptercity;
    ArrayList<String> customerlogin = new ArrayList<String>();
    ArrayList<String> customerloginID = new ArrayList<String>();
    ArrayList<String> customercities = new ArrayList<String>();
    Uri imageuri;
    int uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customer_profileedit, container, false);
        getActivity().setTitle(" Profile Edit");
        try {
            SharedPreferences pref = getActivity().getSharedPreferences(
                    "MyPref", 0);

            uid = pref.getInt("uid", 0);
            strfirstname = pref.getString("firstname", "");
            strlastname = pref.getString("lastname", "");
            stremail = pref.getString("email", "");
            strmobileno = pref.getString("mobileno", "");
            strpincode = pref.getString("pincode", "");
            strstate = pref.getString("state", "");
            strcity = pref.getString("city", "");
            strarea = pref.getString("area", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        img_profile = (ImageView) view.findViewById(R.id.img_profile);
        input_email = (TextInputEditText) view.findViewById(R.id.inputemail);
        input_firstname = (TextInputEditText) view.findViewById(R.id.input_firstname);
        input_lastname = (TextInputEditText) view.findViewById(R.id.input_lastname);
        input_pincode = (TextInputEditText) view.findViewById(R.id.input_pincode);
        input_mobilenumber = (TextInputEditText) view.findViewById(R.id.input_mobilenumber);
        input_area = (TextInputEditText) view.findViewById(R.id.input_area);
        btn_profilesave = (Button) view.findViewById(R.id.btn_profilesave);
        tv_newimage = (TextView) view.findViewById(R.id.tv_newimage);
        spnr_state = (Spinner) view.findViewById(R.id.spnr_state);
        input_spinnercity = (Spinner) view.findViewById(R.id.input_spinnercity);
        tv_newimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectImageClick(v);
            }
        });
        input_email.setEnabled(false);
      /*  Bundle bundle= getArguments();
        if(bundle!=null){
            strfirstname =   bundle.getString("fname");
            strlastname = bundle.getString("lname");
            stremail = bundle.getString("email");
            strmobileno = bundle.getString("mobileno");
            strstate = bundle.getString("state");
            strcity = bundle.getString("city");
            strarea = bundle.getString("area");
            strphoto = bundle.getString("photo");

        }*/
  /*  Glide.with(getActivity()).load(strphoto)
                .override(290,200)
                .into(img_profile);*/
        input_firstname.setText(strfirstname);
        input_lastname.setText(strlastname);
        input_email.setText(stremail);
        input_mobilenumber.setText(strmobileno);
        input_area.setText(strarea);
        input_pincode.setText(strpincode);

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
                strstateid = customerloginID.get(i);
                if (customercities.size() > 0) {
                    customercities.clear();
                }

                loadspinnercity(strstateid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_profilesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfirstname = input_firstname.getText().toString().trim();
                strlastname = input_lastname.getText().toString().trim();
                stremail = input_email.getText().toString().trim();
                strmobileno = input_mobilenumber.getText().toString().trim();
                strstate = spnr_state.getSelectedItem().toString().trim();
                strcity = input_spinnercity.getSelectedItem().toString().trim();
                strarea = input_area.getText().toString().trim();
                strpincode = input_pincode.getText().toString().trim();
                ConnectivityManager cn = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nf = cn.getActiveNetworkInfo();
                if (nf != null && nf.isConnected() == true) {
                    profileeditservice(Constants.APPID, Constants.APPKEY, strfirstname, strlastname, strmobileno, strstate, strcity, strarea, imageuri, uid);
                } else {
                    Toast.makeText(getActivity(),
                            "Network Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

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
                // Toast.makeText(getActivity(), "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
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

    private void profileeditservice(String appid, String appkey, String strfirstname, String strlastname, String strmobileno, String strstate, String strcity, String strarea, Uri photo, int uid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerProfileEdit> mService = mApiService.customerProfileEdit(appid, appkey, strfirstname, strlastname, strmobileno, strstate, strcity, strarea, photo, uid);
            mService.enqueue(new Callback<customerProfileEdit>() {
                @Override
                public void onResponse(Call<customerProfileEdit> call, Response<customerProfileEdit> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();


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
                                strstateid = Items.get(i).getId(); // I want to show this when Selected
                                String status1 = Items.get(i).getStatus(); // I want to show this when Selected
                                customerlogin.add(names);
                                customerloginID.add(strstateid);
                            }
                            //     customerlogin.add(0, "- SELECT STATE -");
                            //   customerloginID.add(0, "0");

                            adapterstatets = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, customerlogin);
                            spnr_state.setAdapter(adapterstatets);
                            int state = adapterstatets.getPosition(strstate);
                            spnr_state.setSelection(state);
                        } else {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
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

    private void loadspinnercity(String strcityid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
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
                                // strstateid = Items.get(i).getId(); // I want to show this when Selected
                                names = Items.get(i).getName(); // I want to show this when Selected
                                String statuscity = Items.get(i).getStatus(); // I want to show this when Selected
                                customercities.add(names);
                            }
                            //  customercities.add(0, "- SELECT CITY -");

                            adaptercity = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, customercities);
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
