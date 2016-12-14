package nyc.c4q.leighdouglas.memehunt;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by leighdouglas on 12/13/16.
 */

public class NotificationService extends IntentService {
    private Random random = new Random();
    private static final String SERVICE_NAME = "notification-service";
    private static final String TAG = "Catch";

    public NotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent){
        makeNotification();
    }


    public void makeNotification(){
        int NOTIFICATION_ID = 555;

        // Define an intent to trigger when notification is selected (in this case to open an activity)
        Intent intent = new Intent(this, ListActivity.class);

        MemeDatabaseHelper dbHelper = MemeDatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<Meme> listOfMemes = new ArrayList<>();

        try {
            // Iterate cats
            QueryResultIterable<Meme> itr = cupboard().withDatabase(db).query(Meme.class).query();
            for (Meme meme : itr) {
                if (meme.getUsedAlready() == 0) {
                    listOfMemes.add(meme);
                }
            }
            itr.close();
        } catch (Exception e) {
            Log.e(TAG, "selectAllMemes: ", e);
        }


        Long id = listOfMemes.get(random.nextInt(listOfMemes.size())).get_id();

        intent.putExtra(Meme.MEME_ID, id);

        // Turn this into a PendingIntent
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);


        // Attach the pendingIntent to a new notification using setContentIntent
        Notification notification = new android.support.v7.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("You got a new meme!")
                .setContentText("Click to see more")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

        // Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//      Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, notification);


    }
}
