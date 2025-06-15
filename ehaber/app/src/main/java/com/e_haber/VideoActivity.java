package com.e_haber;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video); // Yeni bir layout oluştur

        VideoView videoView = findViewById(R.id.videoView);
        int resId = getIntent().getIntExtra("videoResId", -1);

        if(resId != -1){
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + resId);
            videoView.setVideoURI(uri);
            MediaController controller = new MediaController(this);
            videoView.setMediaController(controller);
            videoView.start();
        }
    }
}