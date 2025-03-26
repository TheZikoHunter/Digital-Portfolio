package com.zakariadouih.portfolioapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DataBaseHelper dbh=new DataBaseHelper(MainActivity.this);
        try{
            Cursor res=dbh.getAllData();
            if(res.getCount()==0) {
                intent = new Intent(this, RegisterActivity.class);
            }else{
                intent = new Intent(this, LoginActivity.class);
            }
            res.close();
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }

    }
}