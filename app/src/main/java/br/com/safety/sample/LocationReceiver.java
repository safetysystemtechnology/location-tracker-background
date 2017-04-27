package br.com.safety.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import br.com.safety.locationlistenerhelper.core.SettingsLocationTracker;

/**
 * @author josevieira
 */
public class LocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = (Location) intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
            //send your call to api or do any things with the of location data
        }
    }

}