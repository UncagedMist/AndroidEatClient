package com.techbytecare.kk.androideatclient.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techbytecare.kk.androideatclient.Common.Common;
import com.techbytecare.kk.androideatclient.Database.Database;
import com.techbytecare.kk.androideatclient.FoodDetail;
import com.techbytecare.kk.androideatclient.FoodList;
import com.techbytecare.kk.androideatclient.Interface.ItemClickListener;
import com.techbytecare.kk.androideatclient.Model.Favourites;
import com.techbytecare.kk.androideatclient.Model.Food;
import com.techbytecare.kk.androideatclient.Model.Order;
import com.techbytecare.kk.androideatclient.R;
import com.techbytecare.kk.androideatclient.ViewHolder.FavouriteViewHolder;

import java.util.List;

import static com.techbytecare.kk.androideatclient.Common.Common.currentUser;

/**
 * Created by kundan on 2/14/2018.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteViewHolder> {

    private Context context;
    private List<Favourites> favouritesList;

    public FavouriteAdapter(Context context, List<Favourites> favouritesList) {
        this.context = context;
        this.favouritesList = favouritesList;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.favourite_items,parent,false);
        return new FavouriteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder viewHolder, final int position) {

        viewHolder.food_name.setText(favouritesList.get(position).getFoodName());
        viewHolder.food_price.setText(String.format("$ %s",favouritesList.get(position).getFoodPrice().toString()));

        Picasso.with(context).load(favouritesList.get(position).getFoodImage())
                .into(viewHolder.food_image);

        //quick cart
        final boolean isExists = new Database(context).checkFoodExist(
                favouritesList.get(position).getFoodId(), currentUser.getPhone());

        viewHolder.quick_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isExists) {

                    new Database(context).addToCart(new Order(
                            currentUser.getPhone(),
                            favouritesList.get(position).getFoodId(),
                            favouritesList.get(position).getFoodName(),
                            "1",
                            favouritesList.get(position).getFoodPrice(),
                            favouritesList.get(position).getFoodDiscount(),
                            favouritesList.get(position).getFoodImage()
                            ));
                }
                else    {
                    new Database(context).increaseCart(Common.currentUser.getPhone(),
                           favouritesList.get(position).getFoodId());
                }
                Toast.makeText(context, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });

        final Favourites local = favouritesList.get(position);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Toast.makeText(FoodList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();

                //start the activity
                Intent foodDetail = new Intent(context,FoodDetail.class);
                foodDetail.putExtra("FoodId",favouritesList.get(position).getFoodId());    //send food to new activity
                context.startActivity(foodDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    public Favourites getItem(int position)  {
        return favouritesList.get(position);
    }

    public void removeItem(int position)    {
        favouritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Favourites item,int position)    {
        favouritesList.add(position,item);
        notifyItemInserted(position);
    }
}
