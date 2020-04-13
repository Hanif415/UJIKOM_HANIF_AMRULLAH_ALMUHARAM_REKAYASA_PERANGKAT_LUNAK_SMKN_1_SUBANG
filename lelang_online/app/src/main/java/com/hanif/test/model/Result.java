package com.hanif.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("value")
    String value;

    @SerializedName("message")
    String message;

    @SerializedName("id_level")
    String idLevel;

    @SerializedName("id_user")
    String idUser;

    @SerializedName("results")
    List<Auction> resultAuction;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public List<Auction> getResultAuction() {
        return resultAuction;
    }

    public void setResultAuction(List<Auction> resultAuction) {
        this.resultAuction = resultAuction;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
