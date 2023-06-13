package com.alexbc.taskmaster;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.analytics.pinpoint.AWSPinpointAnalyticsPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.predictions.aws.AWSPredictionsPlugin;

public class TaskmasterApplication extends Application {
    public static final String TAG = "taskmaster_application_tag";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSPredictionsPlugin());
            Amplify.addPlugin(new AWSPinpointAnalyticsPlugin());
            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e(TAG, "Error during initialization of Amplify: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }
}