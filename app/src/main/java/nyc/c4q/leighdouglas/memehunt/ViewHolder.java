package nyc.c4q.leighdouglas.memehunt;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imgView;
    TextView txtView;

    public ViewHolder(View itemView) {
        super(itemView);
        imgView = (ImageView) itemView.findViewById(R.id.meme_image);
        txtView = (TextView) itemView.findViewById(R.id.meme_name);

    }

    public void onBind(final Meme meme) {
        txtView.setText(meme.getName());

        Glide
                .with(itemView.getContext())
                .load(meme.getUrl())
                .crossFade()
                .into(imgView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileCard profileCard = new ProfileCard(itemView.getContext(), meme);
                profileCard.show();
                profileCard.setCanceledOnTouchOutside(true);
                profileCard.textView.setText(meme.getName());
                profileCard.editText.setHint(meme.getName());
                if (meme.getName().equalsIgnoreCase("bad pun dog")) {
                    Glide
                            .with(itemView.getContext())
                            .load(meme.getUrl())
                            .centerCrop()
                            .crossFade()
                            .into(profileCard.imageView);
                } else {
                    Glide
                            .with(itemView.getContext())
                            .load(meme.getUrl())
                            .crossFade()
                            .into(profileCard.imageView);
                }

            }
        });
    }
}

