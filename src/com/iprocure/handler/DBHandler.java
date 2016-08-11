package com.iprocure.handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
	
	public static final String TABLE_PRODUCTS = "productsCRUD";
	public static final String PRODUCTS_ID = "_id";
	public static final String PRODUCTS_NAME = "name";
	public static final String PRODUCTS_TYPE = "type";
	public static final String PRODUCTS_PRICE = "price";
	
	public static final String DATABASE_NAME = "usuario.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String CREATE_PRODUCTS = "create table " + TABLE_PRODUCTS + " ( " +
								PRODUCTS_ID			+ " integer primary key autoincrement, " +
								PRODUCTS_NAME		+ " text not null, " +
								PRODUCTS_TYPE			+ " text not null, " +
								PRODUCTS_PRICE	+ " text not null);";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		System.out.println("pass to " + this.toString());
		database.execSQL(CREATE_PRODUCTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		onCreate(database);
	}

}
