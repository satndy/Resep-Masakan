package com.arissandy.satria.newresepmasakan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.arissandy.satria.newresepmasakan.adapter.MasakanAdapter;
import com.arissandy.satria.newresepmasakan.models.Masakan;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public List<Masakan> listMasakan = new ArrayList<>();
    public Masakan itemMasakan;
    public MasakanAdapter mAdapter;
    public RecyclerView mRecycler;
    public ArrayList<Masakan> masak= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listMasakan = Masakan.listAll(Masakan.class);
        mRecycler = (RecyclerView) findViewById(R.id.listMasakan);
        mAdapter = new MasakanAdapter(R.layout.item_makanan,listMasakan);


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







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main2Activity.this, MainActivity.class );
                Main2Activity.this.startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        listMasakan.clear();
        listMasakan = Masakan.listAll(Masakan.class);
        mAdapter.setNewData(listMasakan);
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ayam) {
            Intent intent = new Intent(Main2Activity.this, ayam.class );
            Main2Activity.this.startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class );
            Main2Activity.this.startActivity(intent);


        } else if (id == R.id.nav_daging) {
            Intent intent = new Intent(Main2Activity.this, daging.class );
            Main2Activity.this.startActivity(intent);
        } else if (id == R.id.nav_minuman) {
            Intent intent = new Intent(Main2Activity.this, minuman.class );
            Main2Activity.this.startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("Text/plain");
            String shareBody= "Download Aplikasi";
            String shareSub = "https://play.google.com/store/apps/developer?id=Arissandy+Inc";
            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            startActivity(Intent.createChooser(myIntent,"Share Using"));

        } else if (id == R.id.nav_ikan) {
            Intent intent = new Intent(Main2Activity.this, ikan.class );
            Main2Activity.this.startActivity(intent);
        }else if (id == R.id.nav_mie) {
            Intent intent = new Intent(Main2Activity.this, mie.class );
            Main2Activity.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
