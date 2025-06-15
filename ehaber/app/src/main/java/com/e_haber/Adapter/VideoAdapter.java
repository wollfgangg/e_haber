package com.e_haber.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e_haber.Model.VideoModel;
import com.e_haber.R;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private final List<VideoModel> videoList;
    private final Context context;
    public VideoAdapter(Context context, List<VideoModel> videoList) {
        this.context = context;
        this.videoList = videoList;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel video = videoList.get(position);
        holder.textVideoBaslik.setText(video.getBaslik());
        // Thumbnailı drawable'dan göster
        holder.imageThumbnail.setImageResource(video.getDrawableResId());
        // Tıklanınca artık harici intent değil, kendi VideoActivity'ini başlat!
        holder.imageThumbnail.setOnClickListener(v -> {
            Intent intent = new Intent(context, com.e_haber.VideoActivity.class);
            intent.putExtra("videoResId", video.getVideoRawResId());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return videoList.size();
    }
    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textVideoBaslik;
        ImageView imageThumbnail;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            textVideoBaslik = itemView.findViewById(R.id.textVideoBaslik);
            imageThumbnail = itemView.findViewById(R.id.imageThumbnail);
        }
    }
}