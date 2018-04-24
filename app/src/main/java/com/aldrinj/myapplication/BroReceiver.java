package com.aldrinj.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1= new Intent(context,MyIntentService.class);
        context.startService(intent1);
    }
}
