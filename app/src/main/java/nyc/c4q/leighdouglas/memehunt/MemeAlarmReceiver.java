package nyc.c4q.leighdouglas.memehunt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by leighdouglas on 12/17/16.
 */

public class MemeAlarmReceiver extends BroadcastReceiver{
    public static final int REQUEST_CODE = 2222;


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NotificationService.class);
        context.startService(i);

    }
}
