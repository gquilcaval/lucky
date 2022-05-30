package com.example.giancarlosquilca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.giancarlosquilca.databinding.ActivityMainBinding;
import com.example.giancarlosquilca.domain.models.User;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class MainActivity extends AppCompatActivity {

    public static MaterialToolbar toolbar = null;
    public static DrawerLayout drawerLayout = null;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getUserBundles();
        uiToolbar();
        uiDrawerLayout();

        binding.navview.setNavigationItemSelectedListener(item -> {
            selectItemNavigation(item);
            return true;
        });

        binding.toolbar.setNavigationOnClickListener(view1 -> {
            drawerLayout.open();
        });
    }

    private void uiToolbar() {
        toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.showOverflowMenu();
        setTitle("Punto de venta");
    }

    private void selectItemNavigation(MenuItem item) {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        switch (item.getItemId()) {
            case R.id.nav_ptv:
                navController.navigateUp();
                navController.navigate(R.id.pointOfSaleFragment);
                break;
            case R.id.nav_out:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        setTitle(item.getTitle());
        binding.drawerLayout.close();
    }

    private User getUserBundles() {
        User u = new User();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            u.setName(extras.getString("name"));
            u.setEmail(extras.getString("email"));
        }
        return u;
    }

    private void uiDrawerLayout() {
        drawerLayout = binding.drawerLayout;
        View header = binding.navview.getHeaderView(0);
        TextView tvname = header.findViewById(R.id.tv_name);
        TextView tvemail = header.findViewById(R.id.tv_email);
        tvname.setText(getUserBundles().getName());
        tvemail.setText(getUserBundles().getEmail());
    }
}