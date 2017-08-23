package org.celebino.celebinoapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.conection.Requisition;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements Runnable {
    private List<AirConditioning> airConditionings;
    private List<LitersMinute> litersMinutes;
    private List<Garden> gardens;
    private List<GardenStatus> gardenStatuses;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        airConditionings = new ArrayList<>();
        litersMinutes = new ArrayList<>();

        image = (ImageView) findViewById(R.id.loading);
        image.setBackgroundResource(R.drawable.loading);
        AnimationDrawable load = (AnimationDrawable) image.getBackground();
        load.start();

        new Handler().postDelayed(this, 2000);
    }

    @Override
    public void run() {
        getAllAirConditioning();
    }

    public void getAllAirConditioning() {
        Call<List<AirConditioning>> call = ConnectionServer.getInstance().getService().getAllAirConditioning();

        call.enqueue(new Callback<List<AirConditioning>>() {
            @Override
            public void onResponse(Call<List<AirConditioning>> call, Response<List<AirConditioning>> response) {
                airConditionings = response.body();
                Log.i("SplashActivity", airConditionings.toString());

                getAllLitersMinute();
            }

            @Override
            public void onFailure(Call<List<AirConditioning>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getStackTrace());
                Requisition.getRequisition().toaskOnFailure(getBaseContext());
                finish();
            }
        });
    }

    public void getAllLitersMinute() {
        Call<List<LitersMinute>> call = ConnectionServer.getInstance().getService().getAllLitersMinute();

        call.enqueue(new Callback<List<LitersMinute>>() {
            @Override
            public void onResponse(Call<List<LitersMinute>> call, Response<List<LitersMinute>> response) {
                litersMinutes = response.body();
                Log.i("SplashActivity", litersMinutes.toString());

                getAllGarden();
            }

            @Override
            public void onFailure(Call<List<LitersMinute>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(getBaseContext());
                finish();
            }
        });
    }

    public void getAllGarden() {
        Call<List<Garden>> call = ConnectionServer.getInstance().getService().getAllGarden();

        call.enqueue(new Callback<List<Garden>>() {
            @Override
            public void onResponse(Call<List<Garden>> call, Response<List<Garden>> response) {
                gardens = response.body();
                Log.i("SplashActivity", gardens.toString());

                getAllGardenStatus();
            }

            @Override
            public void onFailure(Call<List<Garden>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(getBaseContext());
                finish();
            }
        });
    }

    public void getAllGardenStatus() {
        Call<List<GardenStatus>> call = ConnectionServer.getInstance().getService().getAllGardenStatus();

        call.enqueue(new Callback<List<GardenStatus>>() {
            @Override
            public void onResponse(Call<List<GardenStatus>> call, Response<List<GardenStatus>> response) {
                gardenStatuses = response.body();
                Log.i("SplashActivity", gardenStatuses.toString());

                transition();
            }

            @Override
            public void onFailure(Call<List<GardenStatus>> call, Throwable t) {
                Log.e("onFailure", "Erro: " + t.getMessage().toString());
                Requisition.getRequisition().toaskOnFailure(getBaseContext());
                finish();
            }
        });
    }

    public void transition() {
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        i.putParcelableArrayListExtra("litersMinutes", (ArrayList<? extends Parcelable>) litersMinutes);
        i.putParcelableArrayListExtra("airConditionings", (ArrayList<? extends Parcelable>) airConditionings);

        i.putParcelableArrayListExtra("gardens", (ArrayList<? extends Parcelable>) gardens);
        i.putParcelableArrayListExtra("gardenStatuses", (ArrayList<? extends Parcelable>) gardenStatuses);

        startActivity(i);
        finish();
    }

}
