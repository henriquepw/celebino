package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class Watering implements Parcelable {
    private Long id;
    private Garden garden;
    private Long time;

    public Watering() {
    }

    public Watering(Long id, Garden garden, Long time) {
        super();
        this.id = id;
        this.garden = garden;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Watering [id=" + id + ", garden=" + garden + ", time=" + time + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.garden, flags);
        dest.writeValue(this.time);
    }

    protected Watering(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.garden = in.readParcelable(Garden.class.getClassLoader());
        this.time = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Watering> CREATOR = new Creator<Watering>() {
        @Override
        public Watering createFromParcel(Parcel source) {
            return new Watering(source);
        }

        @Override
        public Watering[] newArray(int size) {
            return new Watering[size];
        }
    };
}

