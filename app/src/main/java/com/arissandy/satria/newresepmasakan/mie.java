package com.arissandy.satria.newresepmasakan;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.arissandy.satria.newresepmasakan.adapter.MasakanAdapter;
import com.arissandy.satria.newresepmasakan.models.Masakan;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class mie extends AppCompatActivity {
    public Button btnTambah;

    public List<Masakan> listMasakan = new ArrayList<>();
    public Masakan itemMasakan;
    public MasakanAdapter mAdapter;
    public RecyclerView mRecycler;
    public ArrayList<Masakan> masak= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mie);
        listMasakan = Masakan.listAll(Masakan.class);
        mRecycler = (RecyclerView) findViewById(R.id.listMasakan);
        mAdapter = new MasakanAdapter(R.layout.item_makanan,listMasakan);
        setTitle( "Aneka Resep Mie");
        btnTambah = (Button) findViewById(R.id.btnTambah);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();




        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Masakan itemMasakan = listMasakan.get(position);
                switch (view.getId()){
                    case R.id.btnEdit:

                        break;
                    case R.id.btnDelete:

                        break;
                    case R.id.nama:
                        Intent j = new Intent(getApplicationContext(),DetailMasakan.class);
                        j.putExtra("id",itemMasakan.getId());
                        j.putExtra("nama",itemMasakan.getNama());
                        j.putExtra("kategori",itemMasakan.getKategori());
                        j.putExtra("resep",itemMasakan.getResep());
                        j.putExtra("cara",itemMasakan.getCara());
                        j.putExtra("foto",Integer.parseInt(itemMasakan.getFoto()));
                        startActivity(j);
                        break;

                }
            }
        });





    }

    @Override
    protected void onResume() {
        listMasakan.clear();

        listMasakan = Masakan.find(Masakan.class,"kategori= ?", "mie");

        mAdapter.setNewData(listMasakan);
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }



}

