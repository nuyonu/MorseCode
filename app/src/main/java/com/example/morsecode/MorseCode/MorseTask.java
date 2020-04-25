package com.example.morsecode.MorseCode;

import android.app.Activity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.morsecode.MainActivity;

public class MorseTask extends AsyncTask<String, String, String> {
    private CameraManager cameraManager;
    private Activity activity;
    private TextView textViewCurrentCharacter;
    private TextView currentMorseCode;

    public MorseTask(CameraManager cameraManager, TextView textViewCurrentCharacter, TextView currentMorseCode, Activity activity)
    {
        this.textViewCurrentCharacter = textViewCurrentCharacter;
        this.cameraManager = cameraManager;
        this.currentMorseCode = currentMorseCode;
        this.activity = activity;
    }

    protected String doInBackground(String... params) {
        String text = params[0];

        for(int i = 0; i < text.length(); i++)
        {
            String currentCharacter = Character.toString(text.charAt(i));
            publishProgress(currentCharacter);
            flashByCharacter(currentCharacter);
        }
        return "";
    }

    protected void onProgressUpdate(String... progress) {
        String currentCharacter = progress[0];
        String morse =  MorseCode.morseCode.get(currentCharacter);
        if(morse != null)
        {
            textViewCurrentCharacter.setText("{" + currentCharacter + "}");
            currentMorseCode.setText("[" + morse + "]");
        }
        else
        {
            Toast toast = Toast.makeText(activity,"No morse code found for [" + currentCharacter + "]", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    protected void onPostExecute(String result) {
        textViewCurrentCharacter.setText("");
        currentMorseCode.setText("");
    }

    private void flashOn() {
        String cameraId = null;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        try {
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void flashOff() {
        String cameraId = null;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        try {
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void flashByCharacter(String character) {
        final int milliseconds = 200;
        String morse = MorseCode.morseCode.get(character);
        try{
            for(int i = 0; i < morse.length(); i++)
                switch (morse.charAt(i)) {
                    case '-' : flashTime((long)(milliseconds * 3)); break;
                    case '.' : flashTime((long)(milliseconds)); break;
                }
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private void flashTime(long milliseconds) {
        try {
            flashOn();
            Thread.sleep(milliseconds);
            flashOff();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}