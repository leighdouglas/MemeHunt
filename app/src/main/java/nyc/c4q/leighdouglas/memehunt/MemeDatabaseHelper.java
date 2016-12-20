package nyc.c4q.leighdouglas.memehunt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.leighdouglas.memehunt.model.Meme;

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
        addMeme(new Meme("Grumpy Cat", "https://static1.squarespace.com/static/55ac76aae4b08d4c252270d0/t/55ac8915e4b0f779b8601bdb/1458064138660/"), db);
        addMeme(new Meme("Nyan Cat", "http://www.pngall.com/wp-content/uploads/2016/06/Nyan-Cat-PNG.png"), db);
        addMeme(new Meme("Doge", "https://s-media-cache-ak0.pinimg.com/originals/fd/29/96/fd299634a2989fec82e88a98b1563533.png"), db);
        addMeme(new Meme("Deal with it Doge", "http://vignette3.wikia.nocookie.net/thefakegees/images/8/8e/Mlg_Doge.png/revision/latest?cb=20151231000415"), db);
        addMeme(new Meme("Thug Life Sunglasses", "http://www.pngall.com/wp-content/uploads/2016/03/Thug-Life-Cool-Glasses-PNG.png"), db);
        addMeme(new Meme("Why You Lyin", "https://pbs.twimg.com/media/CPpI3t3VAAAKThU.jpg"), db);
        addMeme(new Meme("Kermit Sipping Tea", "http://wac.450f.edgecastcdn.net/80450F/1079ishot.com/files/2014/06/Lipton_Kermit.png?w=600&h=0&zc=1&s=0&a=t&q=89"), db);
        addMeme(new Meme("Skeptical Kid", "https://imgflip.com/s/meme/Third-World-Skeptical-Kid.jpg"), db);
        addMeme(new Meme("Xzibit", "https://blog.developer.bazaarvoice.com/wp-content/uploads/2016/09/-11"), db);
        addMeme(new Meme("Bad Pun Dog", "https://i.imgflip.com/91cvs.jpg"), db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        cupboard().withDatabase(db).upgradeTables();
    }

    private void addMeme(Meme meme, SQLiteDatabase db) {
        cupboard().withDatabase(db).put(meme);
    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }

}
