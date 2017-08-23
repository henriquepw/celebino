package org.celebino.celebinoapp.conection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class ConnectionServer {

    private static final String URL_BASE = "http://192.168.0.107:8080/Celebino/"; // 192.168.0.105 - wifi

    private static APIService service;
    private static ConnectionServer instance = new ConnectionServer();

    public static ConnectionServer getInstance() {
        return instance;
    }

    public APIService getService() {
        return service;
    }

    private ConnectionServer() {
        updateServiceAdress();
    }

    public void updateServiceAdress() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIService.class);

    }

}
