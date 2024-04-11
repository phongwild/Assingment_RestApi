package phongtaph31865.poly.healthyapp.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import phongtaph31865.poly.healthyapp.Model.Cart;
import phongtaph31865.poly.healthyapp.Model.Product;
import phongtaph31865.poly.healthyapp.R;

public class Adapter_cart extends RecyclerView.Adapter<Adapter_cart.ViewHolder>{
    List<Cart> carts;
    public Adapter_cart(List<Cart> carts){
        this.carts = carts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        if (cart == null){
            return;
        }
        holder.tv_quantity.setText(String.valueOf(cart.getQuantity()));
        holder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = cart.getQuantity();
                if (quantity > 0) {
                    if (quantity == 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                        builder.setTitle("Xoá sản phẩm");
                        builder.setMessage("Bạn có chắc chắn muốn xoá sản phẩm này khỏi giỏ hàng?");
                        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                    holder.tv_quantity.setText(String.valueOf(quantity));
                }
            }
        });
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = cart.getQuantity();
                quantity+=1;
                holder.tv_quantity.setText(String.valueOf(quantity));
            }
        });
        Picasso.get()
                .load(cart.getImg())
                .placeholder(R.drawable.gif_loading_spinner)
                .into(holder.img_cart);
        holder.tv_quantity.setText(String.valueOf(cart.getQuantity()));
        holder.tv_id_cart.setText(String.valueOf(cart.getPrId()));
        holder.tv_price_cart.setText(String.valueOf(cart.getPrice()));
    }

    @Override
    public int getItemCount() {
        if (carts != null){
            return carts.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView img_cart, btn_minus, btn_plus;
        private final TextView tv_id_cart, tv_price_cart;
        private final TextView tv_quantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cart = itemView.findViewById(R.id.img_cart);
            btn_minus = itemView.findViewById(R.id.btn_minus_cart);
            btn_plus = itemView.findViewById(R.id.btn_plus_cart);
            tv_id_cart = itemView.findViewById(R.id.tv_id_cart);
            tv_price_cart = itemView.findViewById(R.id.tv_price_cart);
            tv_quantity = itemView.findViewById(R.id.tv_quantity_cart);
        }
    }
}
