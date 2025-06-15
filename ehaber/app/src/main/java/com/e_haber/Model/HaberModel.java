package com.e_haber.Model; // Paket adınızı kontrol edin

// Firebase'den gelen veriyi direkt objeye çevirebilmesi için
// alan adlarının veritabanındaki anahtarlarla aynı olması önemlidir.
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties // Veritabanında olmayan ekstra alanları modelde görmezden gel
public class HaberModel {
    private String baslik;
    private String icerik;
    private String resimUrl;
    private String tarih;    // Görseldeki gibi String formatında
    private String kategori; // Bu alanı ekledik

    // Firebase'in veriyi direkt objeye çevirebilmesi için boş constructor şart!
    public HaberModel() {
    }

    public HaberModel(String baslik, String icerik, String resimUrl, String tarih, String kategori) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.resimUrl = resimUrl;
        this.tarih = tarih;
        this.kategori = kategori;
    }

    // Getter ve Setter metodları
    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}