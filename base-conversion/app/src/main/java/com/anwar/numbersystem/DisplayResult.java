package com.anwar.numbersystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class DisplayResult extends Activity {
    private TextView mTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        Intent intent = getIntent();
        String output = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mTextView = findViewById(R.id.result);
        mTextView.setText(output);
    } // end method onCreate
} // end class DisplayResult