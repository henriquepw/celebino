package org.celebino.celebinoapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.adapters.AirConditioningAdapter;
import org.celebino.celebinoapp.adapters.GardenAdapter;
import org.celebino.celebinoapp.entities.AirConditioning;

import java.util.List;

public class GardenFragment extends Fragment {
    private MainActivity main;

    private RecyclerView recyclerGarden;
    private TextView tvGarden;
    private final int id = 5;

    public GardenFragment(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Jardins");

        tvGarden = (TextView) view.findViewById(R.id.tvGarden);
        recyclerGarden = (RecyclerView) view.findViewById(R.id.recyclerGarden);

        Log.i("GardenFragment", recyclerGarden.toString());
        this.tvGarden.setText(String.valueOf(main.getGardens().size()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerGarden.setLayoutManager(layoutManager);
        setAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_garden, container, false);
    }

    public void setAdapter() {
        if (this.main.getGardens().size() > 0) {
            GardenAdapter adapter = new GardenAdapter(main);
            recyclerGarden.setAdapter(adapter);
        } else {
            main.noItemfragmet(id);
        }
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void setTotal() {
        this.tvGarden.setText(String.valueOf(main.getGardens().size()));
    }

}
