package com.techbytecare.kk.androideatclient;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.techbytecare.kk.androideatclient.Adapter.FavouriteAdapter;
import com.techbytecare.kk.androideatclient.Common.Common;
import com.techbytecare.kk.androideatclient.Database.Database;
import com.techbytecare.kk.androideatclient.Helper.RecyclerItemTouchHelper;
import com.techbytecare.kk.androideatclient.Interface.RecyclerItemTouchHelperListener;
import com.techbytecare.kk.androideatclient.Model.Favourites;
import com.techbytecare.kk.androideatclient.Model.Order;
import com.techbytecare.kk.androideatclient.ViewHolder.FavouriteViewHolder;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FavouritesActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FavouriteAdapter adapter;

    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        rootLayout = findViewById(R.id.root_layout);

        recyclerView = findViewById(R.id.recycler_fav);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
        
        loadFavourites();
    }

    private void loadFavourites() {
        adapter = new FavouriteAdapter(this,new Database(this).
                getAllFavourites(Common.currentUser.getPhone()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof FavouriteViewHolder)  {

            String name = ((FavouriteAdapter)recyclerView.getAdapter()).getItem(position).getFoodName();

            final Favourites deleteItem = ((FavouriteAdapter)recyclerView.getAdapter()).getItem(viewHolder.getAdapterPosition());

            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem(viewHolder.getAdapterPosition());

            new Database(getBaseContext()).removeFromFavourites(deleteItem.getFoodId(), Common.currentUser.getPhone());

            Snackbar snackbar = Snackbar.make(rootLayout,name + "removed from favourites!",Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem(deleteItem,deleteIndex);
                    new Database(getBaseContext()).addToFavourites(deleteItem);


                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
