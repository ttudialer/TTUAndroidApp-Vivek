package com.kabaladigital.tingtingu;

import android.app.Application;

import com.google.firebase.crashlytics.FirebaseCrashlytics;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);

        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                    //Do nothing
                }
            });
        }
    }
}
