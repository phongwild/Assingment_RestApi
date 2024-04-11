package phongtaph31865.poly.healthyapp.Activity.LoginActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;

import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Users;
import phongtaph31865.poly.healthyapp.databinding.ActivityRegisterBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLoginRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = new Users();
                users.setEmail(binding.edEmailRegis.getText().toString().trim());
                users.setFullName(binding.edFullNameRegis.getText().toString().trim());
                users.setPassword(binding.edPassRegis.getText().toString().trim());
                createUser(users);
                Toast.makeText(Register.this,"Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
    private void createUser(Users Objusers){
        String email = binding.edEmailRegis.getText().toString().trim();
        String pass = binding.edPassRegis.getText().toString().trim();
        String re_pass = binding.edReTypePassRegis.getText().toString().trim();
        String fullName = binding.edFullNameRegis.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            binding.erEmailRegis.setError("Không được để trống email");
        }else if (TextUtils.isEmpty(fullName)){
            binding.erFullNameRegis.setError("Không được để trống full name");
        }else if (TextUtils.isEmpty(pass)){
            binding.erPassRegis.setError("Không được để trống password");
        }else if (TextUtils.isEmpty(re_pass)){
            binding.erRePassRegis.setError("Không được để trống Re-type Password");
        }else if (!pass.equals(re_pass)){
            binding.erRePassRegis.setError("Re-type pass không trùng khớp");
        }else {
            Api_service.api_service.tao_tk(Objusers).enqueue(new Callback<List<Users>>() {
                @Override
                public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(Register.this,"Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Register.this,"Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Users>> call, Throwable throwable) {
                    Log.e(TAG, "Lỗi: " + throwable.getMessage());
                    throwable.printStackTrace();
                }
            });
        }
    }

}