package com.techbytecare.kk.androideatclient.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.techbytecare.kk.androideatclient.R;

/**
 * Created by kundan on 1/22/2018.
 */

public class ShowCommentViewHolder extends RecyclerView.ViewHolder {

    public TextView txtUserPhone,txtComment;
    public RatingBar ratingBar;

    public ShowCommentViewHolder(View itemView) {
        super(itemView);

        txtUserPhone = (TextView)itemView.findViewById(R.id.txtUserPhone);
        txtComment = (TextView)itemView.findViewById(R.id.txtComment);

        ratingBar = (RatingBar)itemView.findViewById(R.id.ratingBar);
    }
}
