package org.celebino.celebinoapp.listeners.OnClick;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 16/06/2017.
 */

public class AirBtSaveOnClickListener implements OnClickListener {
    private AirConditioning airConditioning;
    private MainActivity main;
    private boolean home;

    public AirBtSaveOnClickListener(MainActivity main) {
        this.airConditioning = new AirConditioning();
        this.main = main;
        this.home = false;
    }

    public AirBtSaveOnClickListener(MainActivity main, boolean home) {
        this.airConditioning = new AirConditioning();
        this.main = main;
        this.home = home;
    }

    @Override
    public void onClick(View v) {
        String locality = main.getEtLocality().getText().toString();
        String upcase = "" + locality.charAt(0);
        upcase = upcase.toUpperCase();
        for (int i = 1; i < locality.length(); i++)
            upcase += locality.charAt(i);

        airConditioning.setLocality(upcase);
        insertAirConditioning();
    }

    public void getAllAirConditioning() {
        Call<List<AirConditioning>> call = ConnectionServer.getInstance().getService().getAllAirConditioning();

        call.enqueue(new Callback<List<AirConditioning>>() {
            @Override
            public void onResponse(Call<List<AirConditioning>> call, Response<List<AirConditioning>> response) {
                main.setAirConditionings(response.body());
                Log.i("AirBtSaveOnClickListen", main.getAirConditionings().toString());

                getAllLitersMinute();
                Requisition.getRequisition().toaskErro(response.code(), "Erro ao atualizar - ", main);
            }

            @Override
            public void onFailure(Call<List<AirConditioning>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getStackTrace());
            }
        });
    }

    public void getAllLitersMinute() {
        Call<List<LitersMinute>> call = ConnectionServer.getInstance().getService().getAllLitersMinute();

        call.enqueue(new Callback<List<LitersMinute>>() {
            @Override
            public void onResponse(Call<List<LitersMinute>> call, Response<List<LitersMinute>> response) {
                main.setLitersMinutes(response.body());
                Log.i("AirBtSaveOnClickListen", main.getLitersMinutes().toString());

                main.dismissDialogg();

                if (home) {
                    main.homefragmet();
                } else {
                    main.airfragmet();
                }

                Requisition.getRequisition().toaskErro(response.code(), "Erro ao atualizar - ", main);
            }

            @Override
            public void onFailure(Call<List<LitersMinute>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(main);
            }
        });
    }

    public void insertAirConditioning() {
        Call<AirConditioning> call = ConnectionServer.getInstance().getService().insertAirConditioning(airConditioning);

        call.enqueue(new Callback<AirConditioning>() {
            @Override
            public void onResponse(Call<AirConditioning> call, Response<AirConditioning> response) {
                Requisition.getRequisition().toask(response.code(), "Salvo com sucesso!", main);
                getAllAirConditioning();
            }

            @Override
            public void onFailure(Call<AirConditioning> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
            }
        });
    }
}
