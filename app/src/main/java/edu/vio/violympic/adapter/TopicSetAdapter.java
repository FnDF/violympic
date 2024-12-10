package edu.vio.violympic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.vio.violympic.R;
import edu.vio.violympic.databinding.ItemTopicSetBinding;
import edu.vio.violympic.model.TopicSet;

@SuppressLint("NotifyDataSetChanged")
public class TopicSetAdapter extends RecyclerView.Adapter<TopicSetAdapter.TopicSetViewHolder> {

    public interface OnTopicSetClickListener {
        void onTopicSetDetail(TopicSet topicSet);
    }

    private final OnTopicSetClickListener onTopicSetClickListener;
    private List<TopicSet> topicSets;

    public TopicSetAdapter(List<TopicSet> topicSets, OnTopicSetClickListener onTopicSetClickListener) {
        this.topicSets = topicSets;
        this.onTopicSetClickListener = onTopicSetClickListener;
    }

    public void setDataSource(List<TopicSet> topicSets) {
        this.topicSets = topicSets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopicSetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTopicSetBinding binding = ItemTopicSetBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TopicSetViewHolder(binding, onTopicSetClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicSetViewHolder holder, int position) {
        if (position < topicSets.size()) {
            holder.bind(topicSets.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return topicSets != null ? topicSets.size() : 0;
    }

    public static class TopicSetViewHolder extends RecyclerView.ViewHolder {
        private final ItemTopicSetBinding binding;
        private final Context context;
        private final OnTopicSetClickListener onTopicSetClickListener;

        public TopicSetViewHolder(ItemTopicSetBinding binding, OnTopicSetClickListener onTopicSetClickListener) {
            super(binding.getRoot());

            this.binding = binding;
            context = binding.getRoot().getContext();
            this.onTopicSetClickListener = onTopicSetClickListener;
        }

        public void bind(TopicSet topicSet) {
            binding.tvIdTopic.setText(context.getString(R.string.itv_id_topic, topicSet.getId()));
            binding.tvSubject.setText(context.getString(R.string.itv_subject, topicSet.getSubject()));
            binding.tvGradeTh.setText(context.getString(R.string.itv_grade_th, topicSet.getGradeTh()));
            binding.getRoot().setOnClickListener(v -> onTopicSetClickListener.onTopicSetDetail(topicSet));
        }
    }
}
