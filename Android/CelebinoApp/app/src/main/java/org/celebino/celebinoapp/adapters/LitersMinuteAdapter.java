package org.celebino.celebinoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.LitersMinuteActivity;
import org.celebino.celebinoapp.entities.LitersMinute;

import java.text.SimpleDateFormat;

public class LitersMinuteAdapter extends RecyclerView.Adapter<LitersMinuteAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private LitersMinuteActivity context;

    public LitersMinuteAdapter(LitersMinuteActivity context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_liters, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LitersMinute liters = this.context.getLitersMinutes().get(position);
        holder.setDate(liters, position);
    }

    @Override
    public int getItemCount() {
        return this.context.getLitersMinutes().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvLmin;
        protected TextView tvDate;
        protected TextView tvId;

        private int position;
        private LitersMinute litersMinute;

        public ViewHolder(View view) {
            super(view);
            tvLmin = (TextView) view.findViewById(R.id.tvLmin);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvId = (TextView) view.findViewById(R.id.tvId);

        }

        public void setDate(LitersMinute liters, int position) {
            this.tvLmin.setText(liters.getLmin().toString() + " L/min");
            this.tvId.setText("#" + liters.getId().toString());

            if (liters.getDate() != null) {
                this.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(liters.getDate()));
            } else {
                this.tvDate.setText("Data não informada");
            }

            this.litersMinute = liters;
            this.position = position;
        }
    }

    public void add(int position, LitersMinute item) {
        this.context.getLitersMinutes().add(position, item);
        notifyItemInserted(position);
    }

    public void remove(LitersMinute item) {
        int position = this.context.getLitersMinutes().indexOf(item);
        this.context.getLitersMinutes().remove(position);
        notifyItemRemoved(position);
    }
}
