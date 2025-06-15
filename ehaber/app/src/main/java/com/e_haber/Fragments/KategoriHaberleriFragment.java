package com.e_haber.Fragments; // Paket adınızın doğru olduğundan emin olun

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

// Kendi projenizdeki R dosyasını import edin. 'com.e_haber' kısmını kendi paket adınızla değiştirin.
import com.e_haber.R;
import com.e_haber.Adapter.HaberAdapter;
import com.e_haber.Model.HaberModel;

// Firebase Realtime Database için importlar
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KategoriHaberleriFragment extends Fragment {

    private static final String ARG_KATEGORI_ADI = "kategori_adi";
    private String kategoriAdi;

    // View Referansları
    private TextView kategoriBaslikTextView;
    private RecyclerView haberlerRecyclerView;
    private TextView veriYokTextView;

    private HaberAdapter haberAdapter;
    private List<HaberModel> haberListesi;

    private DatabaseReference databaseReferenceDuyurular; // "duyurular" düğümüne referans
    private ValueEventListener haberlerEventListener; // Listener'ı tutmak için
    private Query haberlerQuery; // Query'yi tutmak için

    private static final String TAG = "KategoriHaberleriFrag";

    public KategoriHaberleriFragment() {
        // Boş constructor gerekli
    }

    public static KategoriHaberleriFragment newInstance(String kategoriAdi) {
        KategoriHaberleriFragment fragment = new KategoriHaberleriFragment();
        Bundle args = new Bundle();
        args.putString(ARG_KATEGORI_ADI, kategoriAdi);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kategoriAdi = getArguments().getString(ARG_KATEGORI_ADI);
        }
        // Firebase Realtime Database referansını al
        databaseReferenceDuyurular = FirebaseDatabase.getInstance().getReference("duyurular");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Doğru layout dosyasını inflate ettiğinizden emin olun
        View view = inflater.inflate(R.layout.fragment_kategori_haberleri, container, false);

        // View'ları ID'leriyle bulma
        kategoriBaslikTextView = view.findViewById(R.id.kategori_baslik_textview);
        haberlerRecyclerView = view.findViewById(R.id.haberler_recyclerview);
        veriYokTextView = view.findViewById(R.id.veri_yok_textview); // Bu ID'nin XML'de olduğundan emin olun

        if (kategoriAdi != null) {
            // String kaynaklarını kullanmak daha iyidir, ancak şimdilik direkt birleştirelim
            kategoriBaslikTextView.setText(kategoriAdi + " Haberleri");
        } else {
            kategoriBaslikTextView.setText("Kategori Haberleri");
        }

        haberlerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        haberListesi = new ArrayList<>();
        haberAdapter = new HaberAdapter(getContext(), haberListesi);
        haberlerRecyclerView.setAdapter(haberAdapter);

        if (kategoriAdi != null && !kategoriAdi.isEmpty()) {
            loadHaberler(kategoriAdi);
        } else {
            Log.e(TAG, "Kategori adı onCreate'de alınamadı veya boş.");
            veriYokTextView.setText("Kategori bilgisi bulunamadı.");
            veriYokTextView.setVisibility(View.VISIBLE);
            haberlerRecyclerView.setVisibility(View.GONE);
        }

        return view;
    }

    private void loadHaberler(String kategori) {
        veriYokTextView.setText(kategori + " kategorisi için haberler yükleniyor...");
        veriYokTextView.setVisibility(View.VISIBLE);
        haberlerRecyclerView.setVisibility(View.GONE);

        // Daha önce oluşturulmuş bir listener varsa önce onu kaldır
        if (haberlerQuery != null && haberlerEventListener != null) {
            haberlerQuery.removeEventListener(haberlerEventListener);
        }

        haberlerQuery = databaseReferenceDuyurular.orderByChild("kategori").equalTo(kategori);

        haberlerEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                haberListesi.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HaberModel haber = snapshot.getValue(HaberModel.class);
                        if (haber != null) {
                            haberListesi.add(haber);
                        }
                    }

                    // Tarihe göre sıralama (String karşılaştırması, en yeniden eskiye)
                    Collections.sort(haberListesi, (h1, h2) -> {
                        if (h1.getTarih() == null && h2.getTarih() == null) return 0;
                        if (h1.getTarih() == null) return 1;
                        if (h2.getTarih() == null) return -1;
                        return h2.getTarih().compareTo(h1.getTarih());
                    });

                    haberAdapter.notifyDataSetChanged();

                    if (haberListesi.isEmpty()) {
                        veriYokTextView.setText("Bu kategoride henüz haber bulunmuyor.");
                        veriYokTextView.setVisibility(View.VISIBLE);
                        haberlerRecyclerView.setVisibility(View.GONE);
                    } else {
                        veriYokTextView.setVisibility(View.GONE);
                        haberlerRecyclerView.setVisibility(View.VISIBLE);
                    }
                } else {
                    veriYokTextView.setText("Bu kategoride henüz haber bulunmuyor.");
                    veriYokTextView.setVisibility(View.VISIBLE);
                    haberlerRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Haberleri çekerken hata oluştu.", databaseError.toException());
                veriYokTextView.setText("Haberler yüklenirken bir hata oluştu.");
                veriYokTextView.setVisibility(View.VISIBLE);
                haberlerRecyclerView.setVisibility(View.GONE);
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Haberler yüklenemedi: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };

        haberlerQuery.addValueEventListener(haberlerEventListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Fragment yok edildiğinde listener'ı kaldır
        if (haberlerQuery != null && haberlerEventListener != null) {
            haberlerQuery.removeEventListener(haberlerEventListener);
            Log.d(TAG, "Haberler için ValueEventListener kaldırıldı.");
        }
    }
}