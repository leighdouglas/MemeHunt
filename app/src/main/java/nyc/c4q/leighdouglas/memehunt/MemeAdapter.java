package nyc.c4q.leighdouglas.memehunt;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by leighdouglas on 12/6/16.
 */

public class MemeAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<Meme> memeData;
    private static String TAG = "Adapter";

    public MemeAdapter(List<Meme> memeData){
        this.memeData = memeData;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meme meme = memeData.get(position);
        holder.onBind(meme);

    }

    @Override
    public int getItemCount() {
        return memeData.size();
    }

    public void setMemeData(List<Meme> memeData) {
        this.memeData = memeData;
        notifyDataSetChanged();
    }
}
