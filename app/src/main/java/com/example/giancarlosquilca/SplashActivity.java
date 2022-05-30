package com.example.giancarlosquilca;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.giancarlosquilca.data.OperationResult;
import com.example.giancarlosquilca.data.callback.Callable;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.data.db.DbUser;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.example.giancarlosquilca.domain.models.Product;
import com.example.giancarlosquilca.domain.models.User;

import java.util.List;

/**
 * Created by Giancarlos Quilca on 28/05/2022.
 */
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launch();
    }

    private void launch(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        insertUser();
        inserPointSales();
        inserProduct();
        finish();
    }

    private void insertUser() {

        final DbUser dbUser = new DbUser(SplashActivity.this);
        List<User> listUsers = dbUser.getUsers();
        if (listUsers.size() == 0) {
            User u = new User();
            u.setUsername("gquilcaval");
            u.setName("Giancarlos");
            u.setEmail("gquilca@gmail.com");
            u.setPhoto("");
            u.setPassword("123");

            User u2 = new User();
            u2.setUsername("lucky");
            u2.setName("Lucky SAC");
            u2.setEmail("lucky@gmail.com");
            u2.setPhoto("");
            u2.setPassword("123");

            dbUser.insertUser(u);
            dbUser.insertUser(u2);
        }

    }

    private void inserPointSales() {

        final DbPointSale dbUser = new DbPointSale(SplashActivity.this);
        List<PointSale> lisPoint = dbUser.getAllValidate();
        if (lisPoint.size() == 0) {
            PointSale u = new PointSale();
            u.setName("YOSLY AMALI SEGUILAR");
            u.setCode("409183");
            u.setDirection("Avenida la Molina 190, Ate");
            u.setPhoto("");

            PointSale u2 = new PointSale();
            u2.setName("METRO AMALI SEGUILAR");
            u2.setCode("409184");
            u2.setDirection("Avenida Alfonso Ugarte, Cercado de Lima");
            u2.setPhoto("");

            PointSale u3 = new PointSale();
            u3.setName("TOTTUS ZORRITOS");
            u3.setCode("409183");
            u3.setDirection("Calle Colón, Miraflores");
            u3.setPhoto("");

            dbUser.insertPointSale(u);
            dbUser.insertPointSale(u2);
            dbUser.insertPointSale(u3);
        }

    }

    private void inserProduct() {

        final DbPointSale dbUser = new DbPointSale(SplashActivity.this);
        List<Product> listProd = dbUser.getAllProduct();
        if (listProd.size() == 0) {
            Product u = new Product();
            u.setName("Aceite Primorx12 Bot");
            u.setCost(13);
            u.setPriceRev(15);
            u.setStock(5);
            u.setIdPoint(1);

            Product u2 = new Product();
            u2.setName("Aceite Metrox12 Bo");
            u2.setCost(13);
            u2.setPriceRev(15);
            u2.setStock(5);
            u2.setIdPoint(1);

            Product u3 = new Product();
            u3.setName("Aceite Bellsx12 Bo");
            u3.setCost(13);
            u3.setPriceRev(15);
            u3.setStock(5);
            u3.setIdPoint(1);

            dbUser.insertProduct(u);
            dbUser.insertProduct(u2);
            dbUser.insertProduct(u3);
        }

    }

}
