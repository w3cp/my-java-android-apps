package com.anwar.rafiqul.utils;

import android.content.Context;
import android.widget.Toast;

public class Toast {
    public Toast() {
        super();
    } // end method Toast
    
    private Toast toast;
    private int duration = toast.LENGTH_SHORT;
    
    public void makeToast(Context context, CharSequence text) {
        toast = Toast.makeText(context, text, duration);
        toast.show();
    } // end method makeToast
} // end class Toast
