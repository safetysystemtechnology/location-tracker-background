package br.com.safety.locationlistenerhelper.core;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.kayvannj.permission_utils.Func2;
import com.github.kayvannj.permission_utils.PermissionUtil;

/**
 * @author netodevel
 */
public class LocationTracker {

    private LocationService locationService;

    private PermissionUtil.PermissionRequestObject mBothPermissionRequest;

    private long interval = 0;

    private String actionReceiver;

    private Boolean gps;

    private Boolean netWork;

    private AppPreferences appPreferences;

    public LocationTracker(String actionReceiver) {
        this.actionReceiver = actionReceiver;
    }

    public LocationTracker setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    public LocationTracker setGps(Boolean gps) {
        this.gps = gps;
        return this;
    }

    public LocationTracker setNetWork(Boolean netWork) {
        this.netWork = netWork;
        return this;
    }

    public LocationTracker start(Context context, AppCompatActivity appCompatActivity) {
        validatePermissions(context, appCompatActivity);
        return this;
    }

    private void startLocationService(Context context, AppCompatActivity appCompatActivity) {
        Intent serviceIntent = new Intent(context, LocationService.class);
        saveSettingsInLocalStorage(context);
        context.startService(serviceIntent);
    }

    public void validatePermissions(Context context, AppCompatActivity appCompatActivity) {
        if (AppUtils.hasM() && !(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            askPermissions(context, appCompatActivity);
        } else {
             startLocationService(context, appCompatActivity);
        }
    }

    public void askPermissions(final Context context, final AppCompatActivity appCompatActivity) {
        mBothPermissionRequest =
            PermissionUtil.with(appCompatActivity).request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).onResult(
                    new Func2() {
                        @Override
                        protected void call(int requestCode, String[] permissions, int[] grantResults) {
                            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                                startLocationService(context, appCompatActivity);
                            } else {
                                Toast.makeText(context, "Permission Deined", Toast.LENGTH_LONG).show();
                            }
                        }

                    }).ask(SettingsLocationTracker.PERMISSION_ACCESS_LOCATION_CODE);
    }

    public void onRequestPermission(int requestCode, String[] permissions, int[] grantResults) {
        if (null != mBothPermissionRequest) {
            mBothPermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void saveSettingsInLocalStorage(Context context) {
        this.appPreferences = new AppPreferences(context);
        if (this.interval != 0) { this.appPreferences.putLong("INTERVAL", this.interval); }
        this.appPreferences.putString("ACTION", this.actionReceiver);
        this.appPreferences.putBoolean("GPS", this.gps);
        this.appPreferences.putBoolean("NETWORK", this.netWork);
    }

}
