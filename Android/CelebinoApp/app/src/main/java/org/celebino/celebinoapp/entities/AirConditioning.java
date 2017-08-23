package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class AirConditioning implements Parcelable {
    private Long id;
    private String locality;

    public AirConditioning() {
    }

    public AirConditioning(Long id, String localily) {
        this.id = id;
        this.locality = localily;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + locality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirConditioning that = (AirConditioning) o;

        if (!id.equals(that.id)) return false;
        return locality.equals(that.locality);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + locality.hashCode();
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.locality);
    }

    protected AirConditioning(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.locality = in.readString();
    }

    public static final Creator<AirConditioning> CREATOR = new Creator<AirConditioning>() {
        @Override
        public AirConditioning createFromParcel(Parcel source) {
            return new AirConditioning(source);
        }

        @Override
        public AirConditioning[] newArray(int size) {
            return new AirConditioning[size];
        }
    };
}