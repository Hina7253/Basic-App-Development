package com.example.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private RecyclerView rvData;
    private Button btnLogout;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);

        // Check if logged in
        if (!sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        initViews();
        loadUserInfo();
        loadData();

        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void initViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        rvData = findViewById(R.id.rvData);
        btnLogout = findViewById(R.id.btnLogout);
        progressBar = findViewById(R.id.progressBar);

        dataAdapter = new DataAdapter();
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(dataAdapter);
    }

    private void loadUserInfo() {
        String userName = sharedPreferences.getString("userName", "User");
        tvWelcome.setText("Welcome, " + userName + "! 👋");
    }

    private void loadData() {
        progressBar.setVisibility(android.view.View.VISIBLE);

        // Simulate loading data
        new Handler().postDelayed(() -> {
            progressBar.setVisibility(android.view.View.GONE);

            // Your data - Replace with API call later
            List<String> dataList = new ArrayList<>();
            dataList.add("📱 Profile Information");
            dataList.add("💰 Recent Transactions");
            dataList.add("⚙️ Account Settings");
            dataList.add("🔔 Notifications");
            dataList.add("⭐ Favorites");
            dataList.add("📊 Statistics");
            dataList.add("🔒 Security");
            dataList.add("🎨 Theme Settings");

            dataAdapter.setData(dataList);
        }, 1500);
    }

    private void performLogout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Snackbar.make(findViewById(android.R.id.content), "Logged out successfully", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }, 1000);
    }
}