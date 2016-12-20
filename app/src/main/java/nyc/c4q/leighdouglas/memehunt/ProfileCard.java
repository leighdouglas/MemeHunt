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

import com.bumptech.glide.Glide;

import nyc.c4q.leighdouglas.memehunt.model.Meme;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by leighdouglas on 12/14/16.
 */

public class ProfileCard extends Dialog {

    private TextView textView;
    private ImageView imageView;
    private EditText editText;
    private Button saveButton;
    private Button cancelButton;
    private Meme meme;

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
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setVisibility(View.GONE);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setVisibility(View.GONE);

        textView.setText(meme.getName());
        editText.setHint(meme.getName());
        if (meme.getName().equalsIgnoreCase("bad pun dog")) {
            Glide
                    .with(getContext())
                    .load(meme.getUrl())
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        } else {
            Glide
                    .with(getContext())
                    .load(meme.getUrl())
                    .crossFade()
                    .into(imageView);
        }
        getWindow().setLayout(1000, 1000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setVisibility(View.GONE);
                editText.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String newName = editText.getText().toString();
                editText.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);

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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
            }
        });
    }

}
