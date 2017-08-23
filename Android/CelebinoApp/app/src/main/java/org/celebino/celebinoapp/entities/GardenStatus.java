package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class GardenStatus implements Parcelable {
    private Long id;
    private Garden garden;
    private double sunLight;
    private double soilHumidity;
    private double airHumidity;
    private double airTemperature;
    private Long time;

    public GardenStatus() {
    }

    public GardenStatus(Long id, Garden garden, double sunLight, double soilHumidity, double airHumidity, double airTemperature,
                        Long time) {
        super();
        this.id = id;
        this.garden = garden;
        this.sunLight = sunLight;
        this.soilHumidity = soilHumidity;
        this.airHumidity = airHumidity;
        this.airTemperature = airTemperature;
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

    public double getSunLight() {
        return sunLight;
    }

    public void setSunLight(double sunLight) {
        this.sunLight = sunLight;
    }

    public double getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(double soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GardenStatus " +
                "[id#" + id +
                ", garden= " + garden +
                ", sunLight= " + sunLight +
                ", soilHumidity= " + soilHumidity +
                ", airHumidity= " + airHumidity +
                ", airTemperature= " + airTemperature +
                ", time=" + time + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.garden, flags);
        dest.writeDouble(this.sunLight);
        dest.writeDouble(this.soilHumidity);
        dest.writeDouble(this.airHumidity);
        dest.writeDouble(this.airTemperature);
        dest.writeValue(this.time);
    }

    protected GardenStatus(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.garden = in.readParcelable(Garden.class.getClassLoader());
        this.sunLight = in.readDouble();
        this.soilHumidity = in.readDouble();
        this.airHumidity = in.readDouble();
        this.airTemperature = in.readDouble();
        this.time = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<GardenStatus> CREATOR = new Creator<GardenStatus>() {
        @Override
        public GardenStatus createFromParcel(Parcel source) {
            return new GardenStatus(source);
        }

        @Override
        public GardenStatus[] newArray(int size) {
            return new GardenStatus[size];
        }
    };
}

