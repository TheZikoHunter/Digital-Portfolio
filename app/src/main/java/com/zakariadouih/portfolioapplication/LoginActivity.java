package com.zakariadouih.portfolioapplication;

import static com.zakariadouih.portfolioapplication.DataBaseHelper.*;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText CINLogin, passLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
    public void verifyUser(View v){
        CINLogin=findViewById(R.id.loginCIN);
        passLogin=findViewById(R.id.loginPass);

        String CINInput=CINLogin.getText().toString();
        String passInput=passLogin.getText().toString();

        DataBaseHelper dbh=new DataBaseHelper(this);
        Cursor res=dbh.getByCIN(CINInput);
        String motDePasse="";
        if(res.getCount()==0){
            Toast.makeText(this, "Utilisateur non trouvé", Toast.LENGTH_LONG).show();
        }else{
            if(res!=null && res.moveToFirst()) {
                motDePasse = res.getString(5);
            }
            if(motDePasse.equals(passInput)){
                Intent intent=new Intent(this, HomeActivity.class);
                intent.putExtra("nom", res.getString(2));
                intent.putExtra("prenom", res.getString(3));
                intent.putExtra("age", res.getString(4));
                intent.putExtra("cin", res.getString(0));
                intent.putExtra("email", res.getString(6));
                Cursor resEcole=dbh.getAbrByName(res.getString(1));
                if(resEcole!=null && resEcole.moveToFirst()){
                    intent.putExtra("ecole", resEcole.getString(0));
                }

                startActivity(intent);
            }else{
                Toast.makeText(this, "Input is : "+passInput+" & Real is : "+motDePasse, Toast.LENGTH_SHORT).show();
            }
        }
    }
}