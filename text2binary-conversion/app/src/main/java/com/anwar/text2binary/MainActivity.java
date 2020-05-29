package com.anwar.text2binary;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
//import android.widget.TextView;
//text
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
//utils
import com.anwar.text2binary.utils.Ringtone;
import com.anwar.text2binary.utils.Toast;
import com.anwar.text2binary.utils.Vibrator;

//import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends Activity {
    Activity context = this;
    
    public final static String EXTRA_MESSAGE = "com.anwar.text2binary";
    
    //private TextView mTextView;
    
    private Toast toast;
    private Ringtone ringtone;
    private Vibrator vibrator;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //initialize values
        // from utils
        toast = new Toast();
        ringtone = new Ringtone();
        vibrator = new Vibrator();
        
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
                "<div><br /><h4>Anwar Hossain</h4>"
                + "<i><a href=\"https://web.facebook.com/anwar.ubuntu\">"
                + "facebook.com/anwar.ubuntu</a></i> <br />"
                + "<p>Shashail, Rasulpur [6540] <br />Patnitala, Naogaon <br />"
                + "Rajshahi, Bangladesh</p>"
                + "<p><b>Shibpur High School - <i>SSC 2014</i></b></p></div>"));
        facebookHtml.setMovementMethod(LinkMovementMethod.getInstance());
    } // end method onCreate
    
    /* Called when user clicks the 'Text to Binary' button */
    public void textToBinary(View view) {
        ArrayList< String > messageList = new ArrayList< String>();
        EditText editText = findViewById(R.id.edit_text);
        String text = editText.getText().toString();
        String output = new String();
        byte [] bytes = text.getBytes();
        StringBuilder binary = new StringBuilder();
        //StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0 ; i < 8 ; i++) {
                binary.append((val & 128 ) == 0 ? 0 : 1);
                val <<= 1 ;
            }
            //binary.append( ' ' );
        }
        messageList.add(binary.toString());
        //System.out.println(Arrays.toString(bytes));
        for (String object : messageList) {
            output = output + object;
        }
        //mTextView = (TextView) findViewById(R.id.output);
        //mTextView.setText(result);
        //mTextView.setText(binary);
        Intent intent = new Intent (this, DisplayResult.class);
        intent.putExtra(EXTRA_MESSAGE, output);
        startActivity(intent);
    } // end method textToBinary
    
    /* Called when user clicks the 'Binary to Text' button */
    public void binaryToText(View view) {
        Intent intent = new Intent (this, DisplayResult.class);
        EditText editText = findViewById(R.id.edit_text);
        String binary = editText.getText().toString();
        String texts = new String();
        //String output = new String();
        
        int i, j, k;
        int ascii_bit = 8;
        int length, decimal, power;
        int total_char;
        int nextTargetPosition;
        int isValidBinarySequence = 1;
        int errorIndex = 0;
        char errorDigit = '0';
        String errorStatement = "\n\n-------FATAL ERROR-------\n\n";
        char ch;

        length = binary.length();
        total_char = length / ascii_bit;
        //StringBuilder texts = new StringBuilder();
        //String texts = new String();
        int texts_index = 0;
        
        while (true) {
            if  (length % ascii_bit != 0) {
                isValidBinarySequence = 0;
                errorStatement += "Invalid Binary Sequence! [Total digits mod 8 != 0]\n";
                break;
            } // end if

            for (i = 0; i < length; i += ascii_bit) {
                power = ascii_bit - 1;
                decimal = 0;
                nextTargetPosition = i + ascii_bit;
                for (j = i; j < nextTargetPosition; j++) {
                    ch = binary.charAt(j);
                    if (ch < '0' || ch > '1') {
                        isValidBinarySequence = 0;
                        errorIndex = j + 1;
                        errorDigit = ch;
                        errorStatement += "Invalid Binary Sequence! At position " + errorIndex + " Invalid digit is: " + errorDigit + "\n";
                        //break;
                    } // end if
                    decimal += ((ch-'0') * Math.pow(2, power));
                    power--;
                } // end for
                if (isValidBinarySequence == 0) {
                    continue;
                    //isValidBinarySequence = 1;
                } else {
                    texts = texts + (char) decimal;
                } // end else if
            } // end for
            /*if (isValidBinarySequence == 0) {
                texts += errorStatement;
            }*/
            break;
        } // end while
        if (isValidBinarySequence == 0) {
            texts += errorStatement;
            toast.makeToast(context, "Fatal Error Occured");
            ringtone.playNotification(context);
            //vibrator.vibrate(context, 500);
        }
        intent.putExtra(EXTRA_MESSAGE, texts);
        startActivity(intent);
    } // end method binaryToText   
} // end class MainActivity
