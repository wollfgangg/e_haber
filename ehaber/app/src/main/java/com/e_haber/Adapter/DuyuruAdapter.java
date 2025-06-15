package com.e_haber.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_haber.Model.DuyuruModel;
import com.e_haber.R;

import java.util.List;

public class DuyuruAdapter extends RecyclerView.Adapter<DuyuruAdapter.DuyuruViewHolder> {
    private final List<DuyuruModel> duyuruList;
    private final Context context;

    public DuyuruAdapter(Context context, List<DuyuruModel> duyuruList) {
        this.context = context;
        this.duyuruList = duyuruList;
    }

    @NonNull
    @Override
    public DuyuruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.duyuru_item, parent, false);
        return new DuyuruViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DuyuruViewHolder holder, int position) {
        DuyuruModel duyuru = duyuruList.get(position);
        holder.textBaslik.setText(duyuru.getBaslik());
        holder.textIcerik.setText(duyuru.getIcerik());
        holder.textUrl.setText(duyuru.getResimUrl());

        // Tıklanınca linki aç
        holder.textUrl.setOnClickListener(v -> {
            String url = duyuru.getResimUrl();
            if (url != null && !url.isEmpty()) {
                if (!url.startsWith("http")) url = "http://" + url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return duyuruList.size();
    }

    public static class DuyuruViewHolder extends RecyclerView.ViewHolder {
        TextView textBaslik, textIcerik, textUrl;
        public DuyuruViewHolder(@NonNull View itemView) {
            super(itemView);
            textBaslik = itemView.findViewById(R.id.textBaslik);
            textIcerik = itemView.findViewById(R.id.textIcerik);
            textUrl   = itemView.findViewById(R.id.textUrl);
        }
    }
}