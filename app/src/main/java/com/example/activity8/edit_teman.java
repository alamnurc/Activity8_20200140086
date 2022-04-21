package com.example.activity8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity8.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class edit_teman extends AppCompatActivity {
    TextInputEditText Nama, Telepon;
    Button Simpan;
    String id, nm, tlp;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        Nama = findViewById(R.id.edNama);
        Telepon = findViewById(R.id.edTelp);
        Simpan = findViewById(R.id.simpanBtn);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telepon");

        setTitle("Edit Data");
        Nama.setText(nm);
        Telepon.setText(tlp);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Nama.getText().toString().equals("") || Telepon.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Mohon isi data terlebih dahulu!",Toast.LENGTH_LONG).show();
                }else{
                    nm = Nama.getText().toString();
                    tlp = Telepon.getText().toString();
                    HashMap<String,String> values = new HashMap<>();
                    values.put("id",id);
                    values.put("nama",nm);
                    values.put("telepon",tlp);
                    controller.UpdateData(values);
                    callHome();
                }
            }
        });
    }
    private void callHome() {
        Intent i = new Intent(edit_teman.this, com.example.activity8.MainActivity.class);
        startActivity(i);
        finish();
    }
}