package br.com.safety.locationlistenerhelper.core;

import android.location.Location;

/**
 * @author netodevel
 */
public interface CurrentLocationListener {

    /**
     * get current location
     */
    void onCurrentLocation(Location location);

    /**
     * Permission deined
     */
    void onPermissionDiened();

}
