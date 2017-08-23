package org.celebino.celebinoapp.listeners.OnClick;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import org.celebino.celebinoapp.activities.GardenStatusActivity;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 16/06/2017.
 */

public class GardenStatusActivityOnClickListener implements OnClickListener {
    private MainActivity main;
    private List<GardenStatus> gardenStatuses;
    private Long id;

    public GardenStatusActivityOnClickListener(MainActivity main, List<GardenStatus> gardenStatuses, Long id) {
        this.gardenStatuses = gardenStatuses;
        this.main = main;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(main, GardenStatusActivity.class);
        i.putParcelableArrayListExtra("gardenStatuses", (ArrayList<? extends Parcelable>) gardenStatuses);
        i.putExtra("gardenStatusId", id);
        main.startActivity(i);
    }
}
