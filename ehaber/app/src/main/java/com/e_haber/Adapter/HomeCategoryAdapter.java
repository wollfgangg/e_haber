package com.e_haber.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e_haber.Model.HomeCategoryModel;
import com.e_haber.R;
import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private List<HomeCategoryModel> catList;
    public HomeCategoryAdapter(List<HomeCategoryModel> catList) {
        this.catList = catList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_item, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeCategoryModel m = catList.get(position);
        holder.homeCatIcon.setImageResource(m.getIconResId());
        holder.homeCatName.setText(m.getKategoriAdi());
    }
    @Override
    public int getItemCount() { return catList.size(); }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView homeCatIcon; TextView homeCatName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeCatIcon = itemView.findViewById(R.id.homeCatIcon);
            homeCatName = itemView.findViewById(R.id.homeCatName);
        }
    }
}