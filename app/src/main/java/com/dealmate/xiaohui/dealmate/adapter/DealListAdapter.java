package com.dealmate.xiaohui.dealmate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dealmate.xiaohui.dealmate.R;
import com.dealmate.xiaohui.dealmate.activity.ActivityBase;
import com.dealmate.xiaohui.dealmate.activity.DealDetailBaseActivity;
import com.dealmate.xiaohui.dealmate.config.CategoryEnum;
import com.dealmate.xiaohui.dealmate.model.DealModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohui on 1/22/2017.
 */

public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.ViewHolder> {
    private List<DealModel> deals = new ArrayList<>();
    private Context parentContext;
    private CategoryEnum categoryEnum;
    private Class classType;
    private final static String KEY = "KEY";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView overview;
        public ImageView thumbnail;
        public TextView shipping;
        public TextView price;
        public LinearLayout bigButton;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            overview = (TextView) view.findViewById(R.id.ovverview);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            shipping = (TextView) view.findViewById(R.id.shipping);
            price = (TextView) view.findViewById(R.id.price);
            bigButton = (LinearLayout) view.findViewById(R.id.bigButton);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_deal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(deals.get(position).getTitle());
        holder.overview.setText(deals.get(position).getOverview());
        if(!deals.get(position).getPrice().equals("")
                && !deals.get(position).getListPrice().equals(""))
            holder.price.setText(deals.get(position).getPrice()
                    + " originally " + deals.get(position).getListPrice());
        if(deals.get(position).getImagePath() != null
                && !deals.get(position).getImagePath().equals("")) {
            Picasso.with(parentContext).load(deals.get(position)
                    .getImagePath().replace("124","496").replace("110","440"))
                    .into(holder.thumbnail);
        }
        holder.shipping.setText(deals.get(position).getShipping());
        holder.bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityOptionsCompat option
//                        = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) parentContext, (View)holder.thumbnail, "productImg");
                DealDetailBaseActivity.invokeActivity((ActivityBase)parentContext,
                        classType,
                        KEY,
                        deals.get(position).getCategory()
                                +"~"+deals.get(position).getId()
                                +"~"+ new Gson().toJson(deals.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public void setDeals(List<DealModel> deals) {
        this.deals = deals;
        notifyDataSetChanged();
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }
}
