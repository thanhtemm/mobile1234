package com.example.petest.apiService;

import com.example.petest.model.Product;
import com.example.petest.model.Nsx;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    String PRODUCT = "products";
    String NSX = "Nhsx";
    @GET(PRODUCT)
    Call<List<Product>> getProduct();

    @POST(PRODUCT)
    Call<Product> addProduct(@Body Product product);

    @GET(NSX)
    Call<List<Nsx>> getPhongban();

    @POST(NSX)
    Call<Nsx> addPhongban(@Body Nsx nsx);

    @PUT(PRODUCT + "/{id}")
    Call<Product> updateProduct(@Path("id") String id, @Body Product product);

    @DELETE(PRODUCT + "/{id}")
    Call<Product> deleteProduct(@Path("id") Object id);

}
