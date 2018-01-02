![](https://jitpack.io/v/safetysystemtechnology/location-tracker-background.svg)



# location-tracker-background
Periodically tracking user's location in the background

## Install 
Add the dependecy

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  compile 'com.github.safetysystemtechnology:location-tracker-background:v1.3'
}

```

### Add permissions in your AndroidManifest.xml

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

### Register your receiver in AndroidManifest.xml

```xml
<receiver android:name=".LocationReceiver">
  <intent-filter>
      <action android:name="my.action"/>
  </intent-filter>
</receiver>

```

## Usage

```java
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
                
                // IF YOU WANT JUST CURRENT LOCATION
                // .currentLocation(new CurrentLocationReceiver(new CurrentLocationListener() {
                //
                //            @Override
                //            public void onCurrentLocation(Location location) {
                //               Log.d("callback", ":onCurrentLocation" + location.getLongitude());
                //               locationTracker.stopLocationService(getBaseContext());
                //            }
                //
                //            @Override
                //            public void onPermissionDiened() {
                //                Log.d("callback", ":onPermissionDiened");
                //                locationTracker.stopLocationService(getBaseContext());
                //            }
                // }))
                        
                .start(getBaseContext(), this);

                // IF YOU WANT RUN IN SERVICE
                // .start(this);
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
```

Create your `BroadcastReceiver` to get informations of location

```java
public class LocationReceiver extends BroadcastReceiver implements ILocationConstants {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && intent.getAction().equals("my.action")) {
            Location locationData = (Location) intent.getParcelableExtra(SettingsLocationTracker.LOCATION_MESSAGE);
            Log.d("Location: ", "Latitude: " + locationData.getLatitude() + "Longitude:" + locationData.getLongitude());
            //send your call to api or do any things with the of location data
        }
    }
}

```

## License

    The MIT License (MIT)

    Copyright (c) Safety System Technology

    Permission is hereby granted, free of charge, to any person obtaining a 
    copy of this software and associated documentation files (the "Software"), 
    to deal in the Software without restriction, including without limitation 
    the rights to use, copy, modify, merge, publish, distribute, sublicense, 
    and/or sell copies of the Software, and to permit persons to whom the Software is 
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included 
    in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
    PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
    ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
