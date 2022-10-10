package com.vgroup.flexfit;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GoogleFitService extends IntentService implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    /**
     * @param intent
     * @deprecated
     */
    private static final int REQUEST_OAUTH = 1431;
    public static final String TAG = "GoogleFitService";
    private static final int SIGN_IN_REQUEST_CODE = 1001;
    private GoogleApiClient mGoogleApiFitnessClient=null;
    private boolean mTryingToConnect = false;

    public static final String SERVICE_REQUEST_TYPE = "requestType";
    public static final int TYPE_GET_STEP_TODAY_DATA = 1;
    public static final int TYPE_CALORIES_EXPENDED = 1;
    public static final int TYPE_DISTANCE_DELTA = 1;
    public static final int TYPE_REQUEST_CONNECTION = 2;

    public static final String HISTORY_INTENT = "fitHistory";
    public static final String HISTORY_EXTRA_STEPS_TODAY = "stepsToday";
    public static final String HISTORY_CALORIES = "calToday";
    public static final String HISTORY_DISTANCE = "distToday";
    public static final String FIT_NOTIFY_INTENT = "fitStatusUpdateIntent";
    public static final String FIT_EXTRA_CONNECTION_MESSAGE = "fitFirstConnection";
    public static final String FIT_EXTRA_NOTIFY_FAILED_STATUS_CODE = "fitExtraFailedStatusCode";
    public static final String FIT_EXTRA_NOTIFY_FAILED_INTENT = "fitExtraFailedIntent";

    private final FitnessOptions fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .build();
    @Override
    public void onDestroy() {
        Log.d(TAG, "GoogleFitService destroyed");
        if (mGoogleApiFitnessClient.isConnected()) {
            Log.d(TAG, "Disconecting Google Fit.");
            mGoogleApiFitnessClient.disconnect();
        }
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //buildFitnessClient();

        mGoogleApiFitnessClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                // Optional: specify more APIs used with additional calls to addApi
                .useDefaultAccount()
                .addScope(Fitness.SCOPE_ACTIVITY_READ_WRITE)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();

        mGoogleApiFitnessClient.connect();

    }

    /*private void buildFitnessClient() {
        // Create the Google API Client

        mGoogleApiFitnessClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_LOCATION_READ_WRITE))
                .enableAutoManage(this, 0, this)
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {

                            @Override
                            public void onConnected(Bundle bundle) {
                                Log.i(TAG, "Google Fit connected.");
                                mTryingToConnect = false;
                                Log.d(TAG, "Notifying the UI that we're connected.");
                                notifyUiFitConnected();

                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                mTryingToConnect = false;
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.i(TAG, "Google Fit Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.i(TAG, "Google Fit Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                )
                .addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            // Called whenever the API client fails to connect.
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                mTryingToConnect = false;
                                notifyUiFailedConnection(result);
                            }
                        }
                )
                .build();
    }*/

    public GoogleFitService() {
        super("GoogleFitService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//Get the request type
        int type = intent.getIntExtra(SERVICE_REQUEST_TYPE, -1);

        //block until google fit connects.  Give up after 10 seconds.
        if (!mGoogleApiFitnessClient.isConnected()) {
            mTryingToConnect = true;
            mGoogleApiFitnessClient.connect();

            //Wait until the service either connects or fails to connect
            while (mTryingToConnect) {
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (mGoogleApiFitnessClient.isConnected()) {
            if (type == TYPE_GET_STEP_TODAY_DATA|| type==TYPE_CALORIES_EXPENDED ||type== TYPE_DISTANCE_DELTA) {
                Log.d(TAG, "Requesting steps from Google Fit");
                getStepsToday();
                Log.d(TAG, "Fit update complete.  Allowing Android to destroy the service.");
            } else if (type == TYPE_REQUEST_CONNECTION) {
                //Don't need to do anything because the connection is already requested above
            }
        } else {
            //Not connected
            Log.w(TAG, "Fit wasn't able to connect, so the request failed.");
        }
    }
    private void getStepsToday() {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long startTime = cal.getTimeInMillis();

        final DataReadRequest readRequest = new DataReadRequest.Builder()
                .read(DataType.TYPE_STEP_COUNT_DELTA)
                .read(DataType.TYPE_CALORIES_EXPENDED)
                .read(DataType.TYPE_DISTANCE_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .bucketByTime(1, TimeUnit.DAYS)
                .build();

        DataReadResult dataReadResult =
                Fitness.HistoryApi.readData(mGoogleApiFitnessClient, readRequest).await(1, TimeUnit.MINUTES);

        DataSet stepData = dataReadResult.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);
        DataSet calData=dataReadResult.getDataSet(DataType.TYPE_CALORIES_EXPENDED);
        DataSet distData=dataReadResult.getDataSet(DataType.TYPE_DISTANCE_DELTA);

        int totalSteps = 0;
        float totalCal =0,totalDist=0;

        for (DataPoint dp : stepData.getDataPoints()) {
            for(Field field : dp.getDataType().getFields()) {
                int steps = dp.getValue(field).asInt();

                totalSteps += steps;

            }
        }
        for (DataPoint dp1 : calData.getDataPoints()) {
            for(Field field : dp1.getDataType().getFields()) {
                float calorie = dp1.getValue(field).asFloat();

                totalCal += calorie;

            }
        }
        for (DataPoint dp2 : distData.getDataPoints()) {
            for(Field field : dp2.getDataType().getFields()) {
                float dist = dp2.getValue(field).asFloat();

                totalDist += dist;

            }
        }
        Intent intent= new Intent(HISTORY_INTENT);
        // You can also include some extra data.
        intent.putExtra(HISTORY_EXTRA_STEPS_TODAY, totalSteps);
        intent.putExtra(HISTORY_CALORIES,totalCal);
        intent.putExtra(HISTORY_DISTANCE,totalDist);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        //publishTodaysData(totalSteps,totalCal,totalDist);
    }

    private void publishTodaysData(int totalSteps,float totalCal,float totalDist) {
        Intent intent= new Intent(HISTORY_INTENT);
        // You can also include some extra data.
        intent.putExtra(HISTORY_EXTRA_STEPS_TODAY, totalSteps);
        intent.putExtra(HISTORY_CALORIES,totalCal);
        intent.putExtra(HISTORY_DISTANCE,totalDist);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    private void notifyUiFitConnected() {
        Intent intent = new Intent(FIT_NOTIFY_INTENT);
        intent.putExtra(FIT_EXTRA_CONNECTION_MESSAGE, FIT_EXTRA_CONNECTION_MESSAGE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void notifyUiFailedConnection(ConnectionResult result) {
        Intent intent = new Intent(FIT_NOTIFY_INTENT);
        intent.putExtra(FIT_EXTRA_NOTIFY_FAILED_STATUS_CODE, result.getErrorCode());
        intent.putExtra(FIT_EXTRA_NOTIFY_FAILED_INTENT, result.getResolution());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
// Connected
        Log.i(TAG, "Google Fit connected.");

        mTryingToConnect = false;
        Log.d(TAG, "Notifying the UI that we're connected.");
        notifyUiFitConnected();
    }

    @Override
    public void onConnectionSuspended(int i) {
// you'll be able to determine the reason and react to it here.
        mTryingToConnect = false;
        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
            Log.i(TAG, "Google Fit Connection lost.  Cause: Network Lost.");
        } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
            Log.i(TAG, "Google Fit Connection lost.  Reason: Service Disconnected");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        mTryingToConnect = false;
        notifyUiFailedConnection(connectionResult);

    }
}
