package com.itla.postapp.model.user;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("estado")
    private String estado;

    @SerializedName("login")
    private String login;

    public DefaultResponse(String estado, String login) {
        this.estado = estado;
        this.login = login;
    }

    public String getEstado() {
        return estado;
    }

    public String getLogin() {
        return login;
    }
}
