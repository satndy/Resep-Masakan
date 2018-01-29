package com.arissandy.satria.newresepmasakan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arissandy.satria.newresepmasakan.models.Masakan;

public class EditMasakan extends AppCompatActivity {

    EditText txtNama,txtNim,txtHobby,txtNomorTelepon;
    Button btnSaveProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_masakan);
        Intent i = getIntent();

        final Long idMasakan = i.getLongExtra("id",0L);
        txtNama = (EditText) findViewById(R.id.txtNama);
        txtNama.setText(i.getStringExtra("nama"));
        txtNim = (EditText) findViewById(R.id.txtNim);
        txtNim.setText(i.getStringExtra("kategori"));
        txtHobby = (EditText) findViewById(R.id.txtHobby);
        txtHobby.setText(i.getStringExtra("resep"));
        txtNomorTelepon = (EditText) findViewById(R.id.txtNomorTelepon);
        txtNomorTelepon.setText(i.getStringExtra("cara"));


        btnSaveProfil = (Button) findViewById(R.id.btnSaveProfil);

        btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Masakan itemProfil = Masakan.findById(Masakan.class,idMasakan);
                itemProfil.setNama(txtNama.getText().toString());
                itemProfil.setKategori(txtNim.getText().toString());
                itemProfil.setResep(txtHobby.getText().toString());
                itemProfil.setCara(txtNomorTelepon.getText().toString());
                itemProfil.save();
            }
        });
    }
}
