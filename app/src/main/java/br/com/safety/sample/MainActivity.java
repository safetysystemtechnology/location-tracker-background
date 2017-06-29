package br.com.safety.sample;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.com.safety.locationlistenerhelper.core.CurrentLocationListener;
import br.com.safety.locationlistenerhelper.core.CurrentLocationReceiver;
import br.com.safety.locationlistenerhelper.core.LocationTracker;

public class MainActivity extends AppCompatActivity {

    private LocationTracker locationTracker;

    private Button buttonLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLocation = (Button) findViewById(R.id.btn_location);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationTracker = new LocationTracker("my.action")
                        .setInterval(1000)
                        .setGps(true)
                        .setNetWork(false)
                        .currentLocation(new CurrentLocationReceiver(new CurrentLocationListener() {

                            @Override
                            public void onCurrentLocation(Location location) {
                                Log.d("callback", ":onCurrentLocation" + location.getLongitude());
                                locationTracker.stopLocationService(getBaseContext());
                            }

                            @Override
                            public void onPermissionDiened() {
                                Log.d("callback", ":onPermissionDiened");
                                locationTracker.stopLocationService(getBaseContext());
                            }
                        })).start(getBaseContext(), MainActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationTracker.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationTracker.stopLocationService(this);
    }

}
