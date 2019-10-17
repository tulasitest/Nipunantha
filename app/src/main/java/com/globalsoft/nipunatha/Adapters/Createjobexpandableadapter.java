package com.globalsoft.nipunatha.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.globalsoft.nipunatha.R;
import com.globalsoft.nipunatha.bean.tradsmenprimarytrades;

import java.util.ArrayList;
import java.util.List;

public class Createjobexpandableadapter extends RecyclerView.Adapter<Createjobexpandableadapter.MyViewHolder> {
    private List<tradsmenprimarytrades.ResponseBean> dummyParentDataItems;

    public Createjobexpandableadapter(List<tradsmenprimarytrades.ResponseBean> dummyParentDataItems) {
        this.dummyParentDataItems = dummyParentDataItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_createjobexpandchild, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView_parentName.setText(dummyParentDataItems.get(position).getName());

        List<tradsmenprimarytrades.ResponseBean.JobTypesBean> jobs= new ArrayList<>();
        jobs.addAll(dummyParentDataItems.get(position).getJob_types());
        int noOfChildTextViews =holder.linearLayout_childItems.getChildCount();
        if (jobs.size() > 0) {


        for (int index = 0; index < noOfChildTextViews; index++) {
            TextView currentTextView = (TextView) holder.linearLayout_childItems.getChildAt(index);
            if(index>=jobs.size()){
                currentTextView.setVisibility(View.GONE);
            }else {
                currentTextView.setVisibility(View.VISIBLE);
                currentTextView.setText(dummyParentDataItems.get(position).getJob_types().get(index).getName());
            }
        }

        }else{

        }



    }

    @Override
    public int getItemCount() {
        return dummyParentDataItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView textView_parentName;
        private LinearLayout linearLayout_childItems;

        MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textView_parentName = itemView.findViewById(R.id.tv_parentName);
            linearLayout_childItems = itemView.findViewById(R.id.ll_child_items);
            linearLayout_childItems.setVisibility(View.GONE);
          //  int intMaxNoOfChild = 0;
            for (int index = 0; index < dummyParentDataItems.size(); index++) {
                List<tradsmenprimarytrades.ResponseBean.JobTypesBean> jobs= new ArrayList<>();
                jobs.addAll(dummyParentDataItems.get(index).getJob_types());
                   // intMaxNoOfChild = jobs.size();

if(jobs.size()>0){

    for (int indexView = 0; indexView < jobs.size(); indexView++) {

        TextView textView = new TextView(context);
        textView.setId(indexView);
        textView.setPadding(0, 20, 0, 20);
        textView.setGravity(Gravity.CENTER);
        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.drbackgroundsub));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setOnClickListener(this);
        linearLayout_childItems.addView(textView, layoutParams);
    }
}
            }



           textView_parentName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.tv_parentName) {
                if(dummyParentDataItems.get(getAdapterPosition()).getJob_types().size()>0){
                    if (linearLayout_childItems.getVisibility() == View.VISIBLE) {
                        linearLayout_childItems.setVisibility(View.GONE);
                    } else {
                        linearLayout_childItems.setVisibility(View.VISIBLE);
                    }
                }else{
                    linearLayout_childItems.setVisibility(View.GONE);

                }

            } else {
                TextView textViewClicked = (TextView) view;
                Toast.makeText(context, "" + textViewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
