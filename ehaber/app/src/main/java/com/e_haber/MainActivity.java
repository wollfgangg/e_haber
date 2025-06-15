package com.e_haber;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
// FCM topic için:
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Siyah arka plan + merkezi resim

        // 🌟 Kullanıcıdan bağımsız olarak FCM topic abone yap (her açılışta tekrar denemekte sakınca yok)
        FirebaseMessaging.getInstance().subscribeToTopic("allUsers")
                .addOnCompleteListener(task -> {
                    String msg = "Konu aboneliği: " + (task.isSuccessful() ? "başarılı" : "başarısız");
                    Log.d("FCM", msg);
                });

        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                // Kullanıcı daha önce giriş yapmış, ana ekrana yönlendir
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            } else {
                // Kullanıcı giriş yapmamış, login ekranına yönlendir
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            finish(); // Splash activity kapansın
        }, 3000); // 3 saniye bekle
    }
}