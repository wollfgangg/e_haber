package com.e_haber.Model;
public class HomeCategoryModel {
    private int iconResId;
    private String kategoriAdi;
    public HomeCategoryModel(int iconResId, String kategoriAdi) {
        this.iconResId = iconResId;
        this.kategoriAdi = kategoriAdi;
    }
    public int getIconResId() { return iconResId; }
    public String getKategoriAdi() { return kategoriAdi; }
}