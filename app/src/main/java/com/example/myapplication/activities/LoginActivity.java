package com.example.myapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.models.LoginRequest;
import com.example.myapplication.models.LoginResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etPhone, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;
    private TextView tvError, tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(v -> performLogin());

        tvRegisterLink.setOnClickListener(v -> {

            startActivity(
                    new Intent(LoginActivity.this,
                            RegisterActivity.class)
            );
        });
    }

    private void initViews() {

        etPhone = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        progressBar = findViewById(R.id.progressBar);

        tvError = findViewById(R.id.tvError);

        tvRegisterLink = findViewById(R.id.tvRegisterLink);
    }

    private void performLogin() {

        tvError.setVisibility(View.GONE);

        String phone =
                etPhone.getText().toString().trim();

        String password =
                etPassword.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(phone)) {

            etPhone.setError("Phone number required");
            return;
        }

        if (phone.length() != 10) {

            etPhone.setError("Enter valid 10 digit number");
            return;
        }

        if (TextUtils.isEmpty(password)) {

            etPassword.setError("Password required");
            return;
        }

        showLoading(true);

        ApiService apiService =
                RetrofitClient.getClient()
                        .create(ApiService.class);

        LoginRequest request =
                new LoginRequest(phone, password);

        apiService.login(request)
                .enqueue(new Callback<LoginResponse>() {

                    @Override
                    public void onResponse(
                            Call<LoginResponse> call,
                            Response<LoginResponse> response) {

                        showLoading(false);

                        if (response.isSuccessful()
                                && response.body() != null) {

                            LoginResponse loginResponse =
                                    response.body();

                            if (loginResponse.success) {

                                String token = "";

                                if (loginResponse.result != null) {

                                    token =
                                            loginResponse.result.accessToken;
                                }

                                // Save token
                                SharedPreferences prefs =
                                        getSharedPreferences(
                                                "MyApp",
                                                MODE_PRIVATE
                                        );

                                prefs.edit()
                                        .putString("TOKEN", token)
                                        .putBoolean("isLoggedIn", true)
                                        .putString("user_name", loginResponse.result.name)
                                        .apply();

                                Toast.makeText(
                                        LoginActivity.this,
                                        "Login Successful",
                                        Toast.LENGTH_SHORT
                                ).show();

                                startActivity(
                                        new Intent(
                                                LoginActivity.this,
                                                MainActivity.class
                                        )
                                );

                                finish();

                            } else {

                                tvError.setText(
                                        loginResponse.message
                                );

                                tvError.setVisibility(View.VISIBLE);
                            }

                        } else {

                            tvError.setText(
                                    "Server Error : "
                                            + response.code()
                            );

                            tvError.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(
                            Call<LoginResponse> call,
                            Throwable t) {

                        showLoading(false);

                        tvError.setText(
                                "Network Error : "
                                        + t.getMessage()
                        );

                        tvError.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showLoading(boolean show) {

        progressBar.setVisibility(
                show ? View.VISIBLE : View.GONE
        );

        btnLogin.setEnabled(!show);

        btnLogin.setText(
                show ? "Logging in..." : "Login"
        );
    }
}