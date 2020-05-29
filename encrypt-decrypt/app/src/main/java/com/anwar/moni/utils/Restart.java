package com.anwar.moni.utils;

import android.content.Intent;
import android.content.Context;
import android.app.*;

public class Restart {
    public void restartApplication(Context context, Activity activity) {
        Intent intent = activity.getBaseContext().getPackageManager()
            .getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
} // end class Restart
