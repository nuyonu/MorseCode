package com.example.morsecode;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.morsecode.MorseCode.MorseCode;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class SmsViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_view);

        final MessageListAdapter listAdapter = new MessageListAdapter(SmsViewActivity.this, getMessages());
        ListView listViewMessages = findViewById(R.id.listViewMessages);
        listViewMessages.setAdapter(listAdapter);
        listViewMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageInfo messageInfo = listAdapter.getItem(position);
                new AlertDialog.Builder(SmsViewActivity.this)
                        .setTitle("Message decoded")
                        .setMessage(MorseCode.tranformFromMorseCodeToText(messageInfo.getMessage()))
                        .setPositiveButton(android.R.string.yes, null)
                        .setIcon(android.R.drawable.ic_input_get)
                        .show();
            }
        });

        getMessages();
    }

    private List<MessageInfo> getMessages() {
        List<MessageInfo> messageInfoList = new ArrayList<>();

        Uri mSmsQueryUri = Uri.parse("content://sms/inbox");
        Cursor cursor = null;

        try {
            cursor = getContentResolver().query(mSmsQueryUri, new String[] { "address", "body", "date" }, null, null, "date DESC");
            if (cursor == null) {
//                Snackbar.make(SmsViewActivity, "A aparut o problemă, contactează administratorul aplicației.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            for (boolean hasData = cursor.moveToFirst(); hasData; hasData = cursor.moveToNext()) {
                final String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                final String bodyMessage = cursor.getString(cursor.getColumnIndexOrThrow("body"));

                if(bodyMessage.matches("[\\.\\- ]+"))
                {
                    messageInfoList.add(new MessageInfo(phoneNumber, bodyMessage));
                    final String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    Log.d("NYN", phoneNumber == null ? "No person" : phoneNumber);
                    Log.d("NYN", bodyMessage);
                    Log.d("NYN", date);
                }
            }
        } catch (Exception e) {
            Log.e("NYN", e.getMessage());
        } finally {
            cursor.close();
        }

        for(MessageInfo msg : messageInfoList)
        {
            Log.d("NYN", msg.getPhoneNumber() + " "  + msg.getMessage());
        }
        return messageInfoList;
    }
}
