package org.celebino.celebinoapp.conection;

import android.content.Context;
import android.widget.Toast;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.entities.AirConditioning;

import java.util.List;
import java.util.Random;


/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class Requisition {
    public List<AirConditioning> airConditionings;

    public Requisition() {
    }

    public static Requisition getRequisition() {
        Requisition instance = new Requisition();
        return instance;
    }

    public void toask(int code, String message, Context context) {
        if (code >= 400 && code < 599) {
            Toast.makeText(context, "Erro " + code, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void toaskErro(int code, String message, Context context) {
        if (code >= 400 && code < 599) {
            Toast.makeText(context, message + code, Toast.LENGTH_SHORT).show();
        }
    }

    public void toaskOnFailure(Context context) {
        Toast.makeText(context, "verifique sua conecção com a internet", Toast.LENGTH_SHORT).show();
    }
}
