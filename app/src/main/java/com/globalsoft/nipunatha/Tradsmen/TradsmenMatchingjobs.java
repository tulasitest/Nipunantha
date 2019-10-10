package com.globalsoft.nipunatha.Tradsmen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.globalsoft.nipunatha.Adapters.TradsmenMatchingjobsAdapter;
import com.globalsoft.nipunatha.Adapters.TradsmenjobsAdapter;
import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.TradsmenJobbean;
import com.globalsoft.nipunatha.bean.tradesmenJobMatch;
import com.globalsoft.nipunatha.interfaces.Tradsmen;
import com.globalsoft.nipunatha.utills.Constants;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TradsmenMatchingjobs extends Fragment {
    String regid,serviceid;
    private RecyclerView recyclerView;
    private TradsmenMatchingjobsAdapter adapter;
    EditText editTextSearch;
    List<tradesmenJobMatch.ResponseBean> dataresponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tradsmenmatchingjobs, container, false);
        getActivity().setTitle(" Matching Jobs");
        SharedPreferences pref = getActivity().getSharedPreferences(
                "MyPref", 0);
        regid = pref.getString("regidtradsmen", "");
        serviceid = pref.getString("serviceid", "");
        editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclewview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ConnectivityManager cn = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            loadtradsmenconfirmjobs(Constants.APPID, Constants.APPKEY, "6", regid);
        } else {
            Toast.makeText(getActivity(),
                    "Network Not Available",
                    Toast.LENGTH_SHORT).show();
        }
        //to call a method whenever there is some change on the EditText
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
        return view;
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
    private void loadtradsmenconfirmjobs(String appid, String appkey, String serviceid, String regid) {
        try {
            final ProgressDialog mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            Tradsmen mApiService = this.getservicedetails();
            Call<tradesmenJobMatch> mService = mApiService.tradesmenJobMatch(appid, appkey, serviceid, regid);
            mService.enqueue(new Callback<tradesmenJobMatch>() {
                @Override
                public void onResponse(Call<tradesmenJobMatch>call, Response<tradesmenJobMatch> response) {
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    try {

                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        dataresponse = response.body().getResponse();

                        if (status.equals("valid")) {

                            adapter = new TradsmenMatchingjobsAdapter(getActivity(),response.body().getResponse());
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();


                    }
                }

                @Override
                public void onFailure(Call<tradesmenJobMatch> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filter(String text) {
        List<tradesmenJobMatch.ResponseBean> temp = new ArrayList();
        //  Iterable<? extends customerJobs.ResponseBean> displayedList = null;
        for (tradesmenJobMatch.ResponseBean data : dataresponse) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (data.getName().contains(text)) {
                temp.add(data);
            }
        }
        //update recyclerview
        adapter.filterList(temp);
    }

}
