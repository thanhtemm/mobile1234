package com.example.petest.model;

import com.example.petest.apiService.ApiService;
import com.example.petest.apiclient.APIClient;

public class Respository {
    public static ApiService getApiService(){
        return APIClient.getClient().create(ApiService.class);
    }
}
