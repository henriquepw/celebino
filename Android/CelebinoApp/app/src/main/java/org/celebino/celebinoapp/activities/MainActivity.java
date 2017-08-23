package org.celebino.celebinoapp.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.entities.LitersMinute;
import org.celebino.celebinoapp.fragments.AirFragment;
import org.celebino.celebinoapp.fragments.GardenFragment;
import org.celebino.celebinoapp.fragments.HomeFragment;
import org.celebino.celebinoapp.fragments.NoItenFragment;
import org.celebino.celebinoapp.listeners.BottomBarOnTabSelectListener;
import org.celebino.celebinoapp.listeners.OnClick.AirBtSaveOnClickListener;
import org.celebino.celebinoapp.listeners.OnClick.AirBtUpdateOnClickListener;
import org.celebino.celebinoapp.listeners.OnClick.ChoiceOnClickListener;
import org.celebino.celebinoapp.listeners.OnClick.GardenBtSaveOnClickListener;
import org.celebino.celebinoapp.listeners.OnRefresh.MainOnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottomBar)
    protected BottomBar bottomBar;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.swipe)
    protected SwipeRefreshLayout swipe;

    private List<AirConditioning> airConditionings;
    private List<LitersMinute> litersMinutes;
    private List<Garden> gardens;
    private List<GardenStatus> gardenStatuses;

    private int fragment;

    private EditText etLocality;
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private Button btSave;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        bottomBar.setOnTabSelectListener(new BottomBarOnTabSelectListener(this));
        swipe.setOnRefreshListener(new MainOnRefreshListener(this));

        airConditionings = new ArrayList<>();
        litersMinutes = new ArrayList<>();
        gardens = new ArrayList<>();
        gardenStatuses = new ArrayList<>();

        airConditionings = getIntent().getParcelableArrayListExtra("airConditionings");
        litersMinutes = getIntent().getParcelableArrayListExtra("litersMinutes");
        gardens = getIntent().getParcelableArrayListExtra("gardens");
        gardenStatuses = getIntent().getParcelableArrayListExtra("gardenStatuses");

    }

    public void noItemfragmet(int i) {
        this.fragment = i;
        Fragment fragment = new NoItenFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void homefragmet() {
        this.fragment = 1;
        Fragment fragment = new HomeFragment(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void airfragmet() {
        this.fragment = 2;
        Fragment fragment = new AirFragment(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void gardenfragmet() {
        this.fragment = 3;
        Fragment fragment = new GardenFragment(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public Double totalLiters() {
        Double total = 0.0;
        for (LitersMinute l : litersMinutes) {
            total += l.getLmin();
        }

        return total;
    }

    public List<AirConditioning> getAirConditionings() {
        return airConditionings;
    }

    public void setAirConditionings(List<AirConditioning> airConditionings) {
        this.airConditionings = airConditionings;
    }

    public List<LitersMinute> getLitersMinutes() {
        return litersMinutes;
    }

    public void setLitersMinutes(List<LitersMinute> litersMinutes) {
        this.litersMinutes = litersMinutes;
    }

    public int getFragment() {
        return fragment;
    }

    public void setFragment(int fragment) {
        this.fragment = fragment;
    }

    public void setRefrash(boolean set) {
        swipe.setRefreshing(set);
    }

    public List<Garden> getGardens() {
        return gardens;
    }

    public void setGardens(List<Garden> gardens) {
        this.gardens = gardens;
    }

    public List<GardenStatus> getGardenStatuses() {
        return gardenStatuses;
    }

    public void setGardenStatuses(List<GardenStatus> gardenStatuses) {
        this.gardenStatuses = gardenStatuses;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        builder = new AlertDialog.Builder(this);
        if (item.getItemId() == R.id.action_add) {
            switch (fragment) {
                case 1: // HomeFragment
                    view = getLayoutInflater().inflate(R.layout.dialog_choice, null);
                    Button btAir = (Button) view.findViewById(R.id.btAir);
                    Button btGarden = (Button) view.findViewById(R.id.btGarden);

                    btAir.setOnClickListener(new ChoiceOnClickListener(this, true));
                    btGarden.setOnClickListener(new ChoiceOnClickListener(this, false));
                    showDialog();
                    break;
                case 2: // AirFragment
                    setViewInsert();
                    btSave.setOnClickListener(new AirBtSaveOnClickListener(this));

                    showDialog();
                    break;
                case 3: // GardenFragment
                    setViewInsert();
                    btSave.setOnClickListener(new GardenBtSaveOnClickListener(this));

                    showDialog();

                    break;
                case 4: // no itens Air
                    setViewInsert();
                    btSave.setOnClickListener(new AirBtSaveOnClickListener(this));

                    showDialog();
                    break;
                case 5: // no itens garden
                    setViewInsert();
                    btSave.setOnClickListener(new GardenBtSaveOnClickListener(this));

                    showDialog();
                    break;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDialog() {
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    public void setViewInsert() {
        view = getLayoutInflater().inflate(R.layout.dialog_insert, null);
        etLocality = (EditText) view.findViewById(R.id.etLocality);
        btSave = (Button) view.findViewById(R.id.btSave);
    }

    public void dismissDialogg() {
        dialog.dismiss();
    }

    public EditText getEtLocality() {
        return etLocality;
    }

    public void setEtLocality(EditText etLocality) {
        this.etLocality = etLocality;
    }

    public AlertDialog.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(AlertDialog.Builder builder) {
        this.builder = builder;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Button getBtSave() {
        return btSave;
    }

    public void setBtSave(View.OnClickListener onClickListener) {
        this.btSave.setOnClickListener(onClickListener);
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}

