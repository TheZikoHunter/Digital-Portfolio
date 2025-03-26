package com.zakariadouih.portfolioapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent=getIntent();

        TextView fullNameView=findViewById(R.id.fullName);
        TextView emailView=findViewById(R.id.email);
        TextView cinAge=findViewById(R.id.cinAge);
        TextView abrEcole=findViewById(R.id.abrEcole);

        fullNameView.setText(getString(R.string.fullName, intent.getStringExtra("nom"), intent.getStringExtra("prenom")));
        emailView.setText(getString(R.string.emailView, intent.getStringExtra("email")));
        cinAge.setText(getString(R.string.cinAge, intent.getStringExtra("cin"), intent.getStringExtra("age")));
        abrEcole.setText(getString(R.string.abrEcole, intent.getStringExtra("ecole")));
    }
}