package org.celebino.celebinoapp.listeners.OnRefresh;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.activities.SplashActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;
import org.celebino.celebinoapp.fragments.AirFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 17/06/2017.
 */

public class MainOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
    private MainActivity activity;

    public MainOnRefreshListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onRefresh() {
        switch (activity.getFragment()) {
            case 1: // HomeFragment
                getAll();
                break;
            case 2: // AirFragment
                getAllAirConditioning();
                break;
            case 3: // GardenFragment
                getAllGarden();
                break;
            case 4: // no itens Air
                getAllAirConditioning();
                break;
            case 5: // no itens garden
                getAllGarden();
                break;
            default:
                break;
        }
    }

    public void getAllAirConditioning() {
        Call<List<AirConditioning>> call = ConnectionServer.getInstance().getService().getAllAirConditioning();

        call.enqueue(new Callback<List<AirConditioning>>() {
            @Override
            public void onResponse(Call<List<AirConditioning>> call, Response<List<AirConditioning>> response) {
                activity.setAirConditionings(response.body());
                Log.i("AirConditioning", activity.getAirConditionings().toString());

                getAllLitersMinute();

            }

            @Override
            public void onFailure(Call<List<AirConditioning>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getStackTrace());
                Requisition.getRequisition().toaskOnFailure(activity);
                activity.setRefrash(false);
            }
        });
    }

    public void getAllLitersMinute() {
        Call<List<LitersMinute>> call = ConnectionServer.getInstance().getService().getAllLitersMinute();

        call.enqueue(new Callback<List<LitersMinute>>() {
            @Override
            public void onResponse(Call<List<LitersMinute>> call, Response<List<LitersMinute>> response) {
               /* if (activity.getLitersMinutes()) {
                    Toast.makeText(activity, "Nada novo", Toast.LENGTH_LONG);
                } else {*/
                activity.setLitersMinutes(response.body());
                Log.i("LitersMinute", activity.getLitersMinutes().toString());
                //}

                activity.airfragmet();
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

    public void getAllGarden() {
        Call<List<Garden>> call = ConnectionServer.getInstance().getService().getAllGarden();

        call.enqueue(new Callback<List<Garden>>() {
            @Override
            public void onResponse(Call<List<Garden>> call, Response<List<Garden>> response) {
                activity.setGardens(response.body());
                Log.i("SplashActivity", activity.getGardens().toString());

                getAllGardenStatus();
            }

            @Override
            public void onFailure(Call<List<Garden>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(activity);
                activity.setRefrash(false);
            }
        });
    }

    public void getAllGardenStatus() {
        Call<List<GardenStatus>> call = ConnectionServer.getInstance().getService().getAllGardenStatus();

        call.enqueue(new Callback<List<GardenStatus>>() {
            @Override
            public void onResponse(Call<List<GardenStatus>> call, Response<List<GardenStatus>> response) {
                activity.setGardenStatuses(response.body());
                Log.i("SplashActivity", activity.getGardenStatuses().toString());

                activity.gardenfragmet();
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

    public void getAll() {
        Call<List<AirConditioning>> call = ConnectionServer.getInstance().getService().getAllAirConditioning();

        call.enqueue(new Callback<List<AirConditioning>>() {
            @Override
            public void onResponse(Call<List<AirConditioning>> call, Response<List<AirConditioning>> response) {
                activity.setAirConditionings(response.body());
                Log.i("AirConditioning", activity.getAirConditionings().toString());

                Call<List<LitersMinute>> call2 = ConnectionServer.getInstance().getService().getAllLitersMinute();

                call2.enqueue(new Callback<List<LitersMinute>>() {
                    @Override
                    public void onResponse(Call<List<LitersMinute>> call, Response<List<LitersMinute>> response) {
                        activity.setLitersMinutes(response.body());
                        Log.i("LitersMinute", activity.getLitersMinutes().toString());

                        Call<List<Garden>> call3 = ConnectionServer.getInstance().getService().getAllGarden();

                        call3.enqueue(new Callback<List<Garden>>() {
                            @Override
                            public void onResponse(Call<List<Garden>> call, Response<List<Garden>> response) {
                                activity.setGardens(response.body());
                                Log.i("SplashActivity", activity.getGardens().toString());

                                Call<List<GardenStatus>> call4 = ConnectionServer.getInstance().getService().getAllGardenStatus();

                                call4.enqueue(new Callback<List<GardenStatus>>() {
                                    @Override
                                    public void onResponse(Call<List<GardenStatus>> call, Response<List<GardenStatus>> response) {
                                        activity.setGardenStatuses(response.body());
                                        Log.i("SplashActivity", activity.getGardenStatuses().toString());

                                        activity.homefragmet();
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

                            @Override
                            public void onFailure(Call<List<Garden>> call, Throwable t) {
                                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                                Requisition.getRequisition().toaskOnFailure(activity);
                                activity.setRefrash(false);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<List<LitersMinute>> call, Throwable t) {
                        Log.e("onFailure", "Erro: " + t.getMessage().toString());
                        Requisition.getRequisition().toaskOnFailure(activity);
                        activity.setRefrash(false);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<AirConditioning>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getStackTrace());
                Requisition.getRequisition().toaskOnFailure(activity);
                activity.setRefrash(false);
            }
        });

    }

}
