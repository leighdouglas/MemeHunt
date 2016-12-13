package nyc.c4q.leighdouglas.memehunt;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imgView;
    TextView txtView;
    public static String TAG = "ViewHolder";

    public ViewHolder(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.meme_image);
        txtView = (TextView) itemView.findViewById(R.id.meme_name);
    }


    public void onBind(Meme meme){
       // Log.d(TAG, meme.getUrl());
        txtView.setText(meme.getName());
        //imgView.setImageResource(R.drawable.logo);
        //Picasso.with(itemView.getContext()).load(meme.getUrl()).into(imgView);
        //imgView.setImageResource(R.drawable.logo);
        Glide.with(itemView.getContext()).load(meme.getUrl()).error(R.drawable.logo).into(imgView);
    }
}