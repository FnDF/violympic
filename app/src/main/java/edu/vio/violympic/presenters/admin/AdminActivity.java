package edu.vio.violympic.presenters.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Collections;

import edu.vio.violympic.R;
import edu.vio.violympic.adapter.TopicSetAdapter;
import edu.vio.violympic.databinding.ActivityAdminBinding;
import edu.vio.violympic.model.TopicSet;

public class AdminActivity extends AppCompatActivity implements TopicSetAdapter.OnTopicSetClickListener {

    private ActivityAdminBinding binding;
    private TopicSetAdapter topicSetAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        topicSetAdapter = new TopicSetAdapter(Collections.emptyList(), this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(topicSetAdapter);

        binding.btnCreateTopicSet.setOnClickListener(v -> {

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: get data from server
        topicSetAdapter.setDataSource(Collections.emptyList());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onTopicSetDetail(TopicSet topicSet) {

    }
}
