package nyc.c4q.leighdouglas.memehunt;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by leighdouglas on 12/14/16.
 */

public class ProfileCard extends Dialog {

    TextView textView;
    ImageView textView2;

    protected ProfileCard(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public ProfileCard(Context context) {
        super(context);
    }

    public ProfileCard(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_card);
        textView = (TextView) findViewById(R.id.name);
        textView2 = (ImageView) findViewById(R.id.description);
        getWindow().setLayout(1000, 1000);
    }



}
