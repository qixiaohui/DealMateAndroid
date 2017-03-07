package com.dealmate.xiaohui.dealmate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.model.LocalDealModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohui on 3/6/2017.
 */

public class LocalDealListAdapter extends RecyclerView.Adapter<LocalDealListAdapter.ViewHolder>{
    private List<LocalDealModel> localDealModels = new ArrayList<>();
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card_local_deal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(localDealModels.get(position).getImagePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return localDealModels.size();
    }

    public void setLocalDeal(List<LocalDealModel> localDealModels) {
        this.localDealModels = localDealModels;
    }
}
