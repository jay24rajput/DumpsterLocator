package com.example.jay.dumpsterlocator;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener listener;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String provider;
    //TextView question=(TextView)findViewById(R.id.question_text_view);
    //Button yesbutton=(Button)findViewById(R.id.yes_button);
    //Button nobutton=(Button)findViewById(R.id.no_button);
    //Button reanswerbutton=(Button)findViewById(R.id.reanswer_button);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setDustbin(View view)
    {
        TextView question=(TextView)findViewById(R.id.question_text_view);
        Button yesbutton=(Button)findViewById(R.id.yes_button);
        Button nobutton=(Button)findViewById(R.id.no_button);
        Button reanswerbutton=(Button)findViewById(R.id.reanswer_button);
        LatLng dustbinLocation=getDustbinLocation();
        Marker marker = mMap.addMarker(new MarkerOptions().position(dustbinLocation).title("Dustbin Located Successfully"));
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dustbinLocation, 15f));
        //TextView question=(TextView)findViewById(R.id.question_text_view);
        question.setText("Thanks! Your response has been recorded");
        yesbutton.setVisibility(View.GONE);
        nobutton.setVisibility(View.GONE);
        reanswerbutton.setVisibility(View.VISIBLE);
    }

    public void removeDustbin(View view)
    {
        TextView question=(TextView)findViewById(R.id.question_text_view);
        Button yesbutton=(Button)findViewById(R.id.yes_button);
        Button nobutton=(Button)findViewById(R.id.no_button);
        Button reanswerbutton=(Button)findViewById(R.id.reanswer_button);
        //Code to remove the dustbin at that location
        
        question.setText("Thanks! Your response has been recorded");
        yesbutton.setVisibility(View.GONE);
        nobutton.setVisibility(View.GONE);
        reanswerbutton.setVisibility(View.VISIBLE);
    }

    public void reAnswer(View View)
    {
        TextView question=(TextView)findViewById(R.id.question_text_view);
        Button yesbutton=(Button)findViewById(R.id.yes_button);
        Button nobutton=(Button)findViewById(R.id.no_button);
        Button reanswerbutton=(Button)findViewById(R.id.reanswer_button);
        question.setText("Is there a dustbin at your location?");
        yesbutton.setVisibility(View.VISIBLE);
        nobutton.setVisibility(View.VISIBLE);
        reanswerbutton.setVisibility(View.GONE);
    }

    public LatLng getDustbinLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            locationManager.removeUpdates((LocationListener) this);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng myloc=new LatLng(latitude,longitude);
        return myloc;
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

        //Add a marker in Sydney and move the camera
         //LatLng sydney = new LatLng(-34, 151);
        //LatLng sydney = new LatLng(-31, 151);
       // mMap.(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates((LocationListener) this);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        /*LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng myloc=new LatLng(latitude,longitude);*/
        LatLng dustbin=getDustbinLocation();
        Marker marker = mMap.addMarker(new MarkerOptions().position(dustbin).title("Dustbin Located Successfully"));
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dustbin, 15f));
    }
}

