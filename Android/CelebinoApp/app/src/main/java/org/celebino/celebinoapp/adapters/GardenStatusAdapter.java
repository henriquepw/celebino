package org.celebino.celebinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.GardenStatusActivity;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.entities.GardenStatus;

import java.text.SimpleDateFormat;

public class GardenStatusAdapter extends RecyclerView.Adapter<GardenStatusAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private GardenStatusActivity context;

    public GardenStatusAdapter(GardenStatusActivity context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_garden_status, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GardenStatus gardenStatus = this.context.getGardenStatuses().get(position);
        holder.setDate(gardenStatus, position);
    }

    @Override
    public int getItemCount() {
        return this.context.getGardenStatuses().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvGardenId;
        protected TextView tvDate;
        protected TextView tvSunL;
        protected TextView tvSoilHumidity;
        protected TextView tvAirHumidity;
        protected TextView tvAirTemperature;

        private int position;
        private GardenStatus gardenStatus;

        public ViewHolder(View view) {
            super(view);
            tvGardenId = (TextView) view.findViewById(R.id.tvGardenId);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvSunL = (TextView) view.findViewById(R.id.tvSunL);
            tvSoilHumidity = (TextView) view.findViewById(R.id.tvSoilHumidity);
            tvAirHumidity = (TextView) view.findViewById(R.id.tvAirHumidity);
            tvAirTemperature = (TextView) view.findViewById(R.id.tvAirTemperature);

        }

        public void setDate(GardenStatus gardenStatus, int position) {
            this.tvGardenId.setText("#" + gardenStatus.getId().toString());
            this.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(gardenStatus.getTime()));
            this.tvSunL.setText(String.valueOf(gardenStatus.getSunLight()));
            this.tvSoilHumidity.setText(String.valueOf(gardenStatus.getSoilHumidity()));
            this.tvAirHumidity.setText(String.valueOf(gardenStatus.getAirHumidity()));
            this.tvAirTemperature.setText(String.valueOf(gardenStatus.getAirTemperature()));

            this.gardenStatus = gardenStatus;
            this.position = position;
        }
    }

    public void add(int position, GardenStatus item) {
        this.context.getGardenStatuses().add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Garden item) {
        int position = this.context.getGardenStatuses().indexOf(item);
        this.context.getGardenStatuses().remove(position);
        notifyItemRemoved(position);
    }
}
