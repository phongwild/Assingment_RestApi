package phongtaph31865.poly.healthyapp.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import phongtaph31865.poly.healthyapp.Adapter.Adapter_History;
import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.History;
import phongtaph31865.poly.healthyapp.databinding.ActivityHistoryScreenBinding;
import phongtaph31865.poly.healthyapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History_screen extends AppCompatActivity {
    private ActivityHistoryScreenBinding binding;
    private List<History> list;
    private Adapter_History adapter_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);
        binding = ActivityHistoryScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.rcvHistory.setLayoutManager(manager);
        list = new ArrayList<>();
        getDs();
    }
    public void getDs(){
        Api_service.api_service.get_history().enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        list.clear();
                        list = response.body();
                        adapter_history = new Adapter_History(list);
                        binding.rcvHistory.setAdapter(adapter_history);
                        adapter_history.notifyDataSetChanged();
                        Log.e("rcv_his", "Lay du lieu thanh cong");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable throwable) {
                Log.d("rcv_his", "False: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDs();
    }
}