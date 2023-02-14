package com.example.randomuser.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.randomuser.R;
import com.example.randomuser.databinding.ItemListContentBinding;
import com.example.randomuser.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter
        extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> users = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    UserAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListContentBinding binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.setUser(user);

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(user));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateList(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;
        final ImageView mPictureView;

        UserViewHolder(ItemListContentBinding binding) {
            super(binding.getRoot());
            mIdView = binding.idText;
            mContentView = binding.content;
            mPictureView = binding.picture;
        }

        public void setUser(User user) {
            mIdView.setText(user.getName());
            mContentView.setText(user.getPhone());
            String url = user.getThumbnail();

            Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.profile_picture)
                    .into(mPictureView);
        }
    }

    interface OnItemClickListener{
        void onItemClick(User user);
    }
}
