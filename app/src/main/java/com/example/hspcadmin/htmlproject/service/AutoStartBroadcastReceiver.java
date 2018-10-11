package com.example.hspcadmin.htmlproject.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by -- on 2018/8/7.
 */
public class AutoStartBroadcastReceiver extends BroadcastReceiver {
    static final String action_boot ="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action_boot)){
            Intent sayHelloIntent=new Intent(context,ProperService.class);

            sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(sayHelloIntent);
        }
    }

}