package com.e_haber.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e_haber.Model.KategoriModel;
import com.e_haber.R;
import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    private List<KategoriModel> kategoriList;
    private OnKategoriClickListener clickListener;

    // Kategoriye tıklanınca çalışacak arayüz:
    public interface OnKategoriClickListener {
        void onKategoriClick(KategoriModel kategori); // Parametre adı HomeFragment'taki ile aynı
    }

    public KategoriAdapter(List<KategoriModel> kategoriList, OnKategoriClickListener clickListener) {
        this.kategoriList = kategoriList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kategori_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KategoriModel model = kategoriList.get(position);
        if (model != null) { // Null kontrolü eklendi
            holder.kategoriAdi.setText(model.getKategoriAdi());
            holder.kategoriResim.setImageResource(model.getIconResId());

            holder.itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onKategoriClick(model);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return kategoriList != null ? kategoriList.size() : 0; // Null kontrolü eklendi
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView kategoriResim;
        TextView kategoriAdi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kategoriResim = itemView.findViewById(R.id.profile_image); // ID'lerin doğru olduğundan emin olun
            kategoriAdi = itemView.findViewById(R.id.textView3);       // ID'lerin doğru olduğundan emin olun
        }
    }
}