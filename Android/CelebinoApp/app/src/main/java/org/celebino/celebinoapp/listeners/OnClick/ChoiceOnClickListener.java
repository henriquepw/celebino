package org.celebino.celebinoapp.listeners.OnClick;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.conection.ConnectionServer;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by henri on 16/06/2017.
 */

public class ChoiceOnClickListener implements OnClickListener {
    private boolean choice;
    private MainActivity main;

    public ChoiceOnClickListener(MainActivity main, boolean choice) {
        this.choice = choice;
        this.main = main;
    }

    @Override
    public void onClick(View v) {
        main.dismissDialogg();
        main.setViewInsert();
        if (choice) {
            main.setBtSave(new AirBtSaveOnClickListener(main, true));
        } else {
            main.setBtSave(new GardenBtSaveOnClickListener(main, true));
        }

        main.showDialog();

    }
}
