package com.fun_corp.umamappsv10;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.fun_corp.umamappsv10.GPSTracker;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lokasi extends Fragment implements OnMapReadyCallback {
    String[] posAnak;
    double latAnak = -5.378507;
    double lngAnak = 105.277102;
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    GPSTracker gps;
    Location location;
    private ProgressDialog progressDialog;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;
    Firebase bacadata;
    public Lokasi() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markerPoints = new ArrayList<LatLng>();
        bacadata = new Firebase("https://scms-awesome.firebaseio.com/Data");

        latAnak = -5.378507;
        lngAnak = 105.277102;

        try {
            bacadata.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        posAnak = dataSnapshot.child("LatLng").getValue().toString().split(",");
                        latAnak = Double.parseDouble(posAnak[0]);
                        lngAnak = Double.parseDouble(posAnak[1]);
                    }
                    catch(Exception e){
//                        latAnak = -5.378507;
//                        lngAnak = 105.277102;
                    }

//                    markerPoints.add(new LatLng(latAnak,lngAnak));
//                    mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(latAnak, lngAnak)).title("Lokasi Anak").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//                    LatLng origin = markerPoints.get(0);
//                    LatLng dest = markerPoints.get(1);
//
//                    // Getting URL to the Google Directions API
//                    String url = getDirectionsUrl(origin, dest);
//
//                    DownloadTask downloadTask = new DownloadTask();
//
//                    // Start downloading json data from Google Directions API
//                    downloadTask.execute(url);
//
//                    CameraPosition Anak = CameraPosition.builder().target(new LatLng(latAnak, lngAnak)).zoom(16).bearing(0).tilt(45).build();
//
//                    mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Anak));
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(),"Proses pengambilan data",Toast.LENGTH_SHORT).show();
        }


//        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng point) {
//
//                // Already two locations
//                if (markerPoints.size() > 1) {
//                    markerPoints.clear();
//                    mGoogleMap.clear();
//                }
//
//                // Adding new item to the ArrayList
//                markerPoints.add(point);
//
//                // Creating MarkerOptions
//                MarkerOptions options = new MarkerOptions();
//
//                // Setting the position of the marker
//                options.position(point);
//
//                /**
//                 * For the start location, the color of marker is GREEN and
//                 * for the end location, the color of marker is RED.
//                 */
//                if (markerPoints.size() == 1) {
//                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//                } else if (markerPoints.size() == 2) {
//                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                }
//
//                // Add new marker to the Google Map Android API V2
//                mGoogleMap.addMarker(options);
//
//                // Checks, whether start and end locations are captured
//                if (markerPoints.size() >= 2) {
//                    LatLng origin = markerPoints.get(0);
//                    LatLng dest = markerPoints.get(1);
//
//                    // Getting URL to the Google Directions API
//                    String url = getDirectionsUrl(origin, dest);
//
//                    DownloadTask downloadTask = new DownloadTask();
//
//                    // Start downloading json data from Google Directions API
//                    downloadTask.execute(url);
//                }
//            }
//        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        // inflat and return the layout
//        View v = inflater.inflate(R.layout.fragment_lokasi, container,
//                false);
//        mMapView = (MapView) v.findViewById(R.id.map);
//        mMapView.onCreate(savedInstanceState);
//
//        mMapView.onResume();// needed to get the map to display immediately
//
//        try {
//            MapsInitializer.initialize(getActivity().getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return mView;
//        }
//        mMapView.getMapAsync(this);
//        mGoogleMap.setMyLocationEnabled(true);
//        // latitude and longitude
//        LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
//        /*double latitude = 17.385044;
//        double longitude = 78.486671;*/
//
//        // create marker
//        MarkerOptions marker = new MarkerOptions().position(
//                lat).title("Hello Maps");
//
//        // Changing marker icon
//        marker.icon(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
//
//        // adding marker
//        mGoogleMap.addMarker(marker);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(lat).zoom(12).build();
//        mGoogleMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));
//
//        // Perform any camera updates here
//        return v;
        mView = inflater.inflate(R.layout.fragment_lokasi, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        try {
            MapsInitializer.initialize(getContext());

            gps = new GPSTracker(Lokasi.this.getContext());

            if (gps.canGetLocation()) {
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                markerPoints.add(new LatLng(latitude, longitude));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(("Lokasi Anda")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_pin_circle_black_24dp)));
            } else {
                gps.showSettingsAlert();
            }

            mGoogleMap = googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //ini ngambil dari firebase

            bacadata = new Firebase("https://scms-awesome.firebaseio.com/Data");

