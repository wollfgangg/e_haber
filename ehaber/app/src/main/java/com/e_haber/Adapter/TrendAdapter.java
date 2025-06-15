package com.e_haber.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e_haber.Model.TrendModel;
import com.e_haber.R;
import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.ViewHolder> {
    private List<TrendModel> trendList;

    public TrendAdapter(List<TrendModel> trendList) {
        this.trendList = trendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrendModel model = trendList.get(position);
        holder.trendCategory.setText(model.getKategori());
        holder.trendTitle.setText(model.getBaslik());
        holder.trendDate.setText(model.getTarih());
        holder.trendImage.setImageResource(model.getImageResId());

        // Tıklanınca linke gitmesi için:
        holder.itemView.setOnClickListener(v -> {
            if (model.getUrl() != null && !model.getUrl().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView trendImage;
        TextView trendCategory, trendTitle, trendDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trendImage = itemView.findViewById(R.id.trend_image);
            trendCategory = itemView.findViewById(R.id.trend_category);
            trendTitle = itemView.findViewById(R.id.trend_title);
            trendDate = itemView.findViewById(R.id.trend_date);
        }
    }
}