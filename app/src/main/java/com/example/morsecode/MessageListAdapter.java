package com.example.morsecode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageListAdapter extends ArrayAdapter<MessageInfo> {
public MessageListAdapter(Context context, List<MessageInfo> products) {
        super(context, 0, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageInfo message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_sms, parent, false);
        }
        TextView tvPhoneNumber = (TextView) convertView.findViewById(R.id.textViewPhoneNumber);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.textViewMessage);

        tvPhoneNumber.setText(message.getPhoneNumber());
        tvMessage.setText(message.getMessage());

        return convertView;
    }

}
