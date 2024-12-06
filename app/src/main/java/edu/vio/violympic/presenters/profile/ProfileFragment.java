package edu.vio.violympic.presenters.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.vio.violympic.LoginActivity;
import edu.vio.violympic.RegisterActivity;
import edu.vio.violympic.databinding.FragmentProfileBinding;
import edu.vio.violympic.viewmodel.AccountViewModel;

public class ProfileFragment extends Fragment {

    private AccountViewModel viewModel;
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupObserver();

        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(requireActivity(), LoginActivity.class));
        });

        binding.btnRegister.setOnClickListener(v-> {
            startActivity(new Intent(requireActivity(), RegisterActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.checkUser();
    }

    private void setupObserver() {
        viewModel.getStateLogin().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean == null) return;
            if (aBoolean) {

            } else {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
