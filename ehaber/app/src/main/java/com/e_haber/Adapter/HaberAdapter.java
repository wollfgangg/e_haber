package com.e_haber.Adapter; // Paket adınızı kontrol edin

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide; // Glide importu
import com.e_haber.Model.HaberModel;
import com.e_haber.R;
import java.util.List;

public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.ViewHolder> {

    private Context context;
    private List<HaberModel> haberListesi;
    // Tıklama olayı için bir listener ekleyebilirsiniz (opsiyonel)
    // private OnHaberClickListener listener;

    // public interface OnHaberClickListener {
    //    void onHaberClick(HaberModel haber);
    // }

    public HaberAdapter(Context context, List<HaberModel> haberListesi /*, OnHaberClickListener listener*/) {
        this.context = context;
        this.haberListesi = haberListesi;
        // this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.haber_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HaberModel haber = haberListesi.get(position);
        if (haber != null) {
            holder.haberBaslik.setText(haber.getBaslik());
            // İçeriğin tamamı yerine bir özet göstermek daha iyi olabilir.
            // Şimdilik tam içeriği gösterip maxLines ile sınırlayalım.
            holder.haberIcerikOzet.setText(haber.getIcerik());

            if (haber.getResimUrl() != null && !haber.getResimUrl().isEmpty()) {
                Glide.with(context)
                        .load(haber.getResimUrl())
                        .placeholder(R.drawable.sspor) // Yüklenirken gösterilecek varsayılan resim
                        .error(R.drawable.sspor)       // Hata durumunda gösterilecek resim
                        .into(holder.haberResim);
            } else {
                holder.haberResim.setImageResource(R.drawable.sspor); // URL yoksa varsayılan resim
            }

            // holder.itemView.setOnClickListener(v -> {
            //    if (listener != null) listener.onHaberClick(haber);
            // });
        }
    }

    @Override
    public int getItemCount() {
        return haberListesi != null ? haberListesi.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView haberResim;
        TextView haberBaslik;
        TextView haberIcerikOzet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            haberResim = itemView.findViewById(R.id.haber_resim_imageview);
            haberBaslik = itemView.findViewById(R.id.haber_baslik_textview);
            haberIcerikOzet = itemView.findViewById(R.id.haber_icerik_ozet_textview);
        }
    }

    // Adapter listesini güncellemek için bir metod
    public void updateData(List<HaberModel> yeniListe) {
        this.haberListesi.clear();
        if (yeniListe != null) {
            this.haberListesi.addAll(yeniListe);
        }
        notifyDataSetChanged();
    }
}