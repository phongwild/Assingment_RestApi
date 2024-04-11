package phongtaph31865.poly.healthyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import phongtaph31865.poly.healthyapp.Model.Category;
import phongtaph31865.poly.healthyapp.R;

public class Adapter_Category_home extends RecyclerView.Adapter<Adapter_Category_home.ViewHolder> {
    private List<Category> list;
    public Adapter_Category_home(List<Category> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = list.get(position);
        if (category == null) {
            return;
        }
        holder.item_tv_cateName.setText(category.getCateName());
        //Glide.with(holder.item_imgCate.getContext())
         //       .load(category.getImg_cate())
         //       .into(holder.item_imgCate);
        Picasso.get()
                .load(category.getImg_cate())
                .placeholder(R.drawable.gif_loading_spinner)
                .into(holder.item_imgCate);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView item_tv_cateName;
            private ImageView item_imgCate;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                item_tv_cateName = itemView.findViewById(R.id.item_category_name);
                item_imgCate = itemView.findViewById(R.id.item_category_img);
            }
    }
}
