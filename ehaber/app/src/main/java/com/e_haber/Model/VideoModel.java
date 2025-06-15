package com.e_haber.Model;

public class VideoModel {
    private int videoRawResId;    // raw klasöründeki video dosyanın R id'si
    private String baslik;
    private int drawableResId;    // drawable'daki resmin R id'si

    public VideoModel(int videoRawResId, String baslik, int drawableResId) {
        this.videoRawResId = videoRawResId;
        this.baslik = baslik;
        this.drawableResId = drawableResId;
    }

    public int getVideoRawResId() { return videoRawResId; }
    public String getBaslik()     { return baslik; }
    public int getDrawableResId() { return drawableResId; }
}