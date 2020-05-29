package com.anwar.rafiqul.utils;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;

import android.content.Context;

public class Ringtone {
    public Ringtone() {
        super();
    } // end method Ringtone 
    
    public void playNotification(Context context) {
        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), ringtone);
        r.play();
    } // end method playSound
} // end class Ringtone
