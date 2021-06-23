package com.example.tugas6_database.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas6_database.R;
import com.example.tugas6_database.entity.DataUntung;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataUntung> list;
    MainContact.view view;

    public MainAdapter(Context context, List<DataUntung> list, MainContact.view view) {
        this.context = context;
        this.list = list;
        this.view = view;
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView tvTanggal, tvBruto, tvPengeluaran,tvNetto, id;
        CardView cardView;

        public viewHolder(View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_item_Tanggal);
            tvBruto = itemView.findViewById(R.id.tv_item_masukkotor);
            tvPengeluaran= itemView.findViewById(R.id.tv_item_Pengeluaran);
            tvNetto= itemView.findViewById(R.id.tv_item_masukbersih);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }

    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_untung, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.viewHolder holder, int position) {
        final DataUntung item = list.get(position);
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvBruto.setText(String.valueOf(item.getBruto()));
        holder.tvPengeluaran.setText(String.valueOf(item.getPengeluaran()));
        holder.tvNetto.setText(String.valueOf(item.getNetto()));
        holder.id.setText(String.valueOf(item.getId()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
