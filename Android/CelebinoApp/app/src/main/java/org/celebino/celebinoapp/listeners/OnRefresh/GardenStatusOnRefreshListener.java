package org.celebino.celebinoapp.listeners.OnRefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import org.celebino.celebinoapp.activities.GardenStatusActivity;
import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 17/06/2017.
 */

public class GardenStatusOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
    private GardenStatusActivity activity;

    public GardenStatusOnRefreshListener(GardenStatusActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onRefresh() {
        getAllLitersMinuteById();
    }

    public void getAllLitersMinuteById() {
        Call<List<GardenStatus>> call = ConnectionServer.getInstance().getService().findGardenStatusById(activity.getId());

        call.enqueue(new Callback<List<GardenStatus>>() {
            @Override
            public void onResponse(Call<List<GardenStatus>> call, Response<List<GardenStatus>> response) {
                /*if (activity.getLitersMinutes().equals(response.body())) {
                    Toast.makeText(activity, "Nada novo", Toast.LENGTH_LONG);
                } else {*/
                activity.setGardenStatuses(response.body());
                Log.i("LitersMinute", activity.getGardenStatuses().toString());
                //}

                activity.gardenStatusfragmet();
                activity.setRefrash(false);
            }

            @Override
            public void onFailure(Call<List<GardenStatus>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(activity);
                activity.setRefrash(false);
            }
        });
    }
}
