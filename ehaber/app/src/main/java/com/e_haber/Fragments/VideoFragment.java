package com.e_haber.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_haber.Adapter.VideoAdapter;
import com.e_haber.Model.VideoModel;
import com.e_haber.R;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    private RecyclerView recyclerView;
    private VideoAdapter adapter;
    private List<VideoModel> videoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        recyclerView = view.findViewById(R.id.videoRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoAdapter(getContext(), videoList);
        recyclerView.setAdapter(adapter);

        // -------- MANUEL 4 VİDEO EKLE, kendi dosya ve resim isimlerini kullan! -----------
        videoList.clear();
        videoList.add(new VideoModel(
                R.raw.trump,         // res/raw/video1.mp4
                "Trump ile Mask arasına kedi girdi.",
                R.drawable.trump    // res/drawable/thumb1.jpg/png
        ));
        videoList.add(new VideoModel(
                R.raw.mahsun1,
                "Evine 4 defa otomobil düştü",
                R.drawable.mahsun
        ));
        videoList.add(new VideoModel(
                R.raw.bayram,
                "Kurbanlıklar Kaçtı",
                R.drawable.bayram
        ));
        videoList.add(new VideoModel(
                R.raw.silah,
                "Yolun Ortasında Kanlı Saldırı",
                R.drawable.silah
        ));
        adapter.notifyDataSetChanged();

        return view;
    }
}