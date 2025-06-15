package com.e_haber.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_haber.Adapter.DuyuruAdapter;
import com.e_haber.Model.DuyuruModel;
import com.e_haber.R;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class DuyuruFragment extends Fragment {
    private RecyclerView recyclerView;
    private DuyuruAdapter adapter;
    private List<DuyuruModel> duyuruList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duyuru, container, false);
        recyclerView = view.findViewById(R.id.duyuruRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DuyuruAdapter(getContext(), duyuruList);
        recyclerView.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("duyurular");
        ref.addValueEventListener(new ValueEventListener() {
            @Override public void onDataChange(@NonNull DataSnapshot snapshot) {
                duyuruList.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    DuyuruModel duyuru = ds.getValue(DuyuruModel.class);
                    if (duyuru != null) {
                        duyuruList.add(duyuru);
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override public void onCancelled(@NonNull DatabaseError error) {}
        });
        return view;
    }
}