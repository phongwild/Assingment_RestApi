package phongtaph31865.poly.healthyapp.Adapter;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import phongtaph31865.poly.healthyapp.Activity.Detail_product;
import phongtaph31865.poly.healthyapp.Activity.LoginActivity.Register;
import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Cart;
import phongtaph31865.poly.healthyapp.Model.Product;
import phongtaph31865.poly.healthyapp.Model.Users;
import phongtaph31865.poly.healthyapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder>{
    private List<Product> products;

    public Adapter_Product(List<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv_product_home, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        if (product == null){
            return;
        }
        holder.item_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail_product.class);
                intent.putExtra("id", product.get_id());
                intent.putExtra("name", product.getProduct_name());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("price", product.getPrice() + " $");
                intent.putExtra("img_product", product.getImg_product());
                Log.e("item click", product.getImg_product());
                v.getContext().startActivity(intent);
            }
        });
        holder.item_tv_prName.setText(product.getProduct_name());
        holder.item_tv_prDes.setText(product.getDescription());
        holder.item_tv_price.setText(String.valueOf(product.getPrice()));
        Picasso.get()
                .load(product.getImg_product())
                .placeholder(R.drawable.gif_loading_spinner)
                .into(holder.item_img_product);
        holder.item_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart();
                cart.setQuantity(1);
        
                holder.addToCart(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (products != null){
            return products.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_tv_prName, item_tv_prDes, item_tv_price;
        public final ImageView item_img_product;
        private final Button item_btn_add;
        public CardView item_pr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tv_prName = itemView.findViewById(R.id.item_lv_tvName_product);
            item_tv_prDes = itemView.findViewById(R.id.item_lv_tvDes_product);
            item_tv_price = itemView.findViewById(R.id.item_lv_tvPrice_product);
            item_img_product = itemView.findViewById(R.id.item_lv_img_product);
            item_btn_add = itemView.findViewById(R.id.item_lv_btnAdd);
            item_pr = itemView.findViewById(R.id.item_rcv_product);
        }
        public void addToCart(Cart objCart){
            Api_service.api_service.add_to_cart(objCart).enqueue(new Callback<List<Cart>>() {
                @Override
                public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(itemView.getContext(),"Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(itemView.getContext(),"Không thêm được vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                    Log.e(TAG, "Lỗi: " + throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
        }
    }

}
