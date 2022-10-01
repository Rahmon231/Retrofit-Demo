package com.lemzeeyyy.retrofitdemo;

import static com.lemzeeyyy.retrofitdemo.MainActivity.BASEURL;
import static com.lemzeeyyy.retrofitdemo.MainActivity.ENDPOINT;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiCall {

    //https://jsonplaceholder.typicode.com/ ====> base url
    // posts ====> end point
    @GET(ENDPOINT)
    Call<DataModel> getModel();
}
