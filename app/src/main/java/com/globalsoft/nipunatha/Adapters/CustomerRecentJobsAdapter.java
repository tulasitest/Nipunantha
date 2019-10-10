package com.globalsoft.nipunatha.Adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.customerJobs;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerRecentJobsAdapter extends RecyclerView.Adapter<CustomerRecentJobsAdapter.CustomerRecyclerViewholder> {

    private  FragmentActivity activity;
    private List<customerJobs.ResponseBean> arrayList;

    int size;
    public CustomerRecentJobsAdapter( List<customerJobs.ResponseBean> arrayList) {

        this.arrayList = arrayList;
    }


    @Override
    public CustomerRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        CustomerRecyclerViewholder recyclerViewholder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_customjoblist, parent, false);
        recyclerViewholder = new CustomerRecyclerViewholder(view);

        return recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(CustomerRecyclerViewholder holder, int position) {
        int rowPos = position;
        Log.i("positioncreatejob", String.valueOf(position));
        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.txt_sno.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_jobtype.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_timing.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_status.setBackgroundResource(0);
            holder.txt_action.setBackgroundResource(0);
            holder.llstatus.setBackgroundResource(R.drawable.table_header_bg);
            holder.llaction.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_action.setVisibility(View.VISIBLE);
            holder.img_action.setVisibility(View.GONE);
            holder.txt_sno.setText("S.No");
            holder.txt_jobtype.setText("Job Type");
            holder.txt_timing.setText("Timing");
            holder.txt_status.setText("Status");
            holder.txt_status.setTextColor(Color.BLACK);

        } else {
            rowPos=rowPos-1;
            holder.txt_sno.setBackgroundResource(R.drawable.table_cantent_cell_bg);
            holder.txt_jobtype.setBackgroundResource(R.drawable.table_cantent_cell_bg);
            holder.txt_timing.setBackgroundResource(R.drawable.table_cantent_cell_bg);
            holder.txt_sno.setText(String.valueOf(rowPos+1));
            holder.txt_jobtype.setText(arrayList.get(rowPos).getJob_type());
            holder.txt_action.setVisibility(View.GONE);
            holder.img_action.setVisibility(View.VISIBLE);
            holder.txt_timing.setText(arrayList.get(rowPos).getTimings());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public void filterList(List<customerJobs.ResponseBean> arrayList) {
       this. arrayList = arrayList;
        notifyDataSetChanged();
    }

    public static class CustomerRecyclerViewholder extends RecyclerView.ViewHolder {
        //varhiabhe list
        TextView txt_sno, txt_jobtype, txt_timing, txt_status,txt_action;
        ImageView img_action;
        LinearLayout llstatus,llaction;



        public CustomerRecyclerViewholder(View view) {
            super(view);
            txt_sno = (TextView) view.findViewById(R.id.txt_sno);
            txt_jobtype = (TextView) view.findViewById(R.id.txt_jobtype);
            txt_timing = (TextView) view.findViewById(R.id.txt_timing);
            txt_status = (TextView) view.findViewById(R.id.txt_status);
            txt_action = (TextView) view.findViewById(R.id.txt_action);
            img_action = (ImageView) view.findViewById(R.id.img_action);
            llstatus =  view.findViewById(R.id.llstatus);
            llaction =  view.findViewById(R.id.llaction);
        }
    }
}
