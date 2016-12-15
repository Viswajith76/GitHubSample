package com.boxme.githubsample.githhubsample.Utils;

import com.boxme.githubsample.githhubsample.Models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vth02 on 15-Dec-16.
 */

public interface WebServiceInterface {

    @GET("users/{username}/repos")
    Call<List<Repo>> getRepoDetails(@Path("username") String userName);
}
