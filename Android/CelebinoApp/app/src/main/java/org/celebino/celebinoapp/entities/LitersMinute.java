package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class LitersMinute implements Parcelable {
    private Long id;
    private Long date;
    private Double lmin;
    private AirConditioning airconditioning;

    public LitersMinute() {
    }

    public LitersMinute(Long id, Long date, Double lmin, AirConditioning airconditioning) {
        this.id = id;
        this.date = date;
        this.lmin = lmin;
        this.airconditioning = airconditioning;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getLmin() {
        return lmin;
    }

    public void setLmin(Double lmin) {
        this.lmin = lmin;
    }

    public AirConditioning getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(AirConditioning airconditioning) {
        this.airconditioning = airconditioning;
    }

    @Override
    public String toString() {
        if (date != null) {
            return this.lmin + " L/Min - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) + " #" + this.airconditioning.getId();
        } else {
            return this.lmin + " L/Min - data não informada" + " #" + this.airconditioning.getId();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LitersMinute that = (LitersMinute) o;

        if (!id.equals(that.id)) return false;
        if (!date.equals(that.date)) return false;
        if (!lmin.equals(that.lmin)) return false;
        return airconditioning.equals(that.airconditioning);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + lmin.hashCode();
        result = 31 * result + airconditioning.hashCode();
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.date);
        dest.writeValue(this.lmin);
        dest.writeParcelable(this.airconditioning, flags);
    }

    protected LitersMinute(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = (Long) in.readValue(Long.class.getClassLoader());
        this.lmin = (Double) in.readValue(Double.class.getClassLoader());
        this.airconditioning = in.readParcelable(AirConditioning.class.getClassLoader());
    }

    public static final Creator<LitersMinute> CREATOR = new Creator<LitersMinute>() {
        @Override
        public LitersMinute createFromParcel(Parcel source) {
            return new LitersMinute(source);
        }

        @Override
        public LitersMinute[] newArray(int size) {
            return new LitersMinute[size];
        }
    };
}
