package com.iprocure.handler;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.iprocure_dan.Products;

public class ProductsDB {
	private SQLiteDatabase database;
	private DBHandler baseDAO;
	
	private String[] columns = {DBHandler.PRODUCTS_ID,
								DBHandler.PRODUCTS_NAME,
								DBHandler.PRODUCTS_TYPE,
								DBHandler.PRODUCTS_PRICE};

	
	public ProductsDB(Context context) {
		baseDAO = new DBHandler(context);
	}
	
	
	public void open() throws SQLException {
		database = baseDAO.getWritableDatabase();
	}
	
	
	public void close() {
		baseDAO.close();
	}
	
	
	public long create(Products user) {
		ContentValues values = new ContentValues();

		values.put(DBHandler.PRODUCTS_NAME, user.getName());
		values.put(DBHandler.PRODUCTS_TYPE, user.getType());
		values.put(DBHandler.PRODUCTS_PRICE, user.getPrice());

		return database.insert(DBHandler.TABLE_PRODUCTS, null, values);
	}
	
	
	public Products read(long id) {
		Cursor cursor = database.query(DBHandler.TABLE_PRODUCTS, columns, DBHandler.PRODUCTS_ID + " = " + id, null, null, null, null);
		Products user = new Products();
		if  (cursor.moveToFirst()) {
			user.setId(cursor.getLong(0));
			user.setName(cursor.getString(1));
			user.setType(cursor.getString(2));
			user.setPrice(cursor.getString(3));
		}
		cursor.close();
		return user;
	}
	
	
	public List<Products> readAll() {
		List<Products> users = new ArrayList<Products>();
		Cursor cursor = database.query(DBHandler.TABLE_PRODUCTS, columns, null, null, null, null, null);
		if  (cursor.moveToFirst()) {
			while(cursor.isAfterLast()) {
				Products user = new Products( cursor.getLong(0),
						cursor.getString(1),
						cursor.getString(2),
						cursor.getString(3));
				users.add(user);
				cursor.moveToNext();
			}
		}
		cursor.close();
		return users;
	}
	
	
	public int update(Products user) {
		long id = user.getId();
		ContentValues values = new ContentValues();
		
		values.put(DBHandler.PRODUCTS_NAME, user.getName());
		values.put(DBHandler.PRODUCTS_TYPE, user.getType());
		values.put(DBHandler.PRODUCTS_PRICE, user.getPrice());
		return database.update(DBHandler.TABLE_PRODUCTS, values, DBHandler.PRODUCTS_ID + " = " + id, null);
	}
	
	
	public void delete(Products user) {
		long id = user.getId();
		
		database.delete(DBHandler.TABLE_PRODUCTS, DBHandler.PRODUCTS_ID + " = " + id, null);
	}
	
}
