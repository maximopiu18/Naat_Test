package com.naat.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.naat.test.model.ModelFilterService;


public class AdapterFilter extends RecyclerView.Adapter<AdapterFilter.ViewHolder>{
    private ModelFilterService[] listdata;
    Context context;
    public AdapterFilter(ModelFilterService[] listdata, Context context) {
        this.context = context;
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_filter_service, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelFilterService modelFilterService = listdata[position];
        holder.textView.setText(listdata[position].getTittle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Log.e("position","position" + position);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv_tittle);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}