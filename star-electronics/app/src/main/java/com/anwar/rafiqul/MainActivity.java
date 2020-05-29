package com.anwar.rafiqul;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import android.content.Context;

// key event
import android.view.KeyEvent;

// text
import android.widget.TextView;
import android.text.Html;
import android.text.method.LinkMovementMethod;

// utils
import com.anwar.rafiqul.utils.Toast;
import com.anwar.rafiqul.utils.Ringtone;
import com.anwar.rafiqul.utils.Vibrator;
import com.anwar.rafiqul.utils.Camera;
import com.anwar.rafiqul.utils.Restart;
import com.anwar.rafiqul.utils.StringToInteger;
import com.anwar.rafiqul.utils.SharedPreference;
// import com.anwar.rafiqul.utils.;

public class MainActivity extends Activity {
    Activity context = this;
    
    NotificationHandler nHandler;
    
    private static final String ORIGINAL_FLAG = "1";
    private static final String UNREGISTERED_FLAG = "0";
    
    private String isOriginal;
    private int totalMatched;
    private TextView matchingResult;
    
    private Toast toast;
    private Ringtone ringtone;
    private Vibrator vibrator;
    private Camera camera;
    private Restart restart;
    private StringToInteger stringToInteger;
    private SharedPreference sharedPreference;
    
    //public int firstValue = 0;
    public int secondValue = 0;
    
    /*@Override
    protected void onPause() {
        super.onPause();

        //toast.makeToast(context, "Releasing Camera... Closing Application...Good Bye");
        if (camera != null) {
            //toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        }

        // close all notification
        nHandler.closeAll();
    } // close method onPause
    
    @Override
    protected void onResume() {
        super.onResume();

        //toast.makeToast(context, "Releasing Camera... Closing Application...Good Bye");
        if (camera != null) {
            toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        } else {
            camera.openCamera();
        }
        
        showNotification();
    } // close method onResume*/
    
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //toast.makeToast(context, "Releasing Camera... Closing Application...Good Bye");
        if (camera != null) {
            //toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        }
        
        // close all notification
        nHandler.closeAll();
    } // close method onDestroy
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // from utils
        toast = new Toast();
        ringtone = new Ringtone();
        vibrator = new Vibrator();
        camera = new Camera();
        restart = new Restart();
        stringToInteger = new StringToInteger();
        sharedPreference = new SharedPreference();
        
        nHandler = NotificationHandler.getInstance(this);
        
        // check whether app is registered or not
        isOriginal = ORIGINAL_FLAG;
        checkRegistration();
        
        // open Camera
        toast.makeToast(context, "Opening Camera...");
        camera.openCamera();
        // show notification
        showNotification();
        
        // html info
        TextView textViewHtml = (TextView) findViewById(R.id.contactHtml);
        textViewHtml.setText(
            Html.fromHtml(
                "<b><a href=\"http://www.github.com/w3cp\">github.com/w3cp</a></b>"
                + "  <a href=\"tel:+8801768310499\">Call Me</a> <br />"));
        textViewHtml.setMovementMethod(LinkMovementMethod.getInstance());
        
        matchingResult = (TextView) findViewById(R.id.total_matched);
        totalMatched = 0;
        showMatching();
        
        //setNumber();
    } // end method onCreate
    
    /* Controll Volume Up/Down key */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) {
                    add1ByVolumeUp();
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) {
                    volumeDown();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    } // end method dispatchKeyEvent
    
    public int firstNumber() {
        EditText firstValue = (EditText) findViewById(R.id.first_value);
        String strNum1 = firstValue.getText().toString();
        int num1 = 0;
        num1 = stringToInteger.parseInt(strNum1);
        return num1;
    } // end method firstNumber
    
    // increment second value by 1
    public void add1() {
        // check registration
        if (isOriginal == UNREGISTERED_FLAG) {
            login();
        }
        
        int firstValue = firstNumber();
        
        secondValue = secondValue + 1;
        //String strOfSecondValue = Integer.toString(secondValue);
        String strOfSecondValue = String.valueOf(secondValue);
        
        EditText secondValue2 = (EditText) findViewById(R.id.second_value);
        secondValue2.setText(strOfSecondValue);
        
        if (firstValue <= 0) {
            //makeToast("First Value isn't valid!!");
            toast.makeToast(context, "First Value is empty or invalid!");
            // assign the second value to 0 
            zero2();
        }
        
        if (secondValue == firstValue) {
            secondValue = 0;
            // count matching
            totalMatched++;
            showMatching();
            //vibrate
            vibrator.vibrate(context, 500);
            // Show toast
            //toast.makeToast(context, "Hi Rafiqul! How are you?");
            // turn flashlight on/of [even/odd]
            if (camera.flashlight()) {
                toast.makeToast(context, "Hi Rafiqul! Turning on Camera...Vibrating...");
            } else {
                toast.makeToast(context, "Hi Rafiqul! Turning of Camera...Vibrating...");
            }
        }
    } // end method add1
    
    /* Called when user clicks the 'Dialog' button */
    /*public void startDialog(View view) {
        Intent intent = new Intent(MainActivity.this, DialogActivity.class);
        startActivity(intent);
    }*/
    
    /* Called when the first value is empty or invalid -
    assign the second value to 0 */
    public void zero2() {
        secondValue = 0;
        EditText secondValue2 = (EditText) findViewById(R.id.second_value);
        secondValue2.setText("0");
        ringtone.playNotification(context);
    } // end method zero2
    
    /* Called when user clicks the 'Add 1' button */
    public void add1ByButton(View view) {
        add1();
    } // end method add1ByButton
    
    /* Called when user clicks the 'Zero' button */
    public void zero(View view) {
        secondValue = 0;
        //String strOfSecondValue = String.valueOf(secondValue);
        EditText secondValue2 = (EditText) findViewById(R.id.second_value);
        secondValue2.setText("0");
        toast.makeToast(context, "Second Value is assigned to Zero");
        ringtone.playNotification(context);
    } // end method zero
    
    /* Called when user clicks the 'Restart' button */
    public void restart(View view) {
        if (camera != null) {
            //toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        }
        nHandler.closeAll();
        
        restart.restartApplication(context, this);
    } // end method restart
    
    // increment secondValue by 1 when volume up key is pressed
    public void add1ByVolumeUp() {
        add1();
    } // end method add1ByVolumeUp
    
    // Called when user clicks the volume down button
    public void volumeDown() {
        toast.makeToast(context, "Hey Bro! What's up? There is nothing to do!");
        ringtone.playNotification(context);
    } // end method volumeDown
    
    public void login() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    } // end method login
    
    public void checkRegistration() {
        try {
            isOriginal = sharedPreference.getValue(context).toString();
        } catch (Exception e) {
            isOriginal = UNREGISTERED_FLAG;
        }
        //toast.makeToast(context, isOriginal);
        if (isOriginal == UNREGISTERED_FLAG) {
            login();
        }
    } // end method checkRegistration
    
    private void showMatching() {
        matchingResult.setText( "Total matched: " + String.valueOf(totalMatched) + " times" );
    } // end method showMatching
    
    public void showNotification() {
        nHandler.createSimpleNotification(this);
        //nHandler.createExpandableNotification(this);
    } // end method showNotification
    
    public void exit(View view) {
        //toast.makeToast(context, "Releasing Camera... Closing Application...Good Bye");
        if (camera != null) {
            //toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        }

        // close all notification
        nHandler.closeAll();
        
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    } // end method exit
    
} // end class MainActivity
