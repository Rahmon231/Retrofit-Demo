package com.lemzeeyyy.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASEURL = "https://jsonplaceholder.typicode.com/";
    public static final String ENDPOINT = "posts/1";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance for interface
        MyApiCall myApiCall = retrofit.create(MyApiCall.class);
        Call<DataModel> call = myApiCall.getModel();
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                //Checking for response
                if(response.code() != 200){
                    textView.setText(response.message());
                    return;
                }
                //Get Data into textview
                String jsonResponse = "";
                Log.d("TAGY", "onResponse: "+response.body().getTitle());
                jsonResponse = "ID = "+response.body().getId() +
                        "\nuserId = "+response.body().getUserId() +
                        "\ntitle = "+response.body().getTitle() +
                        "\nbody = "+response.body().getBody();
                textView.append(jsonResponse);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAGY", "onFailure: "+t.getMessage());
            }
        });
    }
}