package com.anwar.numbersystem.utils;

// flashlight
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

import android.content.Context;

public class Camera {
    public Camera() {
        super();
    } // end method camera
    
    private Camera camera;
    
    // flag to detect flash is on or off
    private boolean isLightOn = false;
    
    // FLASHLIGHT on of
    public boolean flashlight() {
        final Parameters p = camera.getParameters();

        if (isLightOn) {
            p.setFlashMode(Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.stopPreview();
            isLightOn = false;
        } else {
            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
            isLightOn = true;
        }
        return isLightOn;
    } // end method flashlight
    
    public void openCamera() {
        camera = Camera.open();
    }
    
    public void releaseCamera() {
        if (camera != null) {
            camera.release();
        }
    }
} // end class Camera
