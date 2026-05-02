package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.List;

public class ThoughtAdapter extends RecyclerView.Adapter<ThoughtAdapter.ViewHolder> {

    private List<ThoughtItem> thoughts = new ArrayList<>();

    public void setThoughts(List<ThoughtItem> thoughts) {
        this.thoughts = thoughts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thought, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThoughtItem thought = thoughts.get(position);
        holder.tvTitle.setText(thought.title);
        holder.tvDescription.setText(thought.description);
        holder.tvCategory.setText(thought.category);
    }

    @Override
    public int getItemCount() {
        return thoughts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }


    public static class ThoughtItem {
        public String id;
        public String title;
        public String description;
        public String category;

        public ThoughtItem(String id, String title, String description, String category) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.category = category;
        }
    }
}