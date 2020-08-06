package com.naat.test;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naat.test.constans.Constantes;
import com.naat.test.model.Datos;
import com.naat.test.model.Items;

import java.util.List;

import static com.naat.test.constans.Constantes.modelDatos;


public class AdapterOptionService extends RecyclerView.Adapter<AdapterOptionService.ViewHolder>{
    private Datos datos;
    private List<Items> list;
    Context context;
    public AdapterOptionService(List<Items> list, Context context) {
        this.context = context;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_service_option, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("list", "list: " + list.get(position).getLabel());
        holder.textView.setText(list.get(position).getLabel());
        if(list.get(position).getIcontype().equals("1")){
            holder.imgIcon.setImageResource(R.drawable.ic_claro);
        }
        else if(list.get(position).getIcontype().equals("2")){
            holder.imgIcon.setImageResource(R.drawable.ic_entel);
        }
        else if(list.get(position).getIcontype().equals("3")){
            holder.imgIcon.setImageResource(R.drawable.ic_tuenti);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("posicion" , "posicion: " + list.get(position).getSubid());

                Intent intent = new Intent(context, ActivityPagarServicio.class);
                intent.putExtra("pago",list.get(position).getLabel());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ImageView imgIcon;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv_tittle);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            imgIcon = (ImageView) itemView.findViewById(R.id.item_icon);
        }
    }
}