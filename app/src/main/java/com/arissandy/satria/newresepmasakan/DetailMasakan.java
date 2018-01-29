package com.arissandy.satria.newresepmasakan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arissandy.satria.newresepmasakan.models.Masakan;

public class DetailMasakan extends AppCompatActivity {

    TextView txtNama,txtKategori,txtResep,txtCara;
    ImageView foto;
    Button btnDaftarPendidikan,btnDaftarKeluarga;
    ImageButton favorit;
    Long idProfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masakan);
        Intent i = getIntent();

        idProfil = i.getLongExtra("id",0L);
        txtNama = (TextView) findViewById(R.id.txtNama);
        txtNama.setText(i.getStringExtra("nama"));
        txtKategori = (TextView) findViewById(R.id.txtNim);
        txtKategori.setText(i.getStringExtra("kategori"));
        txtResep = (TextView) findViewById(R.id.txtNomorTelepon);
        txtResep.setText(i.getStringExtra("resep"));
        txtCara = (TextView) findViewById(R.id.txtHobby);
        txtCara.setText(i.getStringExtra("cara"));
        foto=(ImageView)findViewById(R.id.imgFoto);
        //foto.setImageBitmap(BitmapFactory.decodeFile(i.getStringExtra("foto")));
        int a = i.getIntExtra("foto", 0);
        foto.setImageResource(a);
        favorit=(ImageButton)findViewById(R.id.favorit);
        favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Masakan itemProfil = Masakan.findById(Masakan.class,idProfil);
                itemProfil.setStatus(1);
                itemProfil.save();
                Toast.makeText(DetailMasakan.this, "Ditambahkan di Favorit", Toast.LENGTH_LONG).show();
            }
        });

        btnDaftarPendidikan = (Button) findViewById(R.id.btnDaftarPendidikan);
        btnDaftarKeluarga = (Button) findViewById(R.id.btnDaftarKeluarga);

    }
}
