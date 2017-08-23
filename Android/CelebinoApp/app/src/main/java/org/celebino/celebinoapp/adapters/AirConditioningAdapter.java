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
import org.celebino.celebinoapp.entities.AirConditioning;
import org.celebino.celebinoapp.entities.LitersMinute;
import org.celebino.celebinoapp.listeners.OnClick.AirAdapterOnClickListener;
import org.celebino.celebinoapp.listeners.OnClick.AirUpdateDialogOnClickListener;

import java.util.ArrayList;
import java.util.List;

public class AirConditioningAdapter extends RecyclerView.Adapter<AirConditioningAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private MainActivity context;

    public AirConditioningAdapter(MainActivity context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_air, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AirConditioning air = this.context.getAirConditionings().get(position);

        holder.setDate(air, position);
        holder.setLinearOnClickListener(new AirAdapterOnClickListener(findLitersById(context.getLitersMinutes(), air.getId()), context, air.getId().toString()));
    }

    @Override
    public int getItemCount() {
        return this.context.getAirConditionings().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvId;
        protected TextView tvLocality;
        protected LinearLayout linearLayout;
        protected ImageButton btUpdate;

        private int position;
        private AirConditioning airConditioning;

        public ViewHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tvId);
            tvLocality = (TextView) view.findViewById(R.id.tvLocality);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            btUpdate = (ImageButton) view.findViewById(R.id.btUpdate);

        }

        public void setDate(AirConditioning air, int position) {
            this.tvId.setText("#" + air.getId().toString());

            if (air.getLocality() != null) {
                this.tvLocality.setText("Local: " + air.getLocality());
            } else {
                this.tvLocality.setText("Local: não informado");
            }

            this.btUpdate.setOnClickListener(new AirUpdateDialogOnClickListener(context, air));
            this.airConditioning = air;
            this.position = position;
        }

        public void setLinearOnClickListener(View.OnClickListener onClickListener) {
            linearLayout.setOnClickListener(onClickListener);
        }
    }

    public void add(int position, AirConditioning item) {
        this.context.getAirConditionings().add(position, item);
        notifyItemInserted(position);
    }

    public void remove(AirConditioning item) {
        int position = this.context.getAirConditionings().indexOf(item);
        this.context.getAirConditionings().remove(position);
        notifyItemRemoved(position);
    }

    public List<LitersMinute> findLitersById(List<LitersMinute> litersMinutes, Long id) {
        List<LitersMinute> lmin = new ArrayList<>();

        for (LitersMinute l : litersMinutes) {
            if (l.getAirconditioning().getId().equals(id)) {
                lmin.add(l);
            }
        }
        return lmin;
    }
}
