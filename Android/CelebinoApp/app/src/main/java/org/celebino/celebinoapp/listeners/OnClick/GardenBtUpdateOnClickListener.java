package org.celebino.celebinoapp.listeners.OnClick;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 16/06/2017.
 */

public class GardenBtUpdateOnClickListener implements OnClickListener {
    private Garden garden;
    private GardenUpdateDialogOnClickListener main;

    public GardenBtUpdateOnClickListener(Garden garden, GardenUpdateDialogOnClickListener main) {
        this.garden = garden;
        this.main = main;
    }

    @Override
    public void onClick(View v) {
        String locality = main.getEtLocality().getText().toString();
        String upcase = "" + locality.charAt(0);
        upcase = upcase.toUpperCase();
        for (int i = 1; i < locality.length(); i++)
            upcase += locality.charAt(i);

        garden.setLocality(upcase);
        updateGarden();
    }

    public void getAllGarden() {
        Call<List<Garden>> call = ConnectionServer.getInstance().getService().getAllGarden();

        call.enqueue(new Callback<List<Garden>>() {
            @Override
            public void onResponse(Call<List<Garden>> call, Response<List<Garden>> response) {
                main.getMainActivity().setGardens(response.body());
                Log.i("SplashActivity", main.getMainActivity().getGardens().toString());

                getAllGardenStatus();
                Requisition.getRequisition().toaskErro(response.code(), "Erro ao atualizar - ", main.getMainActivity());
            }

            @Override
            public void onFailure(Call<List<Garden>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
            }
        });
    }

    public void getAllGardenStatus() {
        Call<List<GardenStatus>> call = ConnectionServer.getInstance().getService().getAllGardenStatus();

        call.enqueue(new Callback<List<GardenStatus>>() {
            @Override
            public void onResponse(Call<List<GardenStatus>> call, Response<List<GardenStatus>> response) {
                main.getMainActivity().setGardenStatuses(response.body());
                Log.i("SplashActivity", main.getMainActivity().getGardenStatuses().toString());

                main.dismissDialogg();
                main.getMainActivity().gardenfragmet();
                Requisition.getRequisition().toaskErro(response.code(), "Erro ao atualizar - ", main.getMainActivity());
            }

            @Override
            public void onFailure(Call<List<GardenStatus>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
            }
        });
    }

    public void updateGarden() {
        Call<Garden> call = ConnectionServer.getInstance().getService().updateGarden(garden);

        call.enqueue(new Callback<Garden>() {
            @Override
            public void onResponse(Call<Garden> call, Response<Garden> response) {
                getAllGarden();
                Requisition.getRequisition().toask(response.code(), "Salvo com sucesso!", main.getMainActivity());
            }

            @Override
            public void onFailure(Call<Garden> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(main.getMainActivity());
            }
        });
    }
}
