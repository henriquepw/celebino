package org.celebino.celebinoapp.listeners.OnRefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 17/06/2017.
 */

public class LitersMinuteOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
    private LitersMinuteActivity activity;

    public LitersMinuteOnRefreshListener(LitersMinuteActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onRefresh() {
        getAllLitersMinuteById();
    }

    public void getAllLitersMinuteById() {
        Call<List<LitersMinute>> call = ConnectionServer.getInstance().getService().findByIdLitersMinute(activity.getId());

        call.enqueue(new Callback<List<LitersMinute>>() {
            @Override
            public void onResponse(Call<List<LitersMinute>> call, Response<List<LitersMinute>> response) {
                /*if (activity.getLitersMinutes().equals(response.body())) {
                    Toast.makeText(activity, "Nada novo", Toast.LENGTH_LONG);
                } else {*/
                activity.setLitersMinutes(response.body());
                Log.i("LitersMinute", activity.getLitersMinutes().toString());
                //}

                activity.litersfragmet();
                activity.setRefrash(false);
            }

            @Override
            public void onFailure(Call<List<LitersMinute>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(activity);
                activity.setRefrash(false);
            }
        });
    }
}
