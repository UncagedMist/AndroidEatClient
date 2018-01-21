package com.techbytecare.kk.androideatclient.ViewHolder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.techbytecare.kk.androideatclient.Common.Common;
import com.techbytecare.kk.androideatclient.Interface.ItemClickListener;
import com.techbytecare.kk.androideatclient.R;

/**
 * Created by kundan on 12/17/2017.
 */

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener    {

    public TextView txt_cart_name,txt_price;
    public ElegantNumberButton btn_quantity;
    public ImageView cart_image;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);

        txt_cart_name = (TextView) itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView) itemView.findViewById(R.id.cart_item_price);
        btn_quantity = (ElegantNumberButton) itemView.findViewById(R.id.btn_quantity);
        cart_image = (ImageView)itemView.findViewById(R.id.cart_image);

        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        contextMenu.setHeaderTitle("Select Option");
        contextMenu.add(0,0,getAdapterPosition(), Common.DELETE);
    }
}