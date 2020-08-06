package com.naat.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naat.test.model.Companias;
import com.naat.test.model.Datos;
import com.naat.test.model.ModelFilterService;

import static com.naat.test.constans.Constantes.modelDatos;


public class AdapterServices extends RecyclerView.Adapter<AdapterServices.ViewHolder>{
    private Datos datos;
    Context context;
    private AdapterOptionService adapterOptionService;
    public AdapterServices(Datos datos, Context context) {
        this.context = context;
        this.datos = datos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_service, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(datos.getCompanias().get(position).getName_company());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("position","position" + position);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        adapterOptionService = new AdapterOptionService(datos.getCompanias().get(position).getItems(), context);
        holder.recyclerViewOption.setLayoutManager(layoutManager);
        holder.recyclerViewOption.setHasFixedSize(true);
        holder.recyclerViewOption.setAdapter(adapterOptionService);
    }


    @Override
    public int getItemCount() {
        return datos.getCompanias().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public RecyclerView recyclerViewOption;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv_tittle);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            recyclerViewOption = (RecyclerView) itemView.findViewById(R.id.item_recycler_items);
        }
    }
}