package com.globalsoft.nipunatha.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.Tradsmen.TradsmenRegister;
import com.globalsoft.nipunatha.bean.citiesbean;
import com.globalsoft.nipunatha.bean.createjob;
import com.globalsoft.nipunatha.bean.customerjobtype;
import com.globalsoft.nipunatha.bean.statesbean;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;
import com.globalsoft.nipunatha.bean.verifyotp;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Customercreatejob extends Fragment {
    private Spinner spnr_services, spnr_jobtype, spnr_timeperiod;
    private String strid;
    ArrayList<String> services = new ArrayList<String>();
    ArrayList<String> serviceid = new ArrayList<String>();
    ArrayList<String> jobtypes = new ArrayList<String>();
    AppCompatRadioButton rb_1, rb_2;
    private EditText input_jobSummary, input_jobDetails;
    private Button btn_submit;
    String rbasap, rbquotation, strrediogroup, strspnrservices, strspnrjobtype, strspnrtimeperiod, strinputjobSummary, strinputjobDetails, strjobtypeid;
    private RadioGroup rediogroup;
    int uid;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.createjob, container, false);
        getActivity().setTitle("Services");
        SharedPreferences pref = getActivity().getSharedPreferences(
                "MyPref", 0);

        uid = pref.getInt("uid", 0);
        rb_1 = (AppCompatRadioButton) view.findViewById(R.id.rb_1);
        rb_2 = (AppCompatRadioButton) view.findViewById(R.id.rb_2);
        rediogroup = (RadioGroup) view.findViewById(R.id.rediogroup);
        spnr_services = (Spinner) view.findViewById(R.id.spnr_services);
        spnr_jobtype = (Spinner) view.findViewById(R.id.spnr_jobtype);
        spnr_timeperiod = (Spinner) view.findViewById(R.id.spnr_timeperiod);
        input_jobSummary = (EditText) view.findViewById(R.id.input_jobSummary);
        input_jobDetails = (EditText) view.findViewById(R.id.input_jobDetails);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        jobtypes.add(0, "CHOOSE YOUR OPTION ");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, jobtypes);
        spnr_jobtype.setAdapter(adapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int strrediogroup = rediogroup.getCheckedRadioButtonId();
                rbasap = rb_1.getText().toString().trim();
                rbquotation = rb_2.getText().toString().trim();
                strspnrservices = spnr_services.getSelectedItem().toString().trim();
                strspnrjobtype = spnr_jobtype.getSelectedItem().toString().trim();
                strspnrtimeperiod = spnr_timeperiod.getSelectedItem().toString().trim();
                strinputjobSummary = input_jobSummary.getText().toString().trim();
                strinputjobDetails = input_jobDetails.getText().toString().trim();
                String timing = null;
                if (!strspnrservices.isEmpty() && strspnrservices != null
                        && strspnrservices != "") {
                    if (!strspnrjobtype.isEmpty() && strspnrjobtype != null
                            && strspnrjobtype != "") {
                        if (!strinputjobSummary.isEmpty() && strinputjobSummary != null
                                && strinputjobSummary != "") {
                            if (!strinputjobDetails.isEmpty() && strinputjobDetails != null
                                    && strinputjobDetails != "") {
                                ConnectivityManager cn = (ConnectivityManager) getActivity()
                                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo nf = cn.getActiveNetworkInfo();
                                if (nf != null && nf.isConnected() == true) {
                                    loadcustomercreatejobservices("29032019", "e180dd9db5c3af31bf66af67efb0454d", strinputjobSummary, strinputjobDetails, "Asap", strspnrtimeperiod, strid, strjobtypeid, uid);

                                } else {
                                    Toast.makeText(getActivity(),
                                            "Network Not Available",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getActivity(),
                                        "Please Enter Job Details",
                                        Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(getActivity(),
                                    "Please Enter Job Summary",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(),
                                "Please select Job Type",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(),
                            "Please Select Services",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                getActivity(), R.array.timeperiod, R.layout.my_spinner);
        spnr_timeperiod.setAdapter(adapter2);
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
        spnr_services.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    strspnrservices = spnr_services.getSelectedItem().toString();
                    strid = serviceid.get(i);
                    if (jobtypes.size() > 0)
                        jobtypes.clear();
                    jobtype(strid);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    private void loadcustomercreatejobservices(String appid, String appkey, String jobsummery, String jobdetails, String timings, String expecteddate, String serviceid, String jobtypeid, int uid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<createjob> mService = mApiService.createJob(appid, appkey, jobsummery, jobdetails, timings, expecteddate, serviceid, jobtypeid, uid);
            mService.enqueue(new Callback<createjob>() {
                @Override
                public void onResponse(Call<createjob> call, Response<createjob> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        Fragment fragment1 = new CustomerRecentJobs();
                        FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.content_frame, fragment1);
                        transaction1.addToBackStack(null);
                        transaction1.commit();
                        //  Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<createjob> call, Throwable t) {
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

    private void jobtype(String strid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<customerjobtype> mService = mApiService.jobTypes("29032019", "e180dd9db5c3af31bf66af67efb0454d", strid);
            mService.enqueue(new Callback<customerjobtype>() {
                @Override
                public void onResponse(Call<customerjobtype> call, Response<customerjobtype> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {

                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status.equals("valid")) {
                            List<customerjobtype.ResponseBean> Items = response.body().getResponse();

                            for (int i = 0; i < Items.size(); i++) {
                                strjobtypeid = Items.get(i).getId(); // I want to show this when Selected
                                String names = Items.get(i).getName(); // I want to show this when Selected
                                String statuscity = Items.get(i).getStatus(); // I want to show this when Selected
                                jobtypes.add(names);
                            }
                            jobtypes.add(0, "CHOOSE YOUR OPTION ");

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, jobtypes);
                            spnr_jobtype.setAdapter(adapter);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<customerjobtype> call, Throwable t) {
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
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            CustomerregisterInterface mApiService = this.getservicedetails();
            Call<tradsmenprimarytrades> mService = mApiService.servicestradsmenregister("29032019", "e180dd9db5c3af31bf66af67efb0454d");
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
                                services.add(names);
                                serviceid.add(strid);
                            }
                            services.add(0, "-SELECT SERVICE -");
                            serviceid.add(0, "0");
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.my_spinner, services);
                            spnr_services.setAdapter(adapter);
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


}
