package com.boxme.githubsample.githhubsample.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import com.boxme.githubsample.githhubsample.CustomComponents.CustomTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.boxme.githubsample.githhubsample.Models.Repo;
import com.boxme.githubsample.githhubsample.R;
import com.boxme.githubsample.githhubsample.RepositoryDetailsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vth02 on 15-Dec-16.
 */

public class RepositoryListingAdapter extends RecyclerView.Adapter<RepositoryListingAdapter.ViewHolder> {

    private Context mContext;
    private List<Repo> mRepoList;

    public RepositoryListingAdapter(Context context, List<Repo> repoList) {
        this.mContext = context;
        this.mRepoList = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.repository_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String repoName = mRepoList.get(position).getName();
        String repoDescription = mRepoList.get(position).getDescription();

        holder.mFloatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getRandomColor()));
        holder.mTxtRepoName.setText(repoName);
        holder.mTxtRepoDescription.setText(repoDescription);

        holder.mRepositorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RepositoryDetailsActivity.class);
                intent.putExtra("repo", mRepoList.get(position));
                intent.putExtra("fabColor", holder.mFloatingActionButton.getBackgroundTintList());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, holder.mFloatingActionButton, mContext.getString(R.string.animation_name));
                mContext.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    // Generate and returns Random colors
    public int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mRepositorylayout;
        public FloatingActionButton mFloatingActionButton;
        public CustomTextView mTxtRepoName;
        public CustomTextView mTxtRepoDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mRepositorylayout = (LinearLayout) itemView.findViewById(R.id.repository_layout);
            mFloatingActionButton = (FloatingActionButton) itemView.findViewById(R.id.fab);
            mTxtRepoName = (CustomTextView) itemView.findViewById(R.id.txt_repo_name);
            mTxtRepoDescription = (CustomTextView) itemView.findViewById(R.id.txt_repo_description);
        }
    }
}
