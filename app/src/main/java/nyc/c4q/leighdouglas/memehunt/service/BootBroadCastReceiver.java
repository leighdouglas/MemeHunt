package nyc.c4q.leighdouglas.memehunt.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import nyc.c4q.leighdouglas.memehunt.service.MemeAlarmReceiver;

/**
 * Created by leighdouglas on 12/17/16.
 */

public class BootBroadCastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MemeAlarmReceiver.class);


        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context, MemeAlarmReceiver.REQUEST_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15, pendingIntent);

        startWakefulService(context, i);

    }
}

