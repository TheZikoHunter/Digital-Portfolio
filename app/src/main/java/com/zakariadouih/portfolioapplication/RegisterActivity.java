package com.zakariadouih.portfolioapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText inputNom, inputPrenom, inputAge, inputCIN, inputEcole, inputAbr, inputEmail, inputPass;
    Button inputSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputNom=findViewById(R.id.inputNom);
        inputPrenom=findViewById(R.id.inputPrenom);
        inputAge=findViewById(R.id.inputAge);
        inputCIN=findViewById(R.id.inputCIN);
        inputEcole=findViewById(R.id.inputEcole);
        inputAbr=findViewById(R.id.inputAbr);
        inputEmail=findViewById(R.id.inputEmail);
        inputPass=findViewById(R.id.inputPass);
        inputSign=findViewById(R.id.inputUser);


    }
    public void addUser(View view){
        DataBaseHelper dbh=new DataBaseHelper(RegisterActivity.this);
        dbh.addUser(
                inputNom.getText().toString(),
                inputPrenom.getText().toString(),
                Integer.parseInt(inputAge.getText().toString()),
                inputCIN.getText().toString(),
                inputEcole.getText().toString(),
                inputEcole.getText().toString(),
                inputAbr.getText().toString(),
                inputEmail.getText().toString(),
                inputPass.getText().toString()
        );
        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}