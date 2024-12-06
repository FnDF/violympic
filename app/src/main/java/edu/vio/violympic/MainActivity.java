package edu.vio.violympic;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import edu.vio.navigation.BubbleTabBar;
import edu.vio.navigation.parser.BubbleMenuItem;
import edu.vio.violympic.base.BaseViewModel;
import edu.vio.violympic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<BubbleMenuItem> menus = new ArrayList<>();
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, true));
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, false));
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, false));
        menus.add(new BubbleMenuItem(menus.size(), "Tài khoản", R.drawable.baseline_person_24, true, false));
        binding.bottomNavMain.setMenus(menus);

        binding.bottomNavMain.setOnBubbleReselectedListener(id -> {

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
