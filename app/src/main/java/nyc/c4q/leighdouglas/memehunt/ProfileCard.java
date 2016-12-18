package nyc.c4q.leighdouglas.memehunt;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by leighdouglas on 12/14/16.
 */

public class ProfileCard extends Dialog {

    TextView textView;
    ImageView imageView;
    EditText editText;
    Button button;
    Meme meme;
    private SQLiteDatabase db;

    protected ProfileCard(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProfileCard(Context context, Meme meme) {
        super(context);
        this.meme = meme;

    }

    public ProfileCard(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_card);
        textView = (TextView) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.meme_image);
        editText = (EditText) findViewById(R.id.edit_name);
        editText.setVisibility(View.GONE);
        button = (Button) findViewById(R.id.save_button);
        button.setVisibility(View.GONE);
        getWindow().setLayout(1000, 1000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String newName = editText.getText().toString();
                editText.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);

                MemeDatabaseHelper dbHelper = MemeDatabaseHelper.getInstance(getContext());
                db = dbHelper.getWritableDatabase();
                if (!newName.equalsIgnoreCase("")) {
                    textView.setText(newName);
                    Meme mMeme = cupboard()
                            .withDatabase(db)
                            .get(Meme.class, meme.get_id());
                    mMeme.setName(newName);
                    cupboard()
                            .withDatabase(db)
                            .put(mMeme);
                }
            }
        });
    }

}
