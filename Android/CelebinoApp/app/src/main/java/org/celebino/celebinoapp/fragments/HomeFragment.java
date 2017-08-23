package org.celebino.celebinoapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;

public class HomeFragment extends Fragment {
    private MainActivity main;

    private TextView tvAir;
    private TextView tvGarden;
    private TextView tvLiters;

    public HomeFragment() {
    }

    public HomeFragment(MainActivity mainActivity) {
        this.main = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Celebino");

        tvAir = (TextView) view.findViewById(R.id.tvAir);
        tvGarden = (TextView) view.findViewById(R.id.tvGarden);
        tvLiters = (TextView) view.findViewById(R.id.tvLiters);

        if (main != null){
            tvAir.setText(String.valueOf(main.getAirConditionings().size()));
            tvGarden.setText(String.valueOf(main.getGardens().size()));
            tvLiters.setText(String.valueOf(main.totalLiters()));
        }

    }


}
