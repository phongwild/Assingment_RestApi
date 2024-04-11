package phongtaph31865.poly.healthyapp.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import phongtaph31865.poly.healthyapp.Adapter.Menu_home_adapter;
import phongtaph31865.poly.healthyapp.Fragment.Fragment_cart;
import phongtaph31865.poly.healthyapp.Fragment.Fragment_category;
import phongtaph31865.poly.healthyapp.Fragment.Fragment_favorite;
import phongtaph31865.poly.healthyapp.Fragment.Fragment_home;
import phongtaph31865.poly.healthyapp.databinding.ActivityHomeBinding;
import phongtaph31865.poly.healthyapp.R;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    Menu_home_adapter adapter;
    ArrayList<Fragment> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }
    private void initView(){
        list.add(new Fragment_home());
        list.add(new Fragment_cart());
        list.add(new Fragment_category());
        list.add(new Fragment_favorite());
        adapter = new Menu_home_adapter(this, list);
        binding.mainContainer.setAdapter(adapter);
        binding.mainContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomNav.setSelectedItemId(R.id.item_home);
                        break;
                    case 1:
                        binding.bottomNav.setSelectedItemId(R.id.item_cart);
                        break;
                    case 2:
                        binding.bottomNav.setSelectedItemId(R.id.item_category);
                        break;
                    case 3:
                        binding.bottomNav.setSelectedItemId(R.id.item_favorite);
                }
                super.onPageSelected(position);
            }
        });
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_home){
                    binding.mainContainer.setCurrentItem(0);
                }
                if (menuItem.getItemId() == R.id.item_cart){
                    binding.mainContainer.setCurrentItem(1);
                }
                if (menuItem.getItemId() == R.id.item_category){
                    binding.mainContainer.setCurrentItem(2);
                }
                if (menuItem.getItemId() == R.id.item_favorite){
                    binding.mainContainer.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}