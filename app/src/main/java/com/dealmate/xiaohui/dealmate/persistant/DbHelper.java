package com.dealmate.xiaohui.dealmate.persistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dealmate.xiaohui.dealmate.model.DealDetail;
import com.dealmate.xiaohui.dealmate.model.DealModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohui on 2/18/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="DEAL.db";
    public static final String IMAGE_URL_1="image_url_1";
    public static final String IMAGE_URL_2="image_url_2";
    public static final String SHIPPING="shipping";
    public static final String TITLE="title";
    public static final String DESC="desc";
    public static final String ORIGIN_PRICE="origin_price";
    public static final String CURRENT_PRICE="current_price";
    public static final String EXPIRE="expire";
    public static final String URL="url";
    public static final String DETAIL="detail";
    public static final String SERIAL="serial";
    public static final String CATEGORY="category";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table deals "+
                "(serial integer primary key, image_url_1 text, shipping text, title text, desc text, origin_price text,"+
                "current_price text, expire text, url text, detail text, image_url_2 text, category text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS deals");
        onCreate(db);
    }

    public boolean insertDeal(DealModel dealModel, DealDetail dealDetail) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE_URL_1, dealModel.getImagePath());
        contentValues.put(SHIPPING, dealModel.getShipping());
        contentValues.put(TITLE, dealModel.getTitle());
        contentValues.put(DESC, dealModel.getOverview());
        contentValues.put(ORIGIN_PRICE, dealModel.getListPrice());
        contentValues.put(CURRENT_PRICE, dealModel.getPrice());
        contentValues.put(SERIAL, dealModel.getId());
        contentValues.put(CATEGORY, dealModel.getCategory());
        contentValues.put(IMAGE_URL_2, dealDetail.getMainPoster());
        contentValues.put(EXPIRE, dealDetail.getExpire());
        contentValues.put(DETAIL, dealDetail.getDetailDescription());
        contentValues.put(URL, dealDetail.getLink());
        sqLiteDatabase.insert("deals", null, contentValues);
        return true;
    }

    public int deleteDeal(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("deals", "serial=?", new String[] {Integer.toString(id)});
    }

    public boolean checkExist(int id) {
        Cursor cursor = getReadableDatabase()
                .rawQuery("select * from deals where serial="+Integer.toString(id), null);
        boolean exist = (cursor.getCount()>0);
        cursor.close();
        return exist;
    }

    public DealDetail getDetail(int id) {
        DealDetail dealDetail = new DealDetail();
        Cursor cursor = getReadableDatabase()
                .rawQuery("select * from deals where serial="+Integer.toString(id), null);
        cursor.moveToNext();
        dealDetail.setDetailDescription(cursor.getString(9));
        dealDetail.setExpire(cursor.getString(7));
        dealDetail.setId(cursor.getInt(0));
        dealDetail.setLink(cursor.getString(8));
        dealDetail.setMainPoster(cursor.getString(10));
        cursor.close();
        return dealDetail;
    }

    public List<DealModel> getDeals() {
        List<DealModel> dealModels = new ArrayList<>();
        Cursor cursor = getReadableDatabase()
                .rawQuery("select * from deals", null);
        if(cursor.moveToFirst()) {
            do {
                DealModel dealModel = new DealModel();
                dealModel.setId(cursor.getInt(0));
                dealModel.setImagePath(cursor.getString(1));
                dealModel.setShipping(cursor.getString(2));
                dealModel.setTitle(cursor.getString(3));
                dealModel.setOverview(cursor.getString(4));
                dealModel.setListPrice(cursor.getString(5));
                dealModel.setPrice(cursor.getString(6));
                dealModel.setCategory(cursor.getString(11));
                dealModels.add(dealModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return dealModels;
    }
}
