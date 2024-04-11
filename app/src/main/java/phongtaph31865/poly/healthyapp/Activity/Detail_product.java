package phongtaph31865.poly.healthyapp.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Cart;
import phongtaph31865.poly.healthyapp.Model.Product;
import phongtaph31865.poly.healthyapp.R;
import phongtaph31865.poly.healthyapp.databinding.ActivityDetailProductBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_product extends AppCompatActivity {
    private ActivityDetailProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int quantity = 1;
        Bundle intent = getIntent().getExtras();
        binding.tvNameDetail.setText(intent.getString("name"));
        binding.tvDesDetail.setText(intent.getString("description"));
        Picasso.get().load(intent.getString("img_product"))
                .placeholder(R.drawable.gif_loading_spinner)
                .into(binding.imgDetail);
        binding.btnMinusDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1){
                    int minus = quantity;
                    minus--;
                    binding.tvQuantityDetail.setText(String.valueOf(minus));
                }
            }
        });
        binding.btnPlusDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plus = quantity;
                plus++;
                binding.tvQuantityDetail.setText(String.valueOf(plus));
            }
        });
        binding.btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail_product.this, Home.class));
            }
        });
        binding.btnAddDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img = intent.getString("img_product");
                int price = intent.getInt("price");
                Log.e("imgClick", img);
                Log.e("imgClick", String.valueOf(price));
                String prId = intent.getString("id");
                Cart cart = new Cart();
                cart.setPrId(prId);
                cart.setImg(img);
                cart.setPrice(price);
                cart.setQuantity(quantity);
                addToCart(cart);
            }
        });
    }
    public void addToCart(Cart objCart){
        Api_service.api_service.add_to_cart(objCart).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Detail_product.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(Detail_product.this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                Log.e(TAG, "Lỗi: " + throwable.getMessage());
                Toast.makeText(Detail_product.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });
    }
}