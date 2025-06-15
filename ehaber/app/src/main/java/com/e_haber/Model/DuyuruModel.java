package com.e_haber.Model;

public class DuyuruModel {
    private String baslik;
    private String icerik;
    private String resimUrl; // Firebase'deki alan adıyla aynı!

    public DuyuruModel() {}

    public DuyuruModel(String baslik, String icerik, String resimUrl) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.resimUrl = resimUrl;
    }

    public String getBaslik() { return baslik; }
    public void setBaslik(String baslik) { this.baslik = baslik; }

    public String getIcerik() { return icerik; }
    public void setIcerik(String icerik) { this.icerik = icerik; }

    public String getResimUrl() { return resimUrl; }
    public void setResimUrl(String resimUrl) { this.resimUrl = resimUrl; }
}