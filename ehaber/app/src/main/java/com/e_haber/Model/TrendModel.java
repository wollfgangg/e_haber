package com.e_haber.Model;

public class TrendModel {
    private int imageResId;     // drawable resmi
    private String kategori;
    private String baslik;
    private String tarih;
    private String url;         // Her trend haberinin linki

    // <-- GÜNCEL: url parametresi eklendi!
    public TrendModel(int imageResId, String kategori, String baslik, String tarih, String url) {
        this.imageResId = imageResId;
        this.kategori = kategori;
        this.baslik = baslik;
        this.tarih = tarih;
        this.url = url;
    }

    public int getImageResId() { return imageResId; }
    public String getKategori() { return kategori; }
    public String getBaslik() { return baslik; }
    public String getTarih() { return tarih; }
    public String getUrl() { return url; }  // <-- ÖNEMLİ YENİ GETTER!
}