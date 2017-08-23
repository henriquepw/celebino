package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Henrique Martins on 14/06/2017.
 */
public class Garden implements Parcelable {
    private Long id;
    private User user;
    private String locality;

    public Garden() {
    }

    public Garden(Long id, User user, String locality) {
        this.id = id;
        this.user = user;
        this.locality = locality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.locality);
    }

    protected Garden(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
        this.locality = in.readString();
    }

    public static final Creator<Garden> CREATOR = new Creator<Garden>() {
        @Override
        public Garden createFromParcel(Parcel source) {
            return new Garden(source);
        }

        @Override
        public Garden[] newArray(int size) {
            return new Garden[size];
        }
    };

    @Override
    public String toString() {
        return "Garden #" + id + " locality: " + locality;
    }
}
