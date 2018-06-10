package com.android.pharmeasytest.view.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.pharmeasytest.R;
import com.android.pharmeasytest.databinding.UserListItemBinding;
import com.android.pharmeasytest.service.model.model.Data;
import com.android.pharmeasytest.view.callback.OnClickCallback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    public static final int VIEW_TYPE_LOAD_MORE = R.layout.more_laoding_layout;

    private List<Data> mUserData;
    private UserListAdapterListener mChangeListener;
    private int mLastMoreRequestPos;

    public UserListAdapter(List<Data> userData, UserListAdapterListener listener) {
        mUserData = userData;
        mChangeListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int view;
        switch (viewType) {
            case VIEW_TYPE_LOAD_MORE:
                view = R.layout.more_laoding_layout;
                return new ViewHolder((LayoutInflater
                        .from(parent.getContext())
                        .inflate(view, parent, false)));
            default:
                UserListItemBinding postBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.user_list_item,
                        parent,
                        false);
                  postBinding.setCallback(new OnClickCallback());
                return new ViewHolder(postBinding);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        switch (getItemViewType(position)) {
            case VIEW_TYPE_LOAD_MORE:
                if (mLastMoreRequestPos != position) {
                    mChangeListener.onLoadMore();
                    mLastMoreRequestPos = position;
                }
                break;
            default:
                holder.binding.setDataBind(mUserData.get(position));
        }
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @Override
    public int getItemCount() {
        return mUserData != null ? mUserData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mUserData.get(position).getViewType();
    }

    public void updateList(List<Data> dataList) {
        mUserData = dataList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private UserListItemBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
        }

        ViewHolder(UserListItemBinding postBinding) {
            super(postBinding.getRoot());
            binding = postBinding;
        }

    }

    public interface UserListAdapterListener {
        void onLoadMore();

    }

}
