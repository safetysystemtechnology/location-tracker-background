package br.com.safety.locationlistenerhelper.core;

import android.os.Build;

/**
 * @author netodevel
 */
public class AppUtils {

    /**
     * @return true If device has Android Marshmallow or above version
     */
    public static boolean hasM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
