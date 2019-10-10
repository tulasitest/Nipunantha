package com.globalsoft.nipunatha.Tradsmen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globalsoft.nipunatha.R;

import androidx.fragment.app.Fragment;

public class Trdsmencontacts extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_tradsmencontactjobs, container, false);
        getActivity().setTitle("Contact ");
        return view;
    }
}
