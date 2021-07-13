package com.alay.notaservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahActivity extends AppCompatActivity {

    private EditText editName, editNomer_Hp, editJenis_Barang, editKerusakan, editNama_Teknisi;
    private Button btnSave;
    private AppDatabase database;
    private int nid = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        editName = findViewById(R.id.name);
        editNomer_Hp = findViewById(R.id.no_hp);
        editJenis_Barang = findViewById(R.id.jenis_barang);
        editKerusakan = findViewById(R.id.kerusakan);
        editNama_Teknisi = findViewById(R.id.teknisi);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        nid = intent.getIntExtra("nid", 0);
        if (nid>0){
            isEdit = true;
            Nota nota = database.notaDao().get(nid);
            editName.setText(nota.name);
            editNomer_Hp.setText(nota.hp);
            editJenis_Barang.setText(nota.jb);
            editKerusakan.setText(nota.kerusakan);
            editNama_Teknisi.setText(nota.teknisi);
        }else {
            isEdit = false;
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    database.notaDao().update(nid, editName.getText().toString(), editNomer_Hp.getText().toString(), editJenis_Barang.getText().toString(), editKerusakan.getText().toString(), editNama_Teknisi.getText().toString());
                }else {
                    database.notaDao().insertAll(editName.getText().toString(), editNomer_Hp.getText().toString(), editJenis_Barang.getText().toString(), editKerusakan.getText().toString(), editNama_Teknisi.getText().toString());
                }
                startActivity(new Intent(TambahActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}