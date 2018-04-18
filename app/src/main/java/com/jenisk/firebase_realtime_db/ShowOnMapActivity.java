package com.jenisk.firebase_realtime_db;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jenisk.firebase_realtime_db.model.UserDetail;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ShowOnMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    double latitude,longitude;
    String userName;
    ArrayList<UserDetail> arrayList=MainActivity.userDetailsList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initMap();
    }

    private void initMap() {
        if(!MainActivity.isAllShowOnMap) {
            Intent intent = getIntent();
            latitude = Double.parseDouble(intent.getStringExtra("latitude"));
            longitude = Double.parseDouble(intent.getStringExtra("longitude"));
            userName = intent.getStringExtra("username");
        }

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(MainActivity.isAllShowOnMap) {
            for(int i = 0; i < arrayList.size(); i++) {
                LatLng latLng = new LatLng(Double.parseDouble(arrayList.get(i).getLatitude()),Double.parseDouble(arrayList.get(i).getLongitude()));
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.addMarker(new MarkerOptions().position(latLng).title(arrayList.get(i).getName()));
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        }
        else
        {
            LatLng latLng = new LatLng(latitude, longitude);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            googleMap.addMarker(new MarkerOptions().position(latLng).title(userName));
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }

    }
}
