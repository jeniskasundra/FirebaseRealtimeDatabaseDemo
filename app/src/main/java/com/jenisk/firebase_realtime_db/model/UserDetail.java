package com.jenisk.firebase_realtime_db.model;

public class UserDetail {

    String id, name, number, email, latitude, longitude;


    public UserDetail() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public UserDetail(String id, String name, String number, String email, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
