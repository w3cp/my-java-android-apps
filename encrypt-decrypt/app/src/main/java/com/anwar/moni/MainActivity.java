package com.anwar.moni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.TextView;
//text

import android.content.Context;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

//import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;

// utils
/*import com.anwar.moni.utils.Toast;
import com.anwar.moni.utils.Ringtone;
import com.anwar.moni.utils.Vibrator;
import com.anwar.moni.utils.Camera;
import com.anwar.moni.utils.Restart;
import com.anwar.moni.utils.StringToInteger;
import com.anwar.moni.utils.SharedPreference;*/
import com.anwar.moni.utils.*;

public class MainActivity extends Activity {
    Activity context = this;
    
    public final static String EXTRA_MESSAGE = "com.anwar.moni";
    
    //private TextView mTextView;
    
    private Toast toast;
    private Ringtone ringtone;
    private Vibrator vibrator;
    //private Camera camera;
    //private Restart restart;
    private StringToInteger stringToInteger;
    //private SharedPreference sharedPreference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // from utils
        toast = new Toast();
        ringtone = new Ringtone();
        vibrator = new Vibrator();
        //camera = new Camera();
        //restart = new Restart();
        stringToInteger = new StringToInteger();
        //sharedPreference = new SharedPreference();
        
        //initialize values
        
        //toast.makeToast(context, "Opening App...");
        //ringtone.playNotification(context);
        //vibrator.vibrate(context, 500);
        
        // html info
        TextView contactsHtml = findViewById(R.id.contactHtml);
        contactsHtml.setText(
            Html.fromHtml(
                "<b><a href=\"https://www.github.com/w3cp\">github.com/w3cp</a></b>"
                + "  <a href=\"tel:+8801768310499\">Call Me</a> <br />"));
        contactsHtml.setMovementMethod(LinkMovementMethod.getInstance());
        
        TextView facebookHtml = findViewById(R.id.facebook);
        facebookHtml.setText(
            Html.fromHtml(
                "<div><br /><h4 style='color:#A8DFF4;'>Anwar Hossain</h4>"
                + "<i><a href=\"https://web.facebook.com/anwar.ubuntu\">"
                + "facebook.com/anwar.ubuntu</a></i> <br />"
                + "<p>Shashail, Rasulpur [6540] <br />Patnitala, Naogaon <br />"
                + "Rajshahi, Bangladesh</p>"
                + "<p><b>Shibpur High School - <i>SSC 2014</i></b></p></div>"));
        facebookHtml.setMovementMethod(LinkMovementMethod.getInstance());
    } // end method onCreate
    
    private String binary(String type) {
        String output = new String();
        //StringBuilder binary = new StringBuilder();
        EditText editText = findViewById(R.id.edit_text);
        String text = editText.getText().toString();
        EditText editKey = findViewById(R.id.edit_key);
        String key = editKey.getText().toString();
        int myKey = 0;
        if (key.matches("") || text.matches("")) {
            output = "";
            //vibrator.vibrate(context, 500);
            //toast.makeToast(context, "text or key is empty!");
            return output;
        }
        myKey = stringToInteger.parseInt(key);
        if (myKey < 1) {
            myKey = 0;
        }
        if (myKey == 0) {
            alert("invalid key!");
            return "";
        }
        if (type == "e") {
            myKey = 1 * myKey;
        } else if (type == "d") {
            myKey = -1 * myKey;
        }
        byte [] bytes = text.getBytes();
        for (byte b : bytes) {
            int val = b;
            /*StringBuilder binary = new StringBuilder();
            for (int i = 0 ; i < 8 ; i++) {
                binary.append((val & 128 ) == 0 ? 0 : 1);
                val <<= 1 ;
            }*/
            //output = output + (char) (Integer.parseInt(binary.toString(), 2) + myKey);
            output = output + (char) (val + myKey);
        }
        return output;
        //return String.valueOf(myKey);
    } // end method binary
    
    private void alert(String msg) {
        //vibrator.vibrate(context, 500);
        ringtone.playNotification(context);
        toast.makeToast(context, msg);
    }
    
    /* Called when user clicks the 'Encrypt' button */
    public void encrypt(View view) {
        String result = binary("e");
        if (result.matches("")) {
            alert("text or key is empty!");
        } else {
            Intent intent = new Intent (this, DisplayResult.class);
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);
        }
    } // end method encrypt
    
    /* Called when user clicks the 'Decrypt' button */
    public void decrypt(View view) {
        String result = binary("d");
        if (result.matches("")) {
            alert("text or key is empty!");
        } else {
            Intent intent = new Intent (this, DisplayResult.class);
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);
        }
    } // end method decrypt
    
} // end class MainActivity