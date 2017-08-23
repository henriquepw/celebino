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
import org.celebino.celebinoapp.activities.GardenStatusActivity;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.adapters.GardenAdapter;
import org.celebino.celebinoapp.adapters.GardenStatusAdapter;

public class GardenStatusFragment extends Fragment {
    private GardenStatusActivity main;

    private RecyclerView recyclerGardenStatus;

    public GardenStatusFragment(GardenStatusActivity main) {
        this.main = main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerGardenStatus = (RecyclerView) view.findViewById(R.id.recyclerGardenStatus);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerGardenStatus.setLayoutManager(layoutManager);
        setAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_garden_status, container, false);
    }

    public void setAdapter() {
        if (this.main.getGardenStatuses().size() > 0) {
            GardenStatusAdapter adapter = new GardenStatusAdapter(main);
            recyclerGardenStatus.setAdapter(adapter);
        } else {
            main.noItenfragmet();
        }
    }

}
