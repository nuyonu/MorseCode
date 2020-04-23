package com.example.morsecode;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.morsecode.MorseCode.MorseCode;
import com.example.morsecode.MorseCode.MorseTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText input = findViewById(R.id.inputEnglish);
        final TextView textViewCurrentCharacter;textViewCurrentCharacter = findViewById(R.id.textViewCurrentCharacter);
        final TextView currentMorseCode = findViewById(R.id.textViewMorseCode);
        final CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", MorseCode.transformFromTextToMorseCode(input.getText().toString().toLowerCase()));
                startActivity(sendIntent);
            }
        });

        Button button = findViewById(R.id.buttonShowMorseCode);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String value = input.getText().toString().toLowerCase();
                if(value.length() == 0)
                    Snackbar.make(v, "Trebuie sa introduceti cel putin un caracter", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                else
                    new MorseTask(cameraManager, textViewCurrentCharacter, currentMorseCode).execute(value);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
