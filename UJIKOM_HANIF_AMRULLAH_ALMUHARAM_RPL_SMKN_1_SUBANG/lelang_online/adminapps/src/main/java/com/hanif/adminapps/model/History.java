package com.hanif.adminapps.model;

import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("id_barang")
    String idBarang;
    @SerializedName("id_masyarakat")
    String idMasyarakat;
    @SerializedName("penawaran_harga")
    String penawaranHarga;
    @SerializedName("created_at")
    String date;

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdMasyarakat() {
        return idMasyarakat;
    }

    public void setIdMasyarakat(String idMasyarakat) {
        this.idMasyarakat = idMasyarakat;
    }

    public String getPenawaranHarga() {
        return penawaranHarga;
    }

    public void setPenawaranHarga(String penawaranHarga) {
        this.penawaranHarga = penawaranHarga;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
