package com.arissandy.satria.newresepmasakan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class TambahMasakan extends AppCompatActivity {

    String Foto;
    EditText txtNama,txtKategori,txtResep,txtCara;
    Button btnSaveProfil;
    Button upload;
    ImageView ImgUpload;
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 999;
    private String []items={"Camera","Gallery"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_masakan);
        txtNama = (EditText) findViewById(R.id.txtNama);
        txtKategori = (EditText) findViewById(R.id.txtNim);
        txtResep = (EditText) findViewById(R.id.txtHobby);
        txtCara = (EditText) findViewById(R.id.txtNomorTelepon);
        upload=(Button) findViewById(R.id.upload);
        ImgUpload=(ImageView)findViewById(R.id.imgUpload);
        btnSaveProfil = (Button) findViewById(R.id.btnSaveProfil);

        btnSaveProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Masakan itemProfil = new Masakan(txtNama.getText().toString(),txtKategori.getText().toString(),txtResep.getText().toString(),txtCara.getText().toString(),Foto);
             //   itemProfil.save();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openG();
            }
        });
        


    }



    protected void openG() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera")){
                    EasyImage.openCamera(TambahMasakan.this, REQUEST_CODE_CAMERA);
                }else if(items[i].equals("Gallery")){
                    EasyImage.openGallery(TambahMasakan.this, REQUEST_CODE_GALLERY);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        Glide.with(TambahMasakan.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ImgUpload);
                        Foto = imageFile.getAbsolutePath();
                        break;
                    case REQUEST_CODE_GALLERY:
                        Glide.with(TambahMasakan.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ImgUpload);
                        Foto = imageFile.getAbsolutePath();
                        break;
                }
            }
        });
    }
}
