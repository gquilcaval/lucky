package com.example.giancarlosquilca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.giancarlosquilca.data.db.DbUser;
import com.example.giancarlosquilca.databinding.ActivityLoginBinding;
import com.example.giancarlosquilca.domain.models.User;
import com.google.android.material.snackbar.Snackbar;
/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnIngresar.setOnClickListener(view1 -> {
            login(binding.edtUsername.getEditText().getText().toString(), binding.edtPassword.getEditText().getText().toString(), view);
        });

    }

    private void login(String username, String psw, View view) {

        if (!isEmptyUsername() && !isEmptyPassword()) {
            final DbUser dbUser = new DbUser(LoginActivity.this);
            User user = dbUser.login(username, psw);

            if (user != null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("email", user.getEmail());

                startActivity(intent);
                finish();
            }
            else {
                Snackbar.make(view,"vuelve a intentarlo", Snackbar.LENGTH_SHORT ).show();
            }

        }

    }

    private Boolean isEmptyUsername(){
        boolean state = false;
        if (binding.edtUsername.getEditText().getText().toString().isEmpty()) {
            binding.edtUsername.setError("campo vacio");
            state = true;
        }
        return state;
    }

    private boolean isEmptyPassword() {
        boolean state = false;
        if (binding.edtPassword.getEditText().getText().toString().isEmpty()) {
            binding.edtPassword.setError("campo vacio");
            state = true;
        }
        return state;
    }
}