//            latAnak = -5.378507;
//            lngAnak = 105.277102;

            try {
                bacadata.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        try {
                            markerPoints.clear();
                            googleMap.clear();
                            posAnak = dataSnapshot.child("LatLng").getValue().toString().split(",");
                            latAnak = Double.parseDouble(posAnak[0]);
                            lngAnak = Double.parseDouble(posAnak[1]);
                        }
                        catch(Exception e){
                            markerPoints.clear();
                            googleMap.clear();
                            latAnak = -6.973199;
                            lngAnak = 107.632713;
                        }
                        markerPoints.clear();
                        googleMap.clear();
                        if (gps.canGetLocation()) {
                            double latitude = gps.getLatitude();
                            double longitude = gps.getLongitude();
                            markerPoints.add(new LatLng(latitude, longitude));
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(("Lokasi Anda")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_pin_circle_black_24dp)));
                        } else {
                            gps.showSettingsAlert();
                            markerPoints.add(new LatLng(-6.973199, 107.632713));
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(-6.973199, 107.632713)).title(("Lokasi Anda")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_pin_circle_black_24dp)));
                        }
                    markerPoints.add(new LatLng(latAnak,lngAnak));
                    mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(latAnak, lngAnak)).title("Lokasi Anak").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_car_black_24dp)));

                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);

                    CameraPosition Anak = CameraPosition.builder().target(new LatLng(latAnak, lngAnak)).zoom(16).bearing(0).tilt(45).build();

                    mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Anak));
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }catch (Exception e){
                Toast.makeText(getActivity(),"Proses pengambilan data",Toast.LENGTH_SHORT).show();
            }

            markerPoints.add(new LatLng(latAnak, lngAnak));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(latAnak, lngAnak)).title("Lokasi Anak").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_car_black_24dp)));

            LatLng origin = markerPoints.get(0);
            LatLng dest = markerPoints.get(1);

            // Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);

           // Toast.makeText(getActivity(),"origin nya "+origin+" tujuan nya "+dest,Toast.LENGTH_SHORT).show();
            DownloadTask downloadTask = new DownloadTask();

            // Start downloading json data from Google Directions API
            downloadTask.execute(url);

            CameraPosition Anak = CameraPosition.builder().target(new LatLng(latAnak, lngAnak)).zoom(16).bearing(0).tilt(45).build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Anak));
        }
        catch(Exception e){

        }

        FloatingActionButton findLocation =(FloatingActionButton)mView.findViewById(R.id.btn_lokasi_anda);
        findLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    float zoomLevel = 16;
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),zoomLevel));
                }

            }
        });

        FloatingActionButton lokasiAnak =(FloatingActionButton)mView.findViewById(R.id.btn_lokasi_anak);
        lokasiAnak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                markerPoints.add(new LatLng(latAnak,lngAnak));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latAnak, lngAnak)).title("Lokasi Anak").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_car_black_24dp)));

                LatLng origin = markerPoints.get(0);
                LatLng dest = markerPoints.get(1);

                // Getting URL to the Google Directions API
                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);

                CameraPosition Anak = CameraPosition.builder().target(new LatLng(latAnak, lngAnak)).zoom(16).bearing(0).tilt(45).build();

                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Anak));

            }
        });

        FloatingActionButton refresh =(FloatingActionButton)mView.findViewById(R.id.btn_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                markerPoints.clear();
                googleMap.clear();
                if(gps.canGetLocation()){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    markerPoints.add(new LatLng(latitude,longitude));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(("Lokasi Anda")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_pin_circle_black_24dp)));
                }
                else {

                    markerPoints.add(new LatLng(-6.236945, 106.827257));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(-6.236945, 106.827257)).title(("Lokasi Anda")).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_person_pin_circle_black_24dp)));
                }

                markerPoints.add(new LatLng(latAnak,lngAnak));
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latAnak, lngAnak)).title("Lokasi Anak").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_car_black_24dp)));


                LatLng origin = markerPoints.get(0);
                LatLng dest = markerPoints.get(1);

                // Getting URL to the Google Directions API
                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();

                // Start downloading json data from Google Directions API
                downloadTask.execute(url);

                CameraPosition Anak = CameraPosition.builder().target(new LatLng(latAnak, lngAnak)).zoom(16).bearing(0).tilt(45).build();

                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Anak));

            }
        });
    }

//    @Override
//    public void onDirectionFinderStart() {
//        progressDialog = ProgressDialog.show(this, "Please wait.",
//                "Mencari Rute...", true);
//
//        if (originMarkers != null) {
//            for (Marker marker : originMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (destinationMarkers != null) {
//            for (Marker marker : destinationMarkers) {
//                marker.remove();
//            }
//        }
//
//        if (polylinePaths != null) {
//            for (Polyline polyline : polylinePaths) {
//                polyline.remove();
//            }
//        }
//    }
//
//    @Override
//    public void onDirectionFinderSuccess(List<Route> routes) {
//        progressDialog.dismiss();
//        polylinePaths = new ArrayList<>();
//        originMarkers = new ArrayList<>();
//        destinationMarkers = new ArrayList<>();
//
//        for (Route route : routes) {
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);
//
//            originMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.lokasipemadamxcoba))
//                    .title(route.startAddress)
//                    .position(route.startLocation)));
//            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.loaksiapixcoba))
//                    .title(route.endAddress)
//                    .position(route.endLocation)));
//
//            PolylineOptions polylineOptions = new PolylineOptions().
//                    geodesic(true).
//                    color(Color.BLUE).
//                    width(10);
//
//            polylineOptions.add(route.startLocation);
//            for (int i = 0; i < route.points.size(); i++)
//                polylineOptions.add(route.points.get(i));
//            polylineOptions.add(route.endLocation);
//
//            polylinePaths.add(mMap.addPolyline(polylineOptions));
//        }
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        Log.d("URL", url);
        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(Lokasi.this.getContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.BLUE);
            }
            Toast.makeText(getActivity()," Jarak "+distance+" Durasi "+duration+" ",Toast.LENGTH_SHORT).show();
            //tvDistanceDuration = (TextView) mView.findViewById(R.id.textView);
            //tvDistanceDuration.setText("Distance:" + distance + ", Duration:" + duration);

            // Drawing polyline in the Google Map for the i-th route
            mGoogleMap.addPolyline(lineOptions);
        }
    }

}