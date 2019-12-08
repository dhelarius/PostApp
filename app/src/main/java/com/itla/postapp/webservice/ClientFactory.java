package com.itla.postapp.webservice;

public class ClientFactory {

    public static Client getWebClientService(WebClient type, String token){
        switch (type){
            case LOGIN_CLIENT: return new LoginClient();
            case LOGUP_CLIENT: return new LogupClient(token);
            case POST_CLIENT: return new PostClient(token);
            default: return null;
        }
    }
}
