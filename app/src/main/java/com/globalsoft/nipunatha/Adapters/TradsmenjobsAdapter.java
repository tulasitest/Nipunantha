package com.globalsoft.nipunatha.Adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.Tradsmen.TradsmenConfirmedjobs;
import com.globalsoft.nipunatha.bean.TradsmenJobbean;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class TradsmenjobsAdapter extends RecyclerView.Adapter<TradsmenjobsAdapter.Recyclerviewholder> {
    private FragmentActivity activity;
    private List<TradsmenJobbean.ResponseBean> arrayList;


    public TradsmenjobsAdapter(FragmentActivity activity, List<TradsmenJobbean.ResponseBean> arrayList) {
        this.arrayList = arrayList;
        this.activity = activity;

    }


    @Override
    public Recyclerviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        Recyclerviewholder recyclerViewholder;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_tradsmenjobs, parent, false);
        recyclerViewholder = new TradsmenjobsAdapter.Recyclerviewholder(view);
        return recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(Recyclerviewholder holder, int position) {
        int rowPos = position;
        Log.i("positioncreatejob", String.valueOf(position));
        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.txt_sno.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_customer.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_jobtype.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_timing.setBackgroundResource(R.drawable.table_header_bg);
          //  holder.lltiming.setBackgroundResource(0);
            holder.txt_action.setBackgroundResource(0);
            holder.llaction.setBackgroundResource(R.drawable.table_header_bg);
            holder.txt_action.setVisibility(View.VISIBLE);
            holder.btn_confirm.setVisibility(View.GONE);
            holder.btn_close.setVisibility(View.GONE);
            holder.btn_moredetails.setVisibility(View.GONE);
            holder.txt_sno.setText("S.No");
            holder.txt_customer.setText("Customer");
            holder.txt_jobtype.setText("Job Type");
            holder.txt_timing.setText("Timing");
            holder.txt_action.setText("Action");
            holder.txt_action.setTextColor(Color.BLACK);
        } else {
            rowPos = rowPos - 1;
            holder.txt_sno.setText(String.valueOf(rowPos + 1));
            holder.txt_customer.setText(arrayList.get(rowPos).getName());
            holder.txt_jobtype.setText(arrayList.get(rowPos).getService());
            holder.txt_action.setVisibility(View.GONE);
            holder.txt_timing.setText(arrayList.get(rowPos).getTimings());
            holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment1 = new TradsmenConfirmedjobs();
                    FragmentTransaction transaction1 = activity.getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.content_frame, fragment1);
                    transaction1.addToBackStack(null);
                    transaction1.commit();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public void filterList(List<TradsmenJobbean.ResponseBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();


    }

    public static class Recyclerviewholder extends RecyclerView.ViewHolder {
        //variables for list
        TextView txt_sno, txt_customer, txt_jobtype, txt_timing, txt_action;
        LinearLayout lltiming, llaction;
        Button btn_confirm, btn_close, btn_moredetails;

        public Recyclerviewholder(View view) {
            super(view);
            txt_sno = (TextView) view.findViewById(R.id.txt_sno);
            txt_customer = (TextView) view.findViewById(R.id.txt_customer);
            txt_jobtype = (TextView) view.findViewById(R.id.txt_jobtype);
            txt_timing = (TextView) view.findViewById(R.id.txt_timing);
            txt_action = (TextView) view.findViewById(R.id.txt_action);
            btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
            btn_close = (Button) view.findViewById(R.id.btn_close);
            btn_moredetails = (Button) view.findViewById(R.id.btn_moredetails);
          //  lltiming = view.findViewById(R.id.lltiming);
            llaction = view.findViewById(R.id.llaction);
        }
    }
}
