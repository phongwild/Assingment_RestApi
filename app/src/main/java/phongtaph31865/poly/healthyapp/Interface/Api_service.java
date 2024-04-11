package phongtaph31865.poly.healthyapp.Interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import phongtaph31865.poly.healthyapp.Model.Cart;
import phongtaph31865.poly.healthyapp.Model.Category;
import phongtaph31865.poly.healthyapp.Model.History;
import phongtaph31865.poly.healthyapp.Model.Product;
import phongtaph31865.poly.healthyapp.Model.Users;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_service {
    String BASE_URL = "http://10.0.2.2:3000/api/";
    Gson gson = new GsonBuilder().create();
    Api_service api_service = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api_service.class);

    //Lấy ds sp
    @GET("products")
    Call<List<Product>> get_ds_Product();

    //Lấy ds loại sp
    @GET("category")
    Call<List<Category>> get_ds_Category();

    //lấy ds user
    @GET("users")
    Call<List<Users>> get_ds_user();

    @GET("cart")
    Call<List<Cart>> get_cart();
    @POST("cart/add")
    Call<List<Cart>> add_to_cart(@Body Cart objCart);
    //Login
    @POST("login")
    Call<List<Users>> login(@Body Users users);
    //dk
    @POST("register")
    Call<List<Users>> tao_tk(@Body Users objUser);
    //Lịch sử
    @GET("history")
    Call<List<History>> get_history();
    @POST("history/add")
    Call<List<History>> add_to_his(@Body History objHistory);
}
