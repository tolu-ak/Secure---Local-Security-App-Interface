package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Button home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add markers with descriptions and move the camera
        LatLng kelowna = new LatLng(49.8801, -119.4436);
        LatLng ubco = new LatLng(49.9394, -119.3948);
        LatLng lakeCountry = new LatLng(50.0537, -119.4106);

        mMap.addMarker(new MarkerOptions()
                .position(kelowna)
                .title("House Break-In")
                .snippet("A burglary was reported at a residential area in Kelowna."));
        mMap.addMarker(new MarkerOptions()
                .position(ubco)
                .title("8 Car Pileup on HW97")
                .snippet("A major car accident involving 8 vehicles on Highway 97 near UBCO."));
        mMap.addMarker(new MarkerOptions()
                .position(lakeCountry)
                .title("Out of Control Housefire")
                .snippet("An intense housefire in Lake Country has caused significant damage."));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kelowna, 10));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        home = findViewById(R.id.mapHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}