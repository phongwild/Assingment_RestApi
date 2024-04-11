package phongtaph31865.poly.healthyapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import phongtaph31865.poly.healthyapp.Activity.User_detail;
import phongtaph31865.poly.healthyapp.Adapter.Adapter_Category_home;
import phongtaph31865.poly.healthyapp.Adapter.Adapter_Product;
import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Category;
import phongtaph31865.poly.healthyapp.Model.Product;
import phongtaph31865.poly.healthyapp.R;
import phongtaph31865.poly.healthyapp.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_home extends Fragment {
    RecyclerView rcv_product1, rcv_category;
    Adapter_Product adapter_product;
    Adapter_Category_home adapter_category_home;
    List<Product> product1;
    List<Category> categories;
    ViewFlipper viewFlipper;
    ImageView img1, img2, img3;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        Bundle intent = getActivity().getIntent().getExtras();
        String email = intent.getString("email");
        String name = intent.getString("name");
        String pass = intent.getString("pass");
        String idUser = intent.getString("idUser");
        Log.e("id", idUser);
        binding.btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), User_detail.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });
        rcv_product1 = v.findViewById(R.id.lv_product1);
        rcv_category = v.findViewById(R.id.rcv_categoryHome);
        //slide banner
        viewFlipper = v.findViewById(R.id.slider);
        //
        img1 = new ImageView(getActivity());
        img1.setImageResource(R.drawable.banner1);
        viewFlipper.addView(img1);
        //
        img2 = new ImageView(getActivity());
        img2.setImageResource(R.drawable.banner2);
        viewFlipper.addView(img2);
        //
        img3 = new ImageView(getActivity());
        img3.setImageResource(R.drawable.banner3);
        viewFlipper.addView(img3);
        viewFlipper.startFlipping();
        //ds category
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcv_category.setLayoutManager(manager2);
        categories = new ArrayList<>();
        getDsCategory();
        //ds product
        GridLayoutManager grid2 = new GridLayoutManager(getActivity(), 2);
        rcv_product1.setLayoutManager(grid2);
        product1 = new ArrayList<Product>();
        getDanhSach();
        return v;
    }

    public void getDanhSach() {
        Api_service.api_service.get_ds_Product().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        product1.clear();
                        product1 = response.body();
                        adapter_product = new Adapter_Product(product1);
                        rcv_product1.setAdapter(adapter_product);
                        adapter_product.notifyDataSetChanged();
                        Log.d("rcv1", "Succ: Lấy dữ liệu thành công");
                    }
                } else {
                    Log.d("rcv1", "False: Không lấy được dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                Log.d("rcv1", "False: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    public void getDsCategory() {
        Api_service.api_service
                .get_ds_Category()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                categories.clear();
                                categories = response.body();
                                adapter_category_home = new Adapter_Category_home(categories);
                                rcv_category.setAdapter(adapter_category_home);
                                adapter_category_home.notifyDataSetChanged();
                                Log.d("rcv2", "Succ: Lấy dữ liệu thành công");
                            }
                        } else {
                            Log.d("rcv2", "False: Không lấy được dữ liệu");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable throwable) {
                        Log.d("rcv2", "False: " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });
    }
}