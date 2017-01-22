package com.udacity.capstone.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.capstone.R;
import com.udacity.capstone.utilities.PrefManager;
import com.udacity.capstone.data.AartiModel;

import java.util.List;

/**
 * Created by mangesh on 14/1/17.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AartiCollectionViewHolder> implements View.OnClickListener{

    static Context context;

    private OnItemClickListener onItemClickListener;

    PrefManager prefManager = null;

    @Override
    public AartiCollectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_lordaarticollection, viewGroup, false);
        AartiCollectionViewHolder pvh = new AartiCollectionViewHolder(v);
        v.setOnClickListener(this);
        return pvh;

    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return aartiModelList.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (AartiModel) v.getTag());
                }
            }, 200);
        }

    }

    public  class AartiCollectionViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        ImageView like;

        AartiCollectionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.name);

            like = (ImageView) itemView.findViewById(R.id.like);

            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prefManager.cacheStoredNames();
                    if(prefManager.isFreeSlotAvailable()) {
                        Toast.makeText(context, "Added to Favourite.", Toast.LENGTH_SHORT).show();
                        like.setImageResource(R.drawable.ic_favorite_selected);

                        if (onItemClickListener != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onItemClickListener.onFavouriteClick(aartiModelList.get(getAdapterPosition()));
                                }
                            }, 200);
                        }
                    }
                    else {
                        Toast.makeText(context, "No Favourite Slot Available.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    List<AartiModel> aartiModelList;

    RVAdapter(List<AartiModel> aartiModelList, Context context) {
        this.aartiModelList = aartiModelList;
        this.context = context;
        prefManager = new PrefManager(context);
        prefManager.cacheStoredNames();

    }

    @Override
    public void onBindViewHolder(AartiCollectionViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(aartiModelList.get(i).getName());
        if(prefManager.isAartiNameAvailable(aartiModelList.get(i).getName())) {
            personViewHolder.like.setImageResource(R.drawable.ic_favorite_selected);
        }else {
            personViewHolder.like.setImageResource(R.drawable.ic_favorite);
        }
        personViewHolder.itemView.setTag(aartiModelList.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public interface OnItemClickListener {

        void onItemClick(View view, AartiModel viewModel);

        void onFavouriteClick(AartiModel viewModel);

    }

    
}
