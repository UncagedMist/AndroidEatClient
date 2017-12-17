package com.techbytecare.kk.androideatclient.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.techbytecare.kk.androideatclient.Model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kundan on 12/17/2017.
 */

public class DatabaseKK extends SQLiteAssetHelper   {

    private static final String DB_NAME = "EatItDataBase.db";
    private static final int DB_VER = 1;

    public DatabaseKK(Context context) {
        super(context, DB_NAME,null, DB_VER);
    }

    public List<Order> getCarts()   {

        SQLiteDatabase db = getReadableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName","ProductId","Quantity","Price","Discount"};

        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);

        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();

        if (c.moveToFirst())    {
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))));
            } while (c.moveToNext());
        } return result;
    }

    public void addToCart(Order order) {
        final SQLiteDatabase db = getReadableDatabase();

        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount)" +
                        " VALUES('%s', '%s','%s','%s','%s')",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());

        db.execSQL(query);
    }

    public void cleanCart() {

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");

        db.execSQL(query);
    }
}