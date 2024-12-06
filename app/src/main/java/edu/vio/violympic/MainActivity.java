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

public class MainActivity extends AppCompatActivity {

   private BaseViewModel viewModel;
    private BubbleTabBar bottomNavMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavMain = findViewById(R.id.bottomNavMain);

        ArrayList<BubbleMenuItem> menus = new ArrayList<>();
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, true));
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, false));
        menus.add(new BubbleMenuItem(menus.size(), "Trang chủ", R.drawable.baseline_home_24, true, false));
        menus.add(new BubbleMenuItem(menus.size(), "Tài khoản", R.drawable.baseline_person_24, true, false));
        bottomNavMain.setMenus(menus);

        bottomNavMain.setOnBubbleReselectedListener(id -> {

        });
    }
}
