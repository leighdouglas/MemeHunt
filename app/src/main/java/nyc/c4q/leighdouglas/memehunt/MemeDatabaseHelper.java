package nyc.c4q.leighdouglas.memehunt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by leighdouglas on 12/6/16.
 */

public class MemeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "memeDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static MemeDatabaseHelper instance;

    private MemeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MemeDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new MemeDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    static {
        cupboard().register(Meme.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
        addMeme(new Meme ("Grumpy Cat", "https://static1.squarespace.com/static/55ac76aae4b08d4c252270d0/t/55ac8915e4b0f779b8601bdb/1458064138660/"), db);
        addMeme(new Meme ("Nyan Cat", "http://www.pngall.com/wp-content/uploads/2016/06/Nyan-Cat-PNG.png"), db);
        addMeme(new Meme ("Doge", "https://s-media-cache-ak0.pinimg.com/originals/fd/29/96/fd299634a2989fec82e88a98b1563533.png"), db);
        addMeme(new Meme ("Deal with it Doge", "http://vignette3.wikia.nocookie.net/thefakegees/images/8/8e/Mlg_Doge.png/revision/latest?cb=20151231000415"), db);
        addMeme(new Meme ("Thug Life Sunglasses","http://www.pngall.com/wp-content/uploads/2016/03/Thug-Life-Cool-Glasses-PNG.png"), db);
        addMeme(new Meme ("Bad Pun Dog", "https://i.imgflip.com/91cvs.jpg"), db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        cupboard().withDatabase(db).upgradeTables();
    }

    private void addMeme(Meme meme, SQLiteDatabase db) {
        cupboard().withDatabase(db).put(meme);
    }

}
