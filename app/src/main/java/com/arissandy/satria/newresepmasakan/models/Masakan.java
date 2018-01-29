package com.arissandy.satria.newresepmasakan.models;

import com.orm.SugarRecord;

/**
 * Created by MANGGAR-LAPTOP on 11/16/2017.
 */

public class Masakan extends SugarRecord<Masakan> {

    private String nama,kategori,resep,cara,foto;
    private Integer status;



    public Masakan(String nama, String kategori, String resep, String cara, String foto, Integer status) {
        this.nama = nama;
        this.kategori = kategori;
        this.resep = resep;
        this.cara = cara;
        this.foto=foto;
        this.status=status;
    }

    public Masakan() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getResep() {
        return resep;
    }

    public void setResep(String resep) {
        this.resep = resep;
    }

    public String getCara() {
        return cara;
    }

    public void setCara(String cara) {
        this.cara = cara;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



}
