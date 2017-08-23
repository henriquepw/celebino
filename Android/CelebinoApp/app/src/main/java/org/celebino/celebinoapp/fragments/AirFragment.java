package org.celebino.celebinoapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
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
import org.celebino.celebinoapp.entities.AirConditioning;

import java.util.List;

public class AirFragment extends Fragment {
    private MainActivity main;

    private RecyclerView recyclerAir;
    private TextView tvAir;
    private TextView tvLiters;
    private final int id = 4;

    public AirFragment(MainActivity main) {
        this.main = main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Ares-condicionados");

        tvAir = (TextView) view.findViewById(R.id.tvAir);
        tvLiters = (TextView) view.findViewById(R.id.tvLiters);
        recyclerAir = (RecyclerView) view.findViewById(R.id.recyclerAir);

        Log.i("AirFragment", recyclerAir.toString());
        this.tvAir.setText(String.valueOf(main.getAirConditionings().size()));
        this.tvLiters.setText(String.valueOf(main.totalLiters()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerAir.setLayoutManager(layoutManager);
        setAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_air, container, false);
    }

    public void setAdapter() {
        if (this.main.getAirConditionings().size() > 0) {
            AirConditioningAdapter adapter = new AirConditioningAdapter(main);
            recyclerAir.setAdapter(adapter);
        } else {
            main.noItemfragmet(id);
        }
    }

    public void setTvAir(String tvAir) {
        this.tvAir.setText(tvAir);
    }

    public void setTvLiters(String tvLiters) {
        this.tvLiters.setText(tvLiters);
    }

    public List<AirConditioning> getAirConditionings() {
        return main.getAirConditionings();
    }

    public void setAirConditionings(List<AirConditioning> airConditionings) {
        this.main.setAirConditionings(airConditionings);
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public void setTotal() {
        this.tvAir.setText(String.valueOf(main.getAirConditionings().size()));
        this.tvLiters.setText(String.valueOf(main.totalLiters()));
    }
}
