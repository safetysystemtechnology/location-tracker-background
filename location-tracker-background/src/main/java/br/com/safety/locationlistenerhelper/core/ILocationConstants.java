package br.com.safety.locationlistenerhelper.core;

public interface ILocationConstants {

    long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    int ACCURACY_THRESHOLD = 100;

    long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    String LOACTION_ACTION = "br.com.safety.sample.LOCATION_ACTION";

    String LOCATION_MESSAGE = "br.com.safety.sample.LOCATION_DATA";

    int PERMISSION_ACCESS_LOCATION_CODE = 99;

}
