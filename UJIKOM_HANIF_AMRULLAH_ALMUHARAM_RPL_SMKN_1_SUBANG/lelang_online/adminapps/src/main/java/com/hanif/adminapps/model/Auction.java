package com.hanif.adminapps.model;

import com.google.gson.annotations.SerializedName;

public class Auction {
    @SerializedName("id_lelang")
    String idLelang;
    @SerializedName("id_barang")
    String idBarang;
    @SerializedName("nama_barang")
    String NamaBarang;
    @SerializedName("tgl_lelang")
    String tglLelalng;
    @SerializedName("harga_awal")
    String hargaAwal;
    @SerializedName("harga_akhir")
    String haragaAkhir;
    @SerializedName("id_masyarakat")
    String idMasyarakat;
    @SerializedName("id_petugas")
    String idPetugas;
    @SerializedName("status")
    String status;

    public String getIdLelang() {
        return idLelang;
    }

    public void setIdLelang(String idLelang) {
        this.idLelang = idLelang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return NamaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        NamaBarang = namaBarang;
    }

    public String getTglLelalng() {
        return tglLelalng;
    }

    public void setTglLelalng(String tglLelalng) {
        this.tglLelalng = tglLelalng;
    }

    public String getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(String hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public String getHaragaAkhir() {
        return haragaAkhir;
    }

    public void setHaragaAkhir(String haragaAkhir) {
        this.haragaAkhir = haragaAkhir;
    }

    public String getIdMasyarakat() {
        return idMasyarakat;
    }

    public void setIdMasyarakat(String idMasyarakat) {
        this.idMasyarakat = idMasyarakat;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
