package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;
import com.artie.gourmand.dao.ProfilePostItemDao;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private static String INTENT_EXTRA_LATITUDE = "latitude";
    private static String INTENT_EXTRA_LONGITUDE = "longitude";
    private static String INTENT_EXTRA_LOCATION_NAME = "locationName";
    private static String INTENT_EXTRA_LATITUDE_LIST = "latitudes";
    private static String INTENT_EXTRA_LONGITUDE_LIST = "longitudes";
    private static String INTENT_EXTRA_LOCATION_NAME_LIST = "locationNames";
    private static String INTENT_EXTRA_IS_MULTIPLE_MARKER = "isMultipleMarker";

    private GoogleMap mMap;
    private LatLng mLocation;
    private String mLocationeName;
    private List<MarkerOptions> mMarkers = new ArrayList<>();
    private boolean isMultipleMarker;

    public static Intent getStartIntent(Context context, double latitude, double longitude, String locationName) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(INTENT_EXTRA_LATITUDE, latitude);
        intent.putExtra(INTENT_EXTRA_LONGITUDE, longitude);
        intent.putExtra(INTENT_EXTRA_LOCATION_NAME, locationName);
        return intent;
    }

    public static Intent getStartIntent(Context context, List<ProfilePostItemDao> posts) {
        Intent intent = new Intent(context, MapActivity.class);
        double[] latitudes = new double[posts.size()];
        double[] longitudes = new double[posts.size()];
        String[] locationNames = new String[posts.size()];

        for (int i=0; i<posts.size(); i++) {
            latitudes[i] = posts.get(i).getLatitude();
            longitudes[i] = posts.get(i).getLongitude();
            locationNames[i] = posts.get(i).getLocationName();
        }

        intent.putExtra(INTENT_EXTRA_LATITUDE_LIST, latitudes);
        intent.putExtra(INTENT_EXTRA_LONGITUDE_LIST, longitudes);
        intent.putExtra(INTENT_EXTRA_LOCATION_NAME_LIST, locationNames);
        intent.putExtra(INTENT_EXTRA_IS_MULTIPLE_MARKER, true);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        isMultipleMarker = intent.getBooleanExtra(INTENT_EXTRA_IS_MULTIPLE_MARKER, false);
        if (isMultipleMarker) {
            double[] latitudes = intent.getDoubleArrayExtra(INTENT_EXTRA_LATITUDE_LIST);
            double[] longitudes = intent.getDoubleArrayExtra(INTENT_EXTRA_LONGITUDE_LIST);
            String[] locationNames = intent.getStringArrayExtra(INTENT_EXTRA_LOCATION_NAME_LIST);
            for (int i=0; i<locationNames.length; i++) {
                mMarkers.add(new MarkerOptions()
                        .title(locationNames[i])
                        .position(new LatLng(latitudes[i], longitudes[i])));
            }
        } else {
            mLocation = new LatLng(intent.getDoubleExtra(INTENT_EXTRA_LATITUDE, 0),
                    intent.getDoubleExtra(INTENT_EXTRA_LONGITUDE, 0));
            mLocationeName = intent.getStringExtra(INTENT_EXTRA_LOCATION_NAME);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (isMultipleMarker) {
            addMarkersToMap(mMarkers);
        } else {
            mMap.addMarker(new MarkerOptions().position(mLocation).title(mLocationeName));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocation , 14.0f));
        }
    }

    private void addMarkersToMap(List<MarkerOptions> markerOptionsList) {
        List<Marker> markers = new ArrayList<>();
        for (MarkerOptions markerOptions : markerOptionsList) {
            markers.add(mMap.addMarker(markerOptions));
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        mMap.moveCamera(cu);
    }

}
