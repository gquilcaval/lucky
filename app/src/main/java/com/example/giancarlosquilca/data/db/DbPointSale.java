package com.example.giancarlosquilca.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.giancarlosquilca.data.OperationResult;
import com.example.giancarlosquilca.domain.models.PointSale;
import com.example.giancarlosquilca.domain.models.Product;
import com.example.giancarlosquilca.domain.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giancarlos Quilca on 28/05/2022.
 */
public class DbPointSale extends DbHelper {

    Context context;

    public DbPointSale(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertPointSale(PointSale pointSale) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", pointSale.getName());
            values.put("code", pointSale.getCode());
            values.put("direction", pointSale.getDirection());
            values.put("photo", pointSale.getPhoto());

            id = db.insert(TABLE_POINTSALE, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public long insertProduct(Product product) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", product.getName());
            values.put("cost", product.getCost());
            values.put("price", product.getPriceRev());
            values.put("stock", product.getStock());
            values.put("id_point", product.getIdPoint());
            id = db.insert(TABLE_PRODUCT, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Product> getAllProduct() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Product> listProduct = new ArrayList<>();
        Product product;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT , null);

        if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCost(cursor.getInt(2));
                product.setPriceRev(cursor.getInt(3));
                product.setStock(cursor.getInt(4));

                listProduct.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listProduct;
    }

    public OperationResult<PointSale> getAll() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PointSale> listPointSale = new ArrayList<>();
        PointSale pointSale;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_POINTSALE , null);

        if (cursor.moveToFirst()) {
            do {
                pointSale = new PointSale();
                pointSale.setId(cursor.getInt(0));
                pointSale.setName(cursor.getString(1));
                pointSale.setCode(cursor.getString(2));
                pointSale.setDirection(cursor.getString(3));
                pointSale.setPhoto(cursor.getString(4));

                listPointSale.add(pointSale);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return new OperationResult(listPointSale);
    }

    public List<PointSale> getAllValidate() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<PointSale> listPointSale = new ArrayList<>();
        PointSale pointSale;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_POINTSALE , null);

        if (cursor.moveToFirst()) {
            do {
                pointSale = new PointSale();
                pointSale.setId(cursor.getInt(0));
                pointSale.setName(cursor.getString(1));
                pointSale.setCode(cursor.getString(2));
                pointSale.setDirection(cursor.getString(3));
                pointSale.setPhoto(cursor.getString(4));

                listPointSale.add(pointSale);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listPointSale;
    }

    public ArrayList<Product> getAllProductsByPointSale(Integer idpoint) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Product> listaProduct = new ArrayList<>();
        Product product;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT + " WHERE id_point = " + idpoint + "", null);

        if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCost(cursor.getInt(2));
                product.setPriceRev(cursor.getInt(3));
                product.setStock(cursor.getInt(4));

                listaProduct.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return listaProduct;
    }

    public boolean updateProduct(Integer id, Integer costo, Integer price, Integer stock) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRODUCT + " SET  cost = '" + costo + "', price = '" + price + "', stock = '" + stock  + "' WHERE id_prod='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
