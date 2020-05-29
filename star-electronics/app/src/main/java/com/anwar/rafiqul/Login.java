package com.anwar.rafiqul;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;
//import android.content.SharedPreferences;

// utils
import com.anwar.rafiqul.utils.SharedPreference;
import com.anwar.rafiqul.utils.Toast;
import com.anwar.rafiqul.utils.Camera;
import com.anwar.rafiqul.utils.Restart;

public class Login extends Activity {
    private static final String ORIGINAL_FLAG = "1";
    //private static final String UNREGISTERED_FLAG = "0";
    
    private SharedPreference sharedPreference;
    private Toast toast;
    private Camera camera;
    private Restart restart;
    
    Activity context = this;
    
    Button cancel, ok;
    EditText text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        sharedPreference = new SharedPreference();
        toast = new Toast();
        camera = new Camera();
        restart = new Restart();
        
        toast.makeToast(context, "Second Activity");
        
        cancel = (Button) findViewById(R.id.login_cancel);
        ok = (Button) findViewById(R.id.login_ok);
        text = (EditText) findViewById(R.id.password);
        
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.getText().toString().equals("8800-8960")) {
                    sharedPreference.save(context, ORIGINAL_FLAG);
                    toast.makeToast(context, "Restarting...");
                    restart();
                    //finish();
                } else {
                    toast.makeToast(context, "Wrong Credentials");
                }
            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.makeToast(context, "Registration Failed");
                finish();
            }
        });
    }
    
    // restart application
    private void restart() {
        if (camera != null) {
            //toast.makeToast(context, "Releasing Camera...");
            camera.releaseCamera();
        }

        restart.restartApplication(context, this);
    } // end method restart
    
    private Context getActivity() {
        // TODO: Implement this method
        return null;
    } // end method getActivity
} // end class DisplayResult
