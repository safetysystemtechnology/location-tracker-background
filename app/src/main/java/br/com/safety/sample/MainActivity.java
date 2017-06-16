package br.com.safety.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import br.com.safety.locationlistenerhelper.core.LocationTracker;

public class MainActivity extends AppCompatActivity {

    private LocationTracker locationTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationTracker=new LocationTracker("my.action")
                .setInterval(50000)
                .setGps(true)
                .setNetWork(false)
                .start(getBaseContext(), this);
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
