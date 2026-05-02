package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView tvError, tvRegisterLink;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);

        // Already logged in check
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        initViews();

        btnLogin.setOnClickListener(v -> performLogin());

        tvRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        tvError = findViewById(R.id.tvError);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);
    }

    private void performLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password required");
            return;
        }

        // 🔴🔴🔴 CHECK KARO - KYA USER REGISTERED HAI? 🔴🔴🔴
        String registeredEmail = sharedPreferences.getString("registered_email", null);
        String registeredPassword = sharedPreferences.getString("registered_password", null);

        // Agar user registered nahi hai
        if (registeredEmail == null) {
            tvError.setText("❌ No account found! Please register first.");
            tvError.setVisibility(android.view.View.VISIBLE);
            return;
        }

        // Check email and password match
        if (!email.equals(registeredEmail)) {
            tvError.setText("❌ Email not found. Please register first.");
            tvError.setVisibility(android.view.View.VISIBLE);
            return;
        }

        if (!password.equals(registeredPassword)) {
            tvError.setText("❌ Incorrect password. Please try again.");
            tvError.setVisibility(android.view.View.VISIBLE);
            return;
        }

        // ✅ Login success
        showLoading(true);

        new Handler().postDelayed(() -> {
            showLoading(false);

            String userName = email.contains("@") ? email.split("@")[0] : email;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.putString("user_name", userName);
            editor.putString("user_email", email);
            editor.apply();

            Toast.makeText(LoginActivity.this, "Login Successful! 🎉", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1500);
    }

    private void showLoading(boolean show) {
        progressBar.setVisibility(show ? android.view.View.VISIBLE : android.view.View.GONE);
        btnLogin.setEnabled(!show);
        btnLogin.setText(show ? "Logging in..." : "Login");
    }
}