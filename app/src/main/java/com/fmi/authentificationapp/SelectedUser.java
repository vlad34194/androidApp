package com.fmi.authentificationapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedUser extends AppCompatActivity {
    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);
        tvUser = findViewById(R.id.selectedUser);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            UserModel userModel = (UserModel) intent.getSerializableExtra("data");
            tvUser.setText(userModel.getUserName());
        }
    }
}