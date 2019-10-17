package com.globalsoft.nipunatha.customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.globalsoft.nipunatha.Adapters.Createjobexpandableadapter;
import com.globalsoft.nipunatha.Adapters.CustomExpandableListAdapter;
import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.ExpandableListDataPump;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;
import com.globalsoft.nipunatha.interfaces.CustomerregisterInterface;
import com.globalsoft.nipunatha.utills.RetrofitSSLCertificate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Createjobexpand extends Fragment {
    int uid;
    ExpandableListView expandableListView;
    RecyclerView jobrecyclerview;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<tradsmenprimarytrades.ResponseBean.JobTypesBean>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.li_createjobexpand, container, false);
        getActivity().setTitle("Services");
        SharedPreferences pref = getActivity().getSharedPreferences(
                "MyPref", 0);

        uid = pref.getInt("uid", 0);

        jobrecyclerview = view.findViewById(R.id.recyclerView);
       // expandableListView = (ExpandableListView)view.findViewById(R.id.ParentLevel);

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
                        String json = new Gson().toJson(response);
                        Log.i("response", json);
                 //       List<tradsmenprimarytrades.ResponseBean> serviceList=response.body().getResponse();
                        if (status.equals("valid")) {
                            /*for(int i=0;i<serviceList.size();i++){
                                expandableListTitle.add(serviceList.get(i).getName());
                                expandableListDetail.put(serviceList.get(i).getId(),serviceList.get(i).getJob_types());
                            }*/
                           // expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle, (List<tradsmenprimarytrades.ResponseBean>) expandableListDetail);
                          //  expandableListView.setAdapter(expandableListAdapter);
                          /*  expandableListDetail = ExpandableListDataPump.getData();
                            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                            expandableListAdapter = new CustomExpandableListAdapter(getActivity(), expandableListTitle,response.body().getResponse());
                            expandableListView.setAdapter(expandableListAdapter);*/
                            Createjobexpandableadapter createjobexpandableadapter=new Createjobexpandableadapter(response.body().getResponse());
                            jobrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                            jobrecyclerview.setAdapter(createjobexpandableadapter);

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
