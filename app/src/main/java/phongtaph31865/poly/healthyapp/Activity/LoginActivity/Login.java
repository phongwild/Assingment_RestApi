package phongtaph31865.poly.healthyapp.Activity.LoginActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import phongtaph31865.poly.healthyapp.Activity.Home;
import phongtaph31865.poly.healthyapp.Interface.Api_service;
import phongtaph31865.poly.healthyapp.Model.Users;
import phongtaph31865.poly.healthyapp.databinding.ActivityLoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private List<Users> listUser;
    private Users mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCreateLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        listUser = new ArrayList<>();
        getUsers();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users  users = new Users();
                users.setEmail(binding.edEmailLogin.getText().toString().trim());
                users.setPassword(binding.edPassLogin.getText().toString().trim());
                handleLogin();
            }
        });
    }
    private void getUsers(){
        Api_service.api_service.get_ds_user()
                .enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if (response.body() != null){
                            listUser = response.body();
                            Log.e("user", "Database connected");
                            Log.e("user", listUser.size() + "");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable throwable) {
                        Toast.makeText(Login.this, "Get api false", Toast.LENGTH_SHORT).show();
                    }
                });
    }
   private void handleLogin(){
        String email = binding.edEmailLogin.getText().toString().trim();
        String pass = binding.edPassLogin.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            binding.erEmailLogin.setError("Không được để trống email");
        }else if (TextUtils.isEmpty(pass)){
            binding.erPassLogin.setError("Không được để trống password");
        }else {
            //Api_service.api_service.login(users).enqueue(new Callback<List<Users>>() {
            //    @Override
            //    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
            //        if (response.isSuccessful()) {
            //            Intent  intent = new Intent(Login.this, Home.class);
            //            intent.putExtra("email", mUser.getEmail());
            //            intent.putExtra("name", mUser.getFullName());
            //            intent.putExtra("pass", mUser.getPassword());
            //            intent.putExtra("token", mUser.getToken());
            //            Toast.makeText(Login.this, "Đăng nhập thành công",  Toast.LENGTH_SHORT).show();
            //        }else {
            //            Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu",  Toast.LENGTH_SHORT).show();
            //       }
            //    }
            //    @Override
            //    public void onFailure(Call<List<Users>> call, Throwable throwable) {
            //        Log.e(TAG, "Lỗi: " + throwable.getMessage());
            //        throwable.printStackTrace();
             //   }
            //});
            boolean isHasUser = false;
            for (Users users : listUser) {
                if (email.equals(users.getEmail())) {
                    isHasUser = true;
                    mUser = users;
                }
                if (isHasUser) {
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("idUser", mUser.get_id());
                    intent.putExtra("email", mUser.getEmail());
                    intent.putExtra("name", mUser.getFullName());
                    intent.putExtra("pass", mUser.getPassword());
                    intent.putExtra("token", mUser.getToken());
                    //Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    //Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}