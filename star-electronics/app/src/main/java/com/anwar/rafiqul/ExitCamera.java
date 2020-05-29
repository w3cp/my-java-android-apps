package com.anwar.rafiqul;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Context;
import android.view.View;
import android.widget.Button;

// utils
//import com.anwar.rafiqul.utils.Toast;
//import com.anwar.rafiqul.utils.Camera;

public class ExitCamera extends Activity {
    //private Camera camera;
    
    Activity context = this;
    
    //Button yes, no;
    Button gotIt;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_camera);
        
        gotIt = (Button) findViewById(R.id.camera_close_got_it);
        
        gotIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        
        /*camera = new Camera();
        
        no = (Button) findViewById(R.id.camera_close_no);
        yes = (Button) findViewById(R.id.camera_close_yes);
        
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (camera != null) {
                    //toast.makeToast(context, "Releasing Camera...");
                    camera.releaseCamera();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                }
            }
        });
        
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
    } // end method onCreate
    
    private Context getActivity() {
        // TODO: Implement this method
        return null;
    } // end method getActivity
} // end class DisplayResult
