package phongtaph31865.poly.healthyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import phongtaph31865.poly.healthyapp.Activity.LoginActivity.Login;
import phongtaph31865.poly.healthyapp.databinding.ActivityUserDetailBinding;

import phongtaph31865.poly.healthyapp.R;

public class User_detail extends AppCompatActivity {
    private ActivityUserDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_detail.this, Home.class));
            }
        });
        Intent intent = getIntent();
        Log.d("user_d", intent.getStringExtra("email"));
        Log.d("user_d", intent.getStringExtra("name"));
        Log.d("user_d", intent.getStringExtra("pass"));
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_detail.this, Login.class));
            }
        });
        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_detail.this, History_screen.class));
            }
        });
    }
}