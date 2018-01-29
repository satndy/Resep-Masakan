package com.arissandy.satria.newresepmasakan.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.arissandy.satria.newresepmasakan.R;
import com.arissandy.satria.newresepmasakan.models.Masakan;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by MANGGAR-LAPTOP on 11/16/2017.
 */

public class MasakanAdapter extends BaseQuickAdapter<Masakan,BaseViewHolder> {


    public MasakanAdapter(@LayoutRes int layoutResId, @Nullable List<Masakan> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Masakan item) {
        helper.setText(R.id.nama,item.getNama());
        helper.setText(R.id.kategori,item.getKategori());
//        helper.setImageBitmap(R.id.TampilFoto, BitmapFactory.decodeFile(item.getFoto()));
        int d = Integer.parseInt(item.getFoto());
        helper.setImageResource(R.id.TampilFoto, d);
        helper.addOnClickListener(R.id.btnDelete);
        helper.addOnClickListener(R.id.btnEdit);
        helper.addOnClickListener(R.id.nama);
        helper.addOnClickListener(R.id.TampilFoto);
    }
}
