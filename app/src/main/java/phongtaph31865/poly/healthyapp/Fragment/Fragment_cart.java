package phongtaph31865.poly.healthyapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import phongtaph31865.poly.healthyapp.Adapter.Adapter_cart;
import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Cart;
import phongtaph31865.poly.healthyapp.Model.History;
import phongtaph31865.poly.healthyapp.R;
import phongtaph31865.poly.healthyapp.databinding.FragmentCartBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_cart extends Fragment {
    Adapter_cart adapterCart;
    List<Cart> carts;
    RecyclerView rcv;
    Cart mCart;
    private FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        rcv = v.findViewById(R.id.rcv_cart);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(manager);
        carts = new ArrayList<Cart>();
        getDsCart();
        binding.btnPayCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate today = LocalDate.now();
                int year = today.getYear();
                int month = today.getMonthValue();
                int day = today.getDayOfMonth();
                String date = day + "/" + month + "/" + year;
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String time = hour + ":" + minute + ":" + second;
                History history = new History();
                Cart cart = mCart;
                //history.setCartId(cart.get_id());
                history.setDate(date);
                history.setTime(time);
                order(history);
                Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    public void getDsCart() {
        Api_service.api_service.get_cart().enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        carts.clear();
                        carts = response.body();
                        adapterCart = new Adapter_cart(carts);
                        rcv.setAdapter(adapterCart);
                        adapterCart.notifyDataSetChanged();
                        Log.d("rcvCart", "Succ: Lấy dữ liệu thành công");
                    }else {
                        Log.d("rcvCart", "Data: " + response.body().size());
                        Log.d("rcvCart", "Succ: No data received");
                    }
                } else {
                    Log.d("rcvCart", "False: Không lấy được dữ liệu");
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable throwable) {
                Log.d("rcvCart", "False: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void order(History objHistory){
        Api_service.api_service.add_to_his(objHistory).enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable throwable) {
                Log.d("order", "False: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        getDsCart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}