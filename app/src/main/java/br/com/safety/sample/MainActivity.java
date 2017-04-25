package br.com.safety.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.safety.locationlistenerhelper.core.ILocationConstants;
import br.com.safety.locationlistenerhelper.core.LocationHelper;

public class MainActivity extends AppCompatActivity implements ILocationConstants {

    private LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationHelper = new LocationHelper();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationHelper.start(getBaseContext(), this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        locationHelper.onRequestPermission(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
