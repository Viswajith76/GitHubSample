package com.boxme.githubsample.githhubsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import com.boxme.githubsample.githhubsample.CustomComponents.CustomTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.boxme.githubsample.githhubsample.Adapters.RepositoryListingAdapter;
import com.boxme.githubsample.githhubsample.Models.Repo;
import com.boxme.githubsample.githhubsample.Utils.WebServiceUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vth02 on 15-Dec-16.
 */

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRepoListRecycler;
    private ProgressBar mProgressBar;
    private CustomTextView mTxtNoResults;

    private RepositoryListingAdapter mRepositoryListingAdapter;
    private List<Repo> mRepoList = new ArrayList<>();

    private String mUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initAdapters();

        mUserName = getIntent().getStringExtra("userName");
        getRepoDetails();

        if (mUserName != null) {
            getSupportActionBar().setTitle(mUserName);
        }
    }

    // Initialize all the views
    private void initViews() {
        mRepoListRecycler = (RecyclerView) findViewById(R.id.repo_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mTxtNoResults = (CustomTextView) findViewById(R.id.txt_no_results);
        mRepoListRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    // Initialize the adapters used
    private void initAdapters() {
        mRepositoryListingAdapter = new RepositoryListingAdapter(this, mRepoList);
        mRepoListRecycler.setAdapter(mRepositoryListingAdapter);
    }

    // Fetch the repository data from the webservice
    private void getRepoDetails() {
        Call<List<Repo>> call = WebServiceUtil.getServiceInstance().getRepoDetails(mUserName);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Repo> repoList = response.body();
                    if (repoList.isEmpty()) {
                        mTxtNoResults.setVisibility(View.VISIBLE);
                    } else {
                        mRepoList.addAll(response.body());
                        mRepositoryListingAdapter.notifyDataSetChanged();
                    }
                } else {
                    mTxtNoResults.setText(R.string.invalid_user);
                    mTxtNoResults.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mTxtNoResults.setText(R.string.service_error);
                mTxtNoResults.setVisibility(View.VISIBLE);
            }
        });
    }
}
