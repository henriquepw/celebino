package org.celebino.celebinoapp.fragments;

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
import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.adapters.LitersMinuteAdapter;


public class LitersFragment extends Fragment {
    private LitersMinuteActivity main;

    private RecyclerView recyclerLiters;
    private TextView tvLiters;

    public LitersFragment(LitersMinuteActivity main) {
        this.main = main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLiters = (TextView) view.findViewById(R.id.tvLiters);
        recyclerLiters = (RecyclerView) view.findViewById(R.id.recyclerLiters);

        Log.i("LitersFragment", recyclerLiters.toString());
        this.tvLiters.setText(String.valueOf(main.totalLiters()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerLiters.setLayoutManager(layoutManager);
        setAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_liters, container, false);
    }

    public void setAdapter() {
        if (main.getLitersMinutes().size() > 0) {
            LitersMinuteAdapter adapter = new LitersMinuteAdapter(main);
            recyclerLiters.setAdapter(adapter);
        } else {
            main.noItenfragmet();
        }
    }
}
