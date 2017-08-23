package org.celebino.celebinoapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henrique Martins on 14/06/2017.
 */

public class User implements Parcelable {
    private Long id;
    private String email;
    private String username;
    private String name;
    private String password;

    public User() {
    }

    public User(Long id, String email, String username, String name, String password) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", username=" + username + ", email=" + email + "]";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.password);
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
        this.username = in.readString();
        this.name = in.readString();
        this.password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

