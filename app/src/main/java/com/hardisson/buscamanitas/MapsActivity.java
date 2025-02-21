package com.hardisson.buscamanitas;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //Add a marker in Santa Cruz de Tenerife and move the camera
        LatLng santaCruzTenerife = new LatLng(28.467099, -16.247067);
        marker = mMap.addMarker(new MarkerOptions().position(santaCruzTenerife).title("Marker in Santa Cruz de Tenerife"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(santaCruzTenerife));

        mMap.setOnMapClickListener(this);

    }


    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this, "Has hecho click en: "+latLng.latitude+","+latLng.longitude, Toast.LENGTH_SHORT).show();
        marker.setPosition(latLng);  // mueve el marcador a la nueva posición
    }
}