package com.techbytecare.kk.androideatclient.Interface;

import android.support.v7.widget.RecyclerView;

/**
 * Created by kundan on 2/11/2018.
 */

public interface RecyclerItemTouchHelperListener {

    void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);
}
