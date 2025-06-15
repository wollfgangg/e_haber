package com.e_haber.Fragments;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.e_haber.Adapter.HomeCategoryAdapter;
import com.e_haber.Adapter.KategoriAdapter;
import com.e_haber.Adapter.TrendAdapter;
import com.e_haber.Model.HomeCategoryModel;
import com.e_haber.Model.KategoriModel;
import com.e_haber.Model.TrendModel;
import com.e_haber.R;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements KategoriAdapter.OnKategoriClickListener {

    // Eklenen üst kategori bar:
    private RecyclerView homeCategoryRecycler;
    private HomeCategoryAdapter homeCategoryAdapter;
    private List<HomeCategoryModel> homeCatList;

    private RecyclerView kategoriRecler;
    private KategoriAdapter kategoriAdapter;
    private List<KategoriModel> kategoriModelList;

    private ViewPager imageSliderViewPager;
    private RecyclerView trendRecycler;
    private TrendAdapter trendAdapter;
    private List<TrendModel> trendList;
    private Handler sliderHandler = new Handler();

    private int[] sliderImages = {
            R.drawable.nlp1,
            R.drawable.siyaset1,
            R.drawable.coin1,
            R.drawable.spor1,
    };
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (imageSliderViewPager != null && sliderImages.length > 0) {
                int currentItem = imageSliderViewPager.getCurrentItem();
                int nextItem = (currentItem + 1) % sliderImages.length;
                imageSliderViewPager.setCurrentItem(nextItem, true);
                sliderHandler.postDelayed(this, 3000);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // --- Üstteki YATAY kategori bar ---
        homeCategoryRecycler = view.findViewById(R.id.homeCategoryRecycler);
        homeCategoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        homeCatList = new ArrayList<>();
        homeCatList.add(new HomeCategoryModel(R.drawable.teknoloji, "Teknoloji"));
        homeCatList.add(new HomeCategoryModel(R.drawable.sspor, "Spor"));
        homeCatList.add(new HomeCategoryModel(R.drawable.ssiyaset, "Siyaset"));
        homeCatList.add(new HomeCategoryModel(R.drawable.eekonomi, "Ekonomi"));
        homeCatList.add(new HomeCategoryModel(R.drawable.gundem, "Gündem"));
        homeCatList.add(new HomeCategoryModel(R.drawable.hhava, "Hava Durumu"));
        homeCategoryAdapter = new HomeCategoryAdapter(homeCatList);
        homeCategoryRecycler.setAdapter(homeCategoryAdapter);

        // --- Eski kategori/slider/trending kodların aşağıda devam edecek şekilde bırakıldı ---
        //kategoriRecler = view.findViewById(R.id.kategoriRecyclerView);
        // LinearLayoutManager kategoriLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //  kategoriRecler.setLayoutManager(kategoriLinearLayoutManager);
        //  kategoriModelList = new ArrayList<>();
        // kategoriModelList.add(new KategoriModel(R.drawable.teknoloji, "Teknoloji"));
        // kategoriModelList.add(new KategoriModel(R.drawable.sspor, "Spor"));
        // kategoriModelList.add(new KategoriModel(R.drawable.ssiyaset, "Siyaset"));
        // kategoriModelList.add(new KategoriModel(R.drawable.eekonomi, "Ekonomi"));
        //  kategoriModelList.add(new KategoriModel(R.drawable.gundem, "Gündem"));
        // kategoriModelList.add(new KategoriModel(R.drawable.hhava, "Hava Durumu"));
        //kategoriAdapter = new KategoriAdapter(kategoriModelList, this);
        //  kategoriRecler.setAdapter(kategoriAdapter);

        imageSliderViewPager = view.findViewById(R.id.imageSliderViewPager);
        ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(getContext(), sliderImages);
        imageSliderViewPager.setAdapter(sliderAdapter);

        trendRecycler = view.findViewById(R.id.trendRecyclerView);
        trendRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        trendList = new ArrayList<>();
        trendList.add(new TrendModel(
                R.drawable.gundem1, "Gundem", "Türkçe Doğal Dil İşleme Yarışması",
                "12/02/2024", "https://www.teknofest.org/tr/yarismalar/turkce-dogal-dil-isleme-yarismasi/"));
        trendList.add(new TrendModel(
                R.drawable.ssiyaset, "Siyaset", "Cumhurbaşkanı Erdoğan'dan önemli açıklamalar",
                "12/02/2024", "https://www.haber7.com/siyaset/haber/3536546-cumhurbaskani-erdogandan-onemli-aciklamalar"));
        trendList.add(new TrendModel(
                R.drawable.eekonomi, "Ekonomi", "THY'den yeni rekor: Bir günde 1369 uçuş",
                "12/02/2024", "https://www.trthaber.com/haber/ekonomi/thyden-yeni-rekor-bir-gunde-1369-ucus-909909.html"));
        trendList.add(new TrendModel(
                R.drawable.sspor, "Spor", "Sonunda tercihini yaptı: Osimhen'in oynamak istediği takımı duyurdular",
                "12/02/2024", "https://www.ntvspor.net/foto-galeri/sonunda-tercihini-yapti-osimhen-in-oynamak-istedigi-takimi-duyurdular-684695058ab58203f7e37406"));
        trendAdapter = new TrendAdapter(trendList);
        trendRecycler.setAdapter(trendAdapter);

        imageSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override public void onPageSelected(int position) {
                sliderHandler.removeCallbacks(sliderRunnable);
                if (sliderImages.length > 0) {
                    sliderHandler.postDelayed(sliderRunnable, 3000);
                }
            }
            @Override public void onPageScrollStateChanged(int state) {}
        });
        return view;
    }
    @Override
    public void onKategoriClick(KategoriModel kategori) {
        if (getContext() != null && kategori != null) {
            Toast.makeText(getContext(), "Tıklanan kategori: " + kategori.getKategoriAdi(), Toast.LENGTH_SHORT).show();
            Log.d("HomeFragment", "Kategori tıklandı: " + kategori.getKategoriAdi());
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (sliderImages.length > 0) {
            sliderHandler.postDelayed(sliderRunnable, 3000);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sliderHandler.removeCallbacks(sliderRunnable);
    }
}