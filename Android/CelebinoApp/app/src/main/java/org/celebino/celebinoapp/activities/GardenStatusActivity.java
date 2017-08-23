package org.celebino.celebinoapp.activities;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.entities.GardenStatus;
import org.celebino.celebinoapp.fragments.GardenStatusFragment;
import org.celebino.celebinoapp.fragments.NoItenFragment;
import org.celebino.celebinoapp.listeners.OnRefresh.GardenStatusOnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GardenStatusActivity extends AppCompatActivity {
    private List<GardenStatus> gardenStatuses;
    private Long id;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.swipeGardenStatus)
    protected SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_status);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        id = getIntent().getLongExtra("gardenStatusId", 0);
        setTitle("Jardim  #" + id);

        gardenStatuses = getIntent().getParcelableArrayListExtra("gardenStatuses");

        if (gardenStatuses.size() > 0) {
            Log.i("GardenStatusActivity", gardenStatuses.get(0).getGarden().getId().toString());
            gardenStatusfragmet();
        } else {
            noItenfragmet();
        }

        swipe.setOnRefreshListener(new GardenStatusOnRefreshListener(this));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    public void gardenStatusfragmet() {
        Fragment fragment = new GardenStatusFragment(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_garden, fragment);
        fragmentTransaction.commit();
    }

    public void noItenfragmet() {
        Fragment fragment = new NoItenFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_garden, fragment);
        fragmentTransaction.commit();
    }

    public void setRefrash(boolean set) {
        swipe.setRefreshing(set);
    }

    public List<GardenStatus> getGardenStatuses() {
        return gardenStatuses;
    }

    public void setGardenStatuses(List<GardenStatus> gardenStatuses) {
        this.gardenStatuses = gardenStatuses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
