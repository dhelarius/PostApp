package com.itla.postapp.webservice;

public class ClientFactory {

    public static Client getWebClientService(WebClient type){
        switch (type){
            case LOGIN_CLIENT: return new LoginClient();
            default: return null;
        }
    }
}
