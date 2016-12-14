package nyc.c4q.leighdouglas.memehunt;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();
    private static final String TAG2 = "add cat: ";
    private SQLiteDatabase db;
    private RecyclerView rv;
    private MemeAdapter memeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        launchTestService();

        Intent intent = getIntent();

        MemeDatabaseHelper dbHelper = MemeDatabaseHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

        collectMeme(intent.getLongExtra(Meme.MEME_ID, 0), db);

        memeAdapter = new MemeAdapter(memeList());

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(memeAdapter);



    }

    private List<Meme> memeList(){
        List<Meme> listOfMemes = new ArrayList<>();

        try {
            // Iterate memes
            QueryResultIterable<Meme> itr = cupboard().withDatabase(db).query(Meme.class).query();
            for (Meme meme : itr) {
                if (meme.getUsedAlready() == Meme.MEME_COLLECTED) {
                    listOfMemes.add(meme);
                    Log.d(TAG2, "added a meme");
                }
            }
            itr.close();
        } catch (Exception e) {
            Log.e(TAG, "selectAllCats: ", e);
        }

        return listOfMemes;
    }

    public void collectMeme(Long id, SQLiteDatabase db){
        if(id !=0){
            Meme collectMeme = cupboard().withDatabase(db).get(Meme.class, id);
            collectMeme.setUsedAlready(Meme.MEME_COLLECTED);
            cupboard().withDatabase(db).put(collectMeme);
        }
    }
    public void launchTestService() {
        Intent i = new Intent(this, NotificationService.class);
        startService(i);
    }
}
