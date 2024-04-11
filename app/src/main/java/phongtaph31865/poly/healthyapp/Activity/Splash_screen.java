package phongtaph31865.poly.healthyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import phongtaph31865.poly.healthyapp.Activity.LoginActivity.Login;
import phongtaph31865.poly.healthyapp.R;

public class Splash_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash_screen.this, Login.class));
                finish();
            }
        }, 3000);
    }
}