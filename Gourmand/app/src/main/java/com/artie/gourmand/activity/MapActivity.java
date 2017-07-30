package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private static String INTENT_EXTRA_LATITUDE = "latitude";
    private static String INTENT_EXTRA_LONGITUDE = "longitude";
    private static String INTENT_EXTRA_LOCATION_NAME = "locationName";

    private GoogleMap mMap;
    private LatLng mLocation;
    private String mLocationeName;

    public static Intent getStartIntent(Context context, double latitude, double longitude, String locationName) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(INTENT_EXTRA_LATITUDE, latitude);
        intent.putExtra(INTENT_EXTRA_LONGITUDE, longitude);
        intent.putExtra(INTENT_EXTRA_LOCATION_NAME, locationName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        mLocation = new LatLng(intent.getDoubleExtra(INTENT_EXTRA_LATITUDE, 0),
                intent.getDoubleExtra(INTENT_EXTRA_LONGITUDE, 0));
        mLocationeName = intent.getStringExtra(INTENT_EXTRA_LOCATION_NAME);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(mLocation).title(mLocationeName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocation , 14.0f));
    }

}
