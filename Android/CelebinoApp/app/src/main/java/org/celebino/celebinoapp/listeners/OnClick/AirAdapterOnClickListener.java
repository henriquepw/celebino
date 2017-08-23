package org.celebino.celebinoapp.listeners.OnClick;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henri on 16/06/2017.
 */

public class AirAdapterOnClickListener implements OnClickListener {
    private List<LitersMinute> litersMinutes;
    private MainActivity mainActivity;
    private String id;

    public AirAdapterOnClickListener(List<LitersMinute> litersMinutes, MainActivity mainActivity, String id) {
        this.litersMinutes = litersMinutes;
        this.mainActivity = mainActivity;
        this.id = id;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(mainActivity, LitersMinuteActivity.class);
        i.putParcelableArrayListExtra("litersMinutes", (ArrayList<? extends Parcelable>) litersMinutes);
        i.putExtra("id", id);

        mainActivity.startActivity(i);
    }
}
