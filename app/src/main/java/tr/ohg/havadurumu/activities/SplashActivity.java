package tr.ohg.havadurumu.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import tr.ohg.havadurumu.R;
import tr.ohg.havadurumu.notifications.WeatherNotificationService;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showWeatherNotificationIfNeeded();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showWeatherNotificationIfNeeded() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs == null)
            return;

        // we should check permission here because user can update Android version between app launches
        boolean foregroundServicesPermissionGranted = isForegroundServicesPermissionGranted();
        boolean isWeatherNotificationEnabled =
                prefs.getBoolean(getString(R.string.settings_enable_notification_key), false);
        if (isWeatherNotificationEnabled && foregroundServicesPermissionGranted) {
            WeatherNotificationService.start(this);
        }
    }

    private boolean isForegroundServicesPermissionGranted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P)
            return true;    // There is no need for this permission prior Android Pie (Android SDK 28)
        return ContextCompat.checkSelfPermission(this, Manifest.permission.FOREGROUND_SERVICE)
                == PackageManager.PERMISSION_GRANTED;
    }
}
