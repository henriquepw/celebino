package org.celebino.celebinoapp.listeners.OnClick;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.entities.Garden;

/**
 * Created by henri on 16/06/2017.
 */

public class GardenUpdateDialogOnClickListener implements OnClickListener {
    private MainActivity mainActivity;
    private Garden garden;
    private AlertDialog dialog;
    private Button btSave;
    private EditText etLocality;

    public GardenUpdateDialogOnClickListener(MainActivity mainActivity, Garden garden) {
        this.mainActivity = mainActivity;
        this.garden = garden;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        View view = mainActivity.getLayoutInflater().inflate(R.layout.dialog_update, null);
        etLocality = (EditText) view.findViewById(R.id.etLocality);
        btSave = (Button) view.findViewById(R.id.btSave);

        btSave.setOnClickListener(new GardenBtUpdateOnClickListener(garden, this));

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    public void setLocality(String locality) {
        garden.setLocality(locality);
    }

    public void dismissDialogg() {
        dialog.dismiss();
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public Button getBtSave() {
        return btSave;
    }

    public void setBtSave(Button btSave) {
        this.btSave = btSave;
    }

    public EditText getEtLocality() {
        return etLocality;
    }

    public void setEtLocality(EditText etLocality) {
        this.etLocality = etLocality;
    }
}
