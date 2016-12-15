package com.boxme.githubsample.githhubsample;

import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import com.boxme.githubsample.githhubsample.CustomComponents.CustomTextView;

import com.boxme.githubsample.githhubsample.Models.Owner;
import com.boxme.githubsample.githhubsample.Models.Repo;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vth02 on 15-Dec-16.
 */

public class RepositoryDetailsActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingActionButton;
    private CustomTextView mTxtRepoName;
    private CustomTextView mTxtRepoDescription;
    private CustomTextView mTxtOwnerId;
    private CustomTextView mTxtRepoStargazersCount;
    private CustomTextView mTxtWatchersCount;
    private CircleImageView mImgOwnerAvatar;

    private Repo mRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        initViews();

        mRepo = getIntent().getParcelableExtra("repo");
        if (mRepo != null) {
            String title = mRepo.getFull_name();
            if(title != null) {
             getSupportActionBar().setTitle(title);
            }
           fillData();
        }
    }

    // Initialize all the views in layout
    private void initViews() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mTxtRepoName = (CustomTextView) findViewById(R.id.txt_repo_name);
        mTxtRepoDescription = (CustomTextView) findViewById(R.id.txt_repo_description);
        mTxtOwnerId = (CustomTextView) findViewById(R.id.txt_owner_id);
        mTxtRepoStargazersCount = (CustomTextView) findViewById(R.id.txt_stargazers_count);
        mTxtWatchersCount = (CustomTextView) findViewById(R.id.txt_watchers_count);
        mImgOwnerAvatar = (CircleImageView) findViewById(R.id.owner_avatar);
    }

    // Fill the views with data
    private void fillData() {
        mFloatingActionButton.setBackgroundTintList((ColorStateList) getIntent().getParcelableExtra("fabColor"));
        mTxtRepoName.setText(mRepo.getName());
        mTxtRepoDescription.setText(mRepo.getDescription());
        mTxtOwnerId.setText(String.valueOf(mRepo.getId()));
        mTxtRepoStargazersCount.setText(String.valueOf(mRepo.getStargazers_count()));
        mTxtWatchersCount.setText(String.valueOf(mRepo.getWatchers_count()));

        Owner owner = mRepo.getOwner();

        if(owner != null) {
            Picasso.with(this)
                 .load(owner.getAvatar_url())
                 .placeholder(R.drawable.ic_avatar_placeholder)
                 .into(mImgOwnerAvatar);
        }
    }
}
