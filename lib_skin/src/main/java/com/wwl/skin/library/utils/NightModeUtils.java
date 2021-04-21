package com.wwl.skin.library.utils;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import com.wwl.skin.library.SkinActivity;

public class NightModeUtils {
    public static void changeNightMode(SkinActivity context) {
        int uiMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                context.setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                PreferencesUtils.putBoolean(context, "isNight", true);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                context.setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                PreferencesUtils.putBoolean(context, "isNight", false);
                break;
            default:
                break;
        }
    }
}
