package br.com.safety.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.safety.locationlistenerhelper.core.ILocationConstants;
import br.com.safety.locationlistenerhelper.core.LocationTracker;

public class MainActivity extends AppCompatActivity implements ILocationConstants {

    private LocationTracker locationTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new LocationTracker("my.action").setInterval(10000).start(getBaseContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        locationTracker.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
