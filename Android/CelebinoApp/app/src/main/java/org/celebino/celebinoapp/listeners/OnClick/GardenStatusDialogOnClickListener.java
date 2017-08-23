package org.celebino.celebinoapp.listeners.OnClick;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.entities.GardenStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by henri on 16/06/2017.
 */

public class GardenStatusDialogOnClickListener implements OnClickListener {
    private MainActivity mainActivity;
    private Long gardenId;
    private AlertDialog dialog;
    private List<GardenStatus> gardenStatusbyGarden;
    private GardenStatus gardenStatusPresent;
    private Button btHistoric;
    private TextView tvGardenId;
    private TextView tvDate;
    private TextView tvSunL;
    private TextView tvSoilHumidity;
    private TextView tvAirHumidity;
    private TextView tvAirTemperature;

    public GardenStatusDialogOnClickListener(MainActivity mainActivity, Long gardenId) {
        this.mainActivity = mainActivity;
        this.gardenId = gardenId;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View view = mainActivity.getLayoutInflater().inflate(R.layout.dialog_garden_status, null);

        tvGardenId = (TextView) view.findViewById(R.id.tvGardenId);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        tvSunL = (TextView) view.findViewById(R.id.tvSunL);
        tvSoilHumidity = (TextView) view.findViewById(R.id.tvSoilHumidity);
        tvAirHumidity = (TextView) view.findViewById(R.id.tvAirHumidity);
        tvAirTemperature = (TextView) view.findViewById(R.id.tvAirTemperature);
        btHistoric = (Button) view.findViewById(R.id.btHistoric);

        tvGardenId.setText("Jardim #" + gardenId);
        getStatusbyGarden();
        getStatusPresent();

        if (gardenStatusPresent.getTime().longValue() != 0) {
            this.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(gardenStatusPresent.getTime()));
        } else {
            this.tvDate.setText("Data não informada");
        }

        tvSunL.setText(String.valueOf(gardenStatusPresent.getSunLight()));
        tvSoilHumidity.setText(String.valueOf(gardenStatusPresent.getSoilHumidity()));
        tvAirHumidity.setText(String.valueOf(gardenStatusPresent.getAirHumidity()));
        tvAirTemperature.setText(gardenStatusPresent.getAirTemperature() + "ºC");

        btHistoric.setOnClickListener(new GardenStatusActivityOnClickListener(mainActivity, gardenStatusbyGarden, gardenId));

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    public void getStatusbyGarden() {
        gardenStatusbyGarden = new ArrayList<>();
        for (GardenStatus s : mainActivity.getGardenStatuses()) {
            if (s.getGarden().getId().equals(gardenId)) {
                gardenStatusbyGarden.add(s);
            }
        }
    }

    public void getStatusPresent() {
        gardenStatusPresent = new GardenStatus();
        gardenStatusPresent.setTime((long) 0);

        for (GardenStatus s : this.gardenStatusbyGarden) {
            if (s.getTime() >= gardenStatusPresent.getTime()) {
                gardenStatusPresent = s;
            }
        }
    }

}
