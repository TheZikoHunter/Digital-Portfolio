package com.zakariadouih.portfolioapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    ArrayList<String> titresRemarques, contenusRemarques;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        recyclerView=findViewById(R.id.listeRemarques);
        setContentView(R.layout.activity_home);

        TextView fullNameView=findViewById(R.id.fullName);
        TextView emailView=findViewById(R.id.email);
        TextView cinAge=findViewById(R.id.cinAge);
        TextView abrEcole=findViewById(R.id.abrEcole);

        Intent intent=getIntent();



        fullNameView.setText(getString(R.string.fullName, intent.getStringExtra("nom"), intent.getStringExtra("prenom")));
        emailView.setText(getString(R.string.emailView, intent.getStringExtra("email")));
        cinAge.setText(getString(R.string.cinAge, intent.getStringExtra("cin"), intent.getStringExtra("age")));
        abrEcole.setText(getString(R.string.abrEcole, intent.getStringExtra("ecole")));

        titresRemarques=new ArrayList<>();
        contenusRemarques=new ArrayList<>();

        storeRemarques();
    }

    public void storeRemarques(){
        DataBaseHelper dbh=new DataBaseHelper(this);
        Intent intent=getIntent();
        Cursor cursor=dbh.getAllRemarquesByUser(intent.getStringExtra("cin"));
        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                titresRemarques.add(cursor.getString(2));
                contenusRemarques.add(cursor.getString(3));
            }
        }
    }

    public void addRemarque(View view){
        DataBaseHelper dbh=new DataBaseHelper(this);
        Intent intent=getIntent();
        EditText titreRemarque=findViewById(R.id.nouvelleRemarque);
        EditText contenuRemarque=findViewById(R.id.contenuRemarque);
        dbh=new DataBaseHelper(this);
        dbh.addRemarque(intent.getStringExtra("cin"), titreRemarque.getText().toString(), contenuRemarque.getText().toString());
    }
}