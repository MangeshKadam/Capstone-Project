

package com.udacity.capstone.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.capstone.data.AartiKathaDetails;
import com.udacity.capstone.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private final Context context;
    private List<AartiKathaDetails> items;
    private OnItemClickListener onItemClickListener;


    public RecyclerViewAdapter(Context context, List<AartiKathaDetails> items) {
        this.context = context;
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        AartiKathaDetails item = items.get(position);
        holder.itemName.setText(item.getAartiName());
        holder.imageSource.setImageResource(item.getImageID());
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (AartiKathaDetails) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;

        public ImageView imageSource;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.aarti_name);
            imageSource = (ImageView) itemView.findViewById(R.id.img_god);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, AartiKathaDetails viewModel);

    }
}
