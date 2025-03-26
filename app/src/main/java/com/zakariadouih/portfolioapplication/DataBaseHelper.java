package com.zakariadouih.portfolioapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="portfolio.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ETUDIANT="Etudiant";
    private static final String TABLE_ECOLE="Ecole";
    private static final String TABLE_REMARQUE="Remarque";

    private static final String NOM_ETUDIANT="nomEtudiant";
    private static final String PRENOM_ETUDIANT="prenomEtudiant";
    private static final String AGE_ETUDIANT="age";
    private static final String CIN_ETUDIANT="cin";
    private static final String NOM_ECOLE_ETUDIANT="nomEcoleEtudiant";
    private static final String MOT_DE_PASSE="motDePasse";
    private static final String EMAIL="email";
    private static final String NOM_ECOLE="nomEcole";
    private static final String ABR_ECOLE="abrEcloe";
    private static final String CIN_ETUDIANT_REM="cinEtudiant";
    private static final String TITRE_REMARQUE="titreRemarque";
    private static final String CONTENU_REMARQUE="contenuRemarque";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String queryEcole = "CREATE TABLE "+TABLE_ECOLE+" (\n" +
                    "    "+NOM_ECOLE+" TEXT PRIMARY KEY,\n" +
                    "    "+ABR_ECOLE+" TEXT\n" +
                    ");";

            String queryEtudiant=
                    "CREATE TABLE "+TABLE_ETUDIANT+" (\n" +
                            "    "+CIN_ETUDIANT+" TEXT PRIMARY KEY,\n" +
                            "    "+NOM_ECOLE_ETUDIANT+" TEXT,\n" +
                            "    "+NOM_ETUDIANT+" TEXT,\n" +
                            "    "+PRENOM_ETUDIANT+" TEXT,\n" +
                            "    "+AGE_ETUDIANT+" ,\n" +
                            "    "+MOT_DE_PASSE+" TEXT,\n" +
                            "    "+EMAIL+" TEXT,\n" +
                            "    FOREIGN KEY ("+NOM_ECOLE_ETUDIANT+") REFERENCES "+TABLE_ECOLE+"("+NOM_ECOLE+")\n" +
                            ");";
            String queryRemarque=
                    "CREATE TABLE "+TABLE_REMARQUE+" (\n" +
                            "    idRemarque INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                            "    "+CIN_ETUDIANT_REM+" TEXT,\n" +
                            "    "+TITRE_REMARQUE+" TEXT,\n" +
                            "    "+CONTENU_REMARQUE+" TEXT,\n" +
                            "    FOREIGN KEY ("+CIN_ETUDIANT_REM+") REFERENCES "+TABLE_ETUDIANT+" ("+CIN_ETUDIANT+")\n" +
                            ");";
            String queryMatiere=
                    "CREATE TABLE Matiere (\n" +
                            "    nomMatiere TEXT PRIMARY KEY,\n" +
                            "    cinEtudiant TEXT,\n" +
                            "    noteMatiere REAL,\n" +
                            "    avisMatiere TEXT,\n" +
                            "    PRIMARY KEY (nomMatiere, cinEtudiant),\n" +
                            "    FOREIGN KEY (cinEtudiant) REFERENCES Etudiant(cin)\n" +
                            ");";

            db.execSQL(queryEcole);
            db.execSQL(queryEtudiant);
            db.execSQL(queryRemarque);
        }catch(Exception e){
            Log.e("DATABASE", e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ETUDIANT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ECOLE);
        onCreate(db);
    }

    public void addUser(String nom, String prenom, int age, String cin, String ecole, String ecoleEtudiant, String abr, String email, String pass){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv_etudiant=new ContentValues();
        ContentValues cv_ecole=new ContentValues();

        cv_etudiant.put(NOM_ECOLE_ETUDIANT, ecoleEtudiant);
        cv_etudiant.put(CIN_ETUDIANT, cin);
        cv_etudiant.put(NOM_ETUDIANT, nom);
        cv_etudiant.put(PRENOM_ETUDIANT, prenom);
        cv_etudiant.put(EMAIL, email);
        cv_etudiant.put(MOT_DE_PASSE, pass);
        cv_etudiant.put(AGE_ETUDIANT, age);

        cv_ecole.put(NOM_ECOLE, ecole);
        cv_ecole.put(ABR_ECOLE, abr);

        long resultEtudiant=db.insert(TABLE_ETUDIANT, null, cv_etudiant);
        long resultEcole=db.insert(TABLE_ECOLE, null, cv_ecole);
        if(resultEtudiant==-1 || resultEcole==-1){
            Toast.makeText(context, "Problème avec la base de donneés", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(context, "Etudiant ajouté !", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_ETUDIANT,null);
    }
    public Cursor getByCIN(String cin){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_ETUDIANT+" WHERE "+CIN_ETUDIANT+"='"+cin+"'", null);
    }

    public Cursor getAbrByName(String nomEcole){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT "+ABR_ECOLE+" FROM "+TABLE_ECOLE+" WHERE "+NOM_ECOLE+"=?", new String[]{nomEcole});
    }

    public Cursor getAllRemarquesByUser(String cin){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_REMARQUE+" WHERE "+CIN_ETUDIANT_REM+"=?",new String[]{cin});
    }

    public void addRemarque(String cin, String titre, String contenu){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv_remarque=new ContentValues();
        cv_remarque.put(CIN_ETUDIANT_REM, cin);
        cv_remarque.put(TITRE_REMARQUE, titre);
        cv_remarque.put(CONTENU_REMARQUE, contenu);

        long result=db.insert(TABLE_REMARQUE, null, cv_remarque);
        if(result==-1){
            Toast.makeText(context, "Problème avec la base de donneés", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "Remarque ajoutée !", Toast.LENGTH_SHORT).show();
        }
    }
}
