package com.e_haber;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.e_haber.Model.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

// Firebase Messaging için
import com.google.firebase.messaging.FirebaseMessaging;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView goToRegisterText, continueWithoutLoginText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 🌟 BURASI: Her kullanıcıda, giriş EKRANI açılır açılmaz allUsers topic'ine abone et!
        FirebaseMessaging.getInstance().subscribeToTopic("allUsers")
                .addOnCompleteListener(task -> {
                    String msg = "Konu aboneliği: " + (task.isSuccessful() ? "başarılı" : "başarısız");
                    Log.d("FCM", msg);
                });

        // XML'deki view'ları tanımla
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        goToRegisterText = findViewById(R.id.goToRegisterText);
        continueWithoutLoginText = findViewById(R.id.continueWithoutLoginText);

        // Firebase Auth başlat
        mAuth = FirebaseAuth.getInstance();

        // Giriş Butonu
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("E-posta giriniz!");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Şifre giriniz!");
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                            // Giriş başarılıysa ana ekrana yönlendir
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // geri tuşunu kapat
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Giriş başarısız: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Kayıt Ol Linki
        goToRegisterText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        // Giriş yapmadan devam et
        continueWithoutLoginText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}