package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.adapters.ThoughtAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private RecyclerView rvCategories, rvThoughts;
    private Button btnLogout;

    private SharedPreferences sharedPreferences;

    private CategoryAdapter categoryAdapter;
    private ThoughtAdapter thoughtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SAME shared preference name
        sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);

        // Check login status
        boolean isLoggedIn =
                sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {

            Intent intent =
                    new Intent(MainActivity.this,
                            LoginActivity.class);

            startActivity(intent);
            finish();
            return;
        }

        initViews();

        setupRecyclerViews();

        loadUserInfo();

        loadCategories();

        loadThoughts();

        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void initViews() {

        tvWelcome = findViewById(R.id.tvWelcome);

        rvCategories = findViewById(R.id.rvCategories);

        rvThoughts = findViewById(R.id.rvThoughts);

        btnLogout = findViewById(R.id.btnLogout);
    }

    private void setupRecyclerViews() {

        categoryAdapter = new CategoryAdapter();

        rvCategories.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));

        rvCategories.setAdapter(categoryAdapter);

        thoughtAdapter = new ThoughtAdapter();

        rvThoughts.setLayoutManager(
                new LinearLayoutManager(this));

        rvThoughts.setAdapter(thoughtAdapter);

        // Category click
        categoryAdapter.setOnCategoryClickListener(category -> {

            Toast.makeText(
                    this,
                    "Opening " + category.getName(),
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    private void loadUserInfo() {

        String userName =
                sharedPreferences.getString(
                        "user_name",
                        "User"
                );

        tvWelcome.setText("Welcome, " + userName + " 🌟");
    }

    private void loadCategories() {

        List<CategoryAdapter.CategoryItem> categories =
                new ArrayList<>();

        categories.add(new CategoryAdapter.CategoryItem(
                "1",
                "Inspiration",
                "💡",
                24
        ));

        categories.add(new CategoryAdapter.CategoryItem(
                "2",
                "Love",
                "❤️",
                18
        ));

        categories.add(new CategoryAdapter.CategoryItem(
                "3",
                "Success",
                "🎯",
                32
        ));

        categories.add(new CategoryAdapter.CategoryItem(
                "4",
                "Peace",
                "🧘",
                15
        ));

        categories.add(new CategoryAdapter.CategoryItem(
                "5",
                "Motivation",
                "🚀",
                28
        ));

        categories.add(new CategoryAdapter.CategoryItem(
                "6",
                "Learning",
                "📚",
                21
        ));

        categoryAdapter.setCategories(categories);
    }

    private void loadThoughts() {

        List<ThoughtAdapter.ThoughtItem> thoughts =
                new ArrayList<>();

        thoughts.add(new ThoughtAdapter.ThoughtItem(
                "1",
                "Believe in Yourself",
                "You are capable of amazing things.",
                "💡 Motivation"
        ));

        thoughts.add(new ThoughtAdapter.ThoughtItem(
                "2",
                "Small Steps",
                "Every expert was once a beginner.",
                "🚀 Inspiration"
        ));

        thoughts.add(new ThoughtAdapter.ThoughtItem(
                "3",
                "Gratitude",
                "Be thankful for what you have.",
                "🧘 Peace"
        ));

        thoughts.add(new ThoughtAdapter.ThoughtItem(
                "4",
                "Dream Big",
                "Challenge your limits.",
                "🎯 Success"
        ));

        thoughtAdapter.setThoughts(thoughts);
    }

    private void performLogout() {

        SharedPreferences.Editor editor =
                sharedPreferences.edit();

        editor.clear();

        editor.apply();

        Toast.makeText(
                this,
                "Logged out successfully",
                Toast.LENGTH_SHORT
        ).show();

        Intent intent =
                new Intent(MainActivity.this,
                        LoginActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK
        );

        startActivity(intent);

        finish();
    }
}