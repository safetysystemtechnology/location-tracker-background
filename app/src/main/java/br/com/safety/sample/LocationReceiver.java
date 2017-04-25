package br.com.safety.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.safety.locationlistenerhelper.core.ILocationConstants;

/**
 * @author josevieira
 */
public class LocationReceiver extends BroadcastReceiver implements ILocationConstants {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("my.action")) {
            String locationData = intent.getStringExtra(LOCATION_MESSAGE);
            Log.d("TEST DATA: ", locationData);
        }
    }

}