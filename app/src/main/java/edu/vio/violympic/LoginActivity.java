package edu.vio.violympic;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import edu.vio.violympic.databinding.ActivityLoginBinding;
import edu.vio.violympic.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.initFirebaseAuth();
        viewModel.getStateRegisterObserver().observe(this, user -> {
            if (user != null) {
                // Login success
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", user);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                if (!binding.edtPassword.getText().toString().isEmpty()) {
                    // Login fail
                    binding.edtUsername.setError("Invalid username or password");
                    binding.edtPassword.setError("Invalid username or password");
                }
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            handleLogin();
        });
        binding.txtRegister.setOnClickListener(v -> {
            handleRegister();
        });

        binding.btnDemoAdmin.setOnClickListener(v -> {

        });
    }

    // Login
    private void handleLogin() {
        // Check if email/username and password are empty
        if (isValidInput()) {
            String emailText = binding.edtUsername.getText().toString().trim();
            String passwordText = binding.edtPassword.getText().toString().trim();
            viewModel.login(this, emailText, passwordText);
        }
    }

    private boolean isValidInput() {
        if (binding.edtUsername.getText().toString().isEmpty()) {
            binding.edtUsername.setError("Username is required");
            binding.edtUsername.requestFocus();
            return false;
        } else if (binding.edtPassword.getText().toString().isEmpty()) {
            binding.edtPassword.setError("Password is required");
            binding.edtPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    // Open RegisterActivity
    private void handleRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
