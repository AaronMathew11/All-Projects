package com.adgvit.papervit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API {

    @GET("/api/v1/subjects/0")
    Call<root> getSubCat1();

    @GET("/api/v1/subjects/1")
    Call<root> getSubCat2();

    @GET("/api/v1/subjects/2")
    Call<root> getSubFat();

    @GET("/api/v1/papers/{id}")
    Call<root1> getPaperCat1(@Path("id") String _id);

    @GET("/api/v1/papers/{id}")
    Call<root1> getPaperCat2(@Path("id") String _id);

    @GET("/api/v1/papers/{id}")
    Call<root1> getPaperFat(@Path("id") String _id);

    @Multipart
    @POST("/api/v1/public/paper")
    Call<ServerResponse> upload(@Part MultipartBody.Part file);
    

}