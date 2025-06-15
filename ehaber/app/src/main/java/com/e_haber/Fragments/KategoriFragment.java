package com.e_haber.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e_haber.Adapter.KategoriAdapter;
import com.e_haber.Model.KategoriModel;
import com.e_haber.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.List;

public class KategoriFragment extends Fragment {

    private RecyclerView recyclerView;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kategori, container, false);

        recyclerView = view.findViewById(R.id.kategoriRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<KategoriModel> kategoriModelList = new ArrayList<>();
        kategoriModelList.add(new KategoriModel(R.drawable.teknoloji, "Teknoloji"));
        kategoriModelList.add(new KategoriModel(R.drawable.sspor, "Spor"));
        kategoriModelList.add(new KategoriModel(R.drawable.ssiyaset, "Siyaset"));
        kategoriModelList.add(new KategoriModel(R.drawable.eekonomi, "Ekonomi"));
        kategoriModelList.add(new KategoriModel(R.drawable.gundem1, "Gündem"));
        kategoriModelList.add(new KategoriModel(R.drawable.hhava, "Hava Durumu"));

        KategoriAdapter kategoriAdapter = new KategoriAdapter(kategoriModelList, kategori -> {
            Toast.makeText(getContext(), kategori.getKategoriAdi() + " tıklandı", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(kategoriAdapter);

        // Harita fragmentini programatik olarak ekliyoruz
        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.map_container, mapFragment);
        transaction.commit();

        // Konum istemcisi
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Harita hazır olduğunda
        mapFragment.getMapAsync(googleMap -> {

            // Konum izni verilmiş mi?
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                googleMap.setMyLocationEnabled(true);

                fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Benim Konumum"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    }
                });

            } else {
                // İzin yoksa kullanıcıdan iste
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE);
            }

        });

        return view;
    }
}
