package org.celebino.celebinoapp.activities;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.entities.LitersMinute;
import org.celebino.celebinoapp.fragments.LitersFragment;
import org.celebino.celebinoapp.fragments.NoItenFragment;
import org.celebino.celebinoapp.listeners.OnRefresh.LitersMinuteOnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LitersMinuteActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.swipeLiters)
    protected SwipeRefreshLayout swipe;

    private List<LitersMinute> litersMinutes;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liters_minute);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        id = Long.valueOf(getIntent().getStringExtra("id"));
        setTitle("Ar-condicionado #" + id);

        litersMinutes = new ArrayList<>();
        litersMinutes = getIntent().getParcelableArrayListExtra("litersMinutes");
        litersfragmet();

        swipe.setOnRefreshListener(new LitersMinuteOnRefreshListener(this));

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

    public List<LitersMinute> getLitersMinutes() {
        return litersMinutes;
    }

    public Double totalLiters() {
        Double total = 0.0;
        for (LitersMinute l : litersMinutes) {
            total += l.getLmin();
        }

        return total;
    }

    public void litersfragmet() {
        Fragment fragment = new LitersFragment(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_liters, fragment);
        fragmentTransaction.commit();
    }

    public void noItenfragmet() {
        Fragment fragment = new NoItenFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_liters, fragment);
        fragmentTransaction.commit();
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public SwipeRefreshLayout getSwipe() {
        return swipe;
    }

    public void setSwipe(SwipeRefreshLayout swipe) {
        this.swipe = swipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRefrash(boolean set) {
        swipe.setRefreshing(set);
    }

    public void setLitersMinutes(List<LitersMinute> litersMinutes) {
        this.litersMinutes = litersMinutes;
    }
}
