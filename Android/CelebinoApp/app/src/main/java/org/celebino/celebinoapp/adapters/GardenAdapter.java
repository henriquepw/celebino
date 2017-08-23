package org.celebino.celebinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;
import org.celebino.celebinoapp.entities.Garden;
import org.celebino.celebinoapp.listeners.OnClick.GardenStatusDialogOnClickListener;
import org.celebino.celebinoapp.listeners.OnClick.GardenUpdateDialogOnClickListener;

public class GardenAdapter extends RecyclerView.Adapter<GardenAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private MainActivity context;

    public GardenAdapter(MainActivity context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_garden, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Garden garden = this.context.getGardens().get(position);

        holder.setDate(garden, position);
        holder.setLinearOnClickListener(new GardenStatusDialogOnClickListener(context, garden.getId()));
    }

    @Override
    public int getItemCount() {
        return this.context.getGardens().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvId;
        protected TextView tvLocality;
        protected LinearLayout linearLayout;
        protected ImageButton btUpdate;

        private int position;
        private Garden garden;

        public ViewHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tvId);
            tvLocality = (TextView) view.findViewById(R.id.tvLocality);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            btUpdate = (ImageButton) view.findViewById(R.id.btUpdate);

        }

        public void setDate(Garden garden, int position) {
            this.tvId.setText("#" + garden.getId().toString());

            if (garden.getLocality() != null) {
                this.tvLocality.setText("Local: " + garden.getLocality());
            } else {
                this.tvLocality.setText("Local: não informado");
            }

            this.btUpdate.setOnClickListener(new GardenUpdateDialogOnClickListener(context, garden));
            this.garden = garden;
            this.position = position;
        }

        public void setLinearOnClickListener(View.OnClickListener onClickListener) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void add(int position, Garden item) {
        this.context.getGardens().add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Garden item) {
        int position = this.context.getGardens().indexOf(item);
        this.context.getAirConditionings().remove(position);
        notifyItemRemoved(position);
    }
}
