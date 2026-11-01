package com.rydellfilm.festivalkompassen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;

/** Öppnar Festivalkompassen i Chrome utan adressfält, så inloggningen på claude.ai delas med Chrome. */
public class MainActivity extends Activity {
    static final String URL = "https://claude.ai/artifact/YKhAfUGeJXekVBEV3jWxh6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = Uri.parse(URL);
        try {
            CustomTabsIntent tab = new CustomTabsIntent.Builder()
                    .setDefaultColorSchemeParams(new CustomTabColorSchemeParams.Builder()
                            .setToolbarColor(Color.parseColor("#1F3A5F")).build())
                    .setShowTitle(true)
                    .setUrlBarHidingEnabled(true)
                    .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
                    .build();
            String pkg = CustomTabsClient.getPackageName(this, null);
            if (pkg != null) tab.intent.setPackage(pkg);
            tab.launchUrl(this, uri);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
        finish();
    }
}
