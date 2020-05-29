package com.anwar.text2binary.utils;

import android.os.Vibrator;

import android.content.Context;

public class Vibrator {
    public Vibrator() {
        super();
    } // end method Vibrator
    
    public void vibrate(Context context, int milliseconds) {
        // Get instance of Vibrator from current Context
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 'milliseconds' milliseconds
        vibrator.vibrate(milliseconds);
    } // end method vibrate
} // end class Vibrator
