package nyc.c4q.leighdouglas.memehunt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import nyc.c4q.leighdouglas.memehunt.model.Meme;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgView;
    private TextView txtView;

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

            }
        });
    }
}

