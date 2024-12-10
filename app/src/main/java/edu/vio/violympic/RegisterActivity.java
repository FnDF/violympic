package edu.vio.violympic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import edu.vio.violympic.databinding.ActivityRegisterBinding;
import edu.vio.violympic.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;
    private boolean isSkipClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.txtLogin.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

        binding.btnRegister.setOnClickListener(v -> {
            if (isSkipClick) return;
            String email = binding.edtEmail.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            String confirmPassword = binding.edtPasswordConfirm.getText().toString().trim();
            String fullName = binding.edtFullName.getText().toString().trim();
            String username = binding.edtUsername.getText().toString().trim();
            String phone = binding.edtPhone.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                binding.edtEmail.setError("Vui lòng nhập email");
                binding.edtEmail.requestFocus();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.setError("Vui lòng nhập đúng địa chỉ email");
                binding.edtEmail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(fullName)) {
                binding.edtFullName.setError("Vui lòng nhập Họ và tên");
                binding.edtFullName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                binding.edtPhone.setError("Vui lòng nhập số điện thoại");
                binding.edtPhone.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(username)) {
                binding.edtUsername.setError("Vui lòng nhập username");
                binding.edtUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                binding.edtPassword.setError("Vui lòng nhập mật khẩu");
                binding.edtPassword.requestFocus();
                return;
            }
            if (password.length() < 6) {
                binding.edtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
                binding.edtPassword.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(confirmPassword)) {
                binding.edtPasswordConfirm.setError("Vui lòng nhập xác nhận mật khẩu");
                binding.edtPasswordConfirm.requestFocus();
                return;
            }
            if (!password.equals(confirmPassword)) {
                binding.edtPasswordConfirm.setError("Mật khẩu không khớp");
                binding.edtPasswordConfirm.requestFocus();
                return;
            }
            showLoading();
            viewModel.register(email, password, fullName, username, phone);
        });
        setupObserver();
    }

    private void setupObserver() {
        viewModel.getStateUsernameIsExists().observe(this, isExists -> {
            if (isExists) {
                binding.edtUsername.setError("Username đã tồn tại");
            }
        });
        viewModel.getStateRegisterObserver().observe(this, registered -> {
            if (registered) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                hideLoading();
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading() {
        isSkipClick = true;
        binding.btnRegister.setText("");
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        isSkipClick = false;
        binding.btnRegister.setText(R.string.register);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
