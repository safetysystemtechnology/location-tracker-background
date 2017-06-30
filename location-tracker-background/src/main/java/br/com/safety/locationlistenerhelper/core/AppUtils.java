package br.com.safety.locationlistenerhelper.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

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

    public static boolean isServiceRunning(Context context, Class serviceClass) {
        if (context != null) {
            Log.d("", "contextIsNotNull: ");
        }
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
