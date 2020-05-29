package com.anwar.numbersystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.TextView;

//import org.apache.commons.codec.binary.*;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;

import android.content.Context;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

//import java.util.*;
//import java.util.Arrays;
//import java.util.ArrayList;

// utils
import com.anwar.numbersystem.utils.*;


public class MainActivity extends Activity {
    Activity context = this;
    
    public final static String EXTRA_MESSAGE = "com.anwar.numbersystem";
    public static final String FLOAT_MSG = "In this version Floating point " + 
        "number isn't working! We will fix it soon";
    public static final String EMPTY_MSG = "Please write something";
    
    private TextView decTextView;
    private TextView hexTextView;
    private TextView octTextView;
    private TextView binTextView;
    
    private static final int totalTheme = 11;
    private String theme[] = {
        "red", "purple", "deepPurple",
        "indigo", "blue", "lightBlue",
        "cyan", "green", "deepOrange",
        "teal"
    };
    
    private Toast toast;
    private Ringtone ringtone;
    private Vibrator vibrator;
    //private Camera camera;
    //private Restart restart;
    private StringToInteger stringToInteger;
    private MyParseFloat myParseFloat;
    //private SharedPreference sharedPreference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.teal);
        int random = (int)(Math.random() * totalTheme + 1);
        
        switch (random) {
            case 1: 
                setTheme(R.style.red);
                break;
            case 2: 
                setTheme(R.style.purple);
                break;
            case 3: 
                setTheme(R.style.deepPurple);
                break;
            case 4: 
                setTheme(R.style.indigo);
                break;
            case 5: 
                setTheme(R.style.blue);
                break;
            case 6: 
                setTheme(R.style.blue);
                break;
            case 7: 
                setTheme(R.style.cyan);
                break;
            case 8: 
                setTheme(R.style.teal);
                break;
            case 9: 
                setTheme(R.style.green);
                break;
            case 10: 
                setTheme(R.style.deepOrange);
                break;
            case 11: 
                setTheme(R.style.MyTheme);
                break;
            default: setTheme(R.style.blue);
        }
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // from utils
        toast = new Toast();
        ringtone = new Ringtone();
        vibrator = new Vibrator();
        //camera = new Camera();
        //restart = new Restart();
        stringToInteger = new StringToInteger();
        myParseFloat = new MyParseFloat();
        //sharedPreference = new SharedPreference();
        
        //initialize values
        
        //toast.makeToast(context, "Opening App...");
        //ringtone.playNotification(context);
        //vibrator.vibrate(context, 500);
        
        TextView facebookHtml = findViewById(R.id.facebook);
        facebookHtml.setText(
            Html.fromHtml(
                "<div><br /><h4 style='color:#A8DFF4;'>Anwar Hossain</h4>"
                + "<p><i><a style='text-decoration: none;' " + 
                    "href=\"https://web.facebook.com/anwar.ubuntu\">"
                + "facebook.com/anwar.ubuntu</a></i> <br /><a style='text-decoration: none;' " + 
                    "href=\"https://www.github.com/w3cp\">github.com/w3cp</a>"
                + "  <a style='text-decoration: none;' href=\"tel:+8801768310499\">Call Me</a> </p>"
                + "<p>Shashail, Rasulpur [6540] <br />Patnitala, Naogaon <br />"
                + "Rajshahi, Bangladesh<br />"
                + "Shibpur High School - <i>SSC 2014</i></p></div>"));
        facebookHtml.setMovementMethod(LinkMovementMethod.getInstance());
        
        EditText textDec = findViewById(R.id.dec);
        EditText textHex = findViewById(R.id.hex);
        EditText textOct = findViewById(R.id.oct);
        EditText textBin = findViewById(R.id.bin);
        
        setListener(textDec, "d");
        setListener(textHex, "h");
        setListener(textOct, "o");
        setListener(textBin, "b");
    } // end method onCreate
    
    private void baseConversion(String base) {
        EditText textDec = findViewById(R.id.dec);
        EditText textHex = findViewById(R.id.hex);
        EditText textOct = findViewById(R.id.oct);
        EditText textBin = findViewById(R.id.bin);
        
        String dec = textDec.getText().toString();
        String hex = textHex.getText().toString();
        String oct = textOct.getText().toString();
        String bin = textBin.getText().toString();
        
        decTextView = findViewById(R.id.dec);
        hexTextView = findViewById(R.id.hex);
        octTextView = findViewById(R.id.oct);
        binTextView = findViewById(R.id.bin);
        
        int decNum;
        
        if (base == "d") {
            if (dec.matches("")) {
                alert(EMPTY_MSG);
                dec = "0";
                //return;
            }
            decNum = stringToInteger.parseInt(dec, 10);
            if (decNum < 0) {
                decNum = 0;
                alert(FLOAT_MSG);
            }
            hexTextView.setText(Integer.toHexString(decNum));
            octTextView.setText(Integer.toOctalString(decNum));
            binTextView.setText(Integer.toBinaryString(decNum));
        } else if (base == "h") {
            if (hex.matches("")) {
                alert(EMPTY_MSG);
                hex = "0";
                //return;
            }
            decNum = stringToInteger.parseInt(hex, 16);
            if (decNum < 0) {
                decNum = 0;
                alert(FLOAT_MSG);
            }
            decTextView.setText(String.valueOf(decNum));
            octTextView.setText(Integer.toOctalString(decNum));
            binTextView.setText(Integer.toBinaryString(decNum));
        } else if (base == "o") {
            if (oct.matches("")) {
                alert(EMPTY_MSG);
                oct = "0";
                //return;
            }
            decNum = stringToInteger.parseInt(oct, 8);
            if (decNum < 0) {
                decNum = 0;
                alert(FLOAT_MSG);
            }
            decTextView.setText(String.valueOf(decNum));
            hexTextView.setText(Integer.toHexString(decNum));
            binTextView.setText(Integer.toBinaryString(decNum));
        } else if (base == "b") {
            if (bin.matches("")) {
                alert(EMPTY_MSG);
                bin = "0";
                //return;
            }
            decNum = stringToInteger.parseInt(bin, 2);
            if (decNum < 0) {
                decNum = 0;
                alert(FLOAT_MSG);
            }
            decTextView.setText(String.valueOf(decNum));
            hexTextView.setText(Integer.toHexString(decNum));
            octTextView.setText(Integer.toOctalString(decNum));
        }
    } // end method baseConversion
    
    private void alert(String msg) {
        //vibrator.vibrate(context, 500);
        ringtone.playNotification(context);
        toast.makeToast(context, msg);
    }
    
    private void setListener(EditText editText, final String numBase) {
        //final String b = base;
        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //if (event.getAction()==KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_INSERT || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    baseConversion(numBase);
                    return true;
                }
                if (event.getAction()==KeyEvent.ACTION_UP) {
                    baseConversion(numBase);
                    return true;
                }
                return false;
            }
        });
    }
    
} // end class MainActivity