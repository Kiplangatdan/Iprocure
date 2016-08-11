package com.iprocure_dan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android_iprocure.R;
public class ProductsUI extends Activity {
	private static final int EDIT = 0;
	// private static final int EDIT = 1;
	Products products;
	EditText txtName;
	EditText txtType;
	EditText txtPrice;
	Button txtbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mai);

		try {

			final Bundle data = (Bundle) getIntent().getExtras();
			int lint = data.getInt("edit");
			if (lint == EDIT) {
				
				products = new Products();
			} else {
				
				products = (Products) data.getSerializable("products");
			}

			//Capture input from users
			txtName =		(EditText) findViewById(R.id.productname);
			txtType =		(EditText) findViewById(R.id.type);
			txtPrice =	(EditText) findViewById(R.id.price);
			txtbtn = (Button) findViewById(R.id.btnAdd);

			//Display data for editing
			txtName.setText(products.getName());
			txtType.setText(products.getType());
			txtPrice.setText(products.getPrice());
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
	}

	public void btnAdd_click(View view) {
		
			if(txtName.getText().toString().trim().equalsIgnoreCase("")){
    	        //txtName.setError("Enter Product Name");
    	    txtName.setError(Html.fromHtml("<font color='black'>Enter Product Name</font>"));
				}
			if(TextUtils.isEmpty(txtType.getText().toString())){
 	        //txtType.setError("Enter Product Type");
			txtType.setError(Html.fromHtml("<font color='black'>Enter Product Type</font>"));
 	        
				}
			if(TextUtils.isEmpty(txtPrice.getText().toString())){
  	        //txtPrice.setError("Enter Product Price");
			txtPrice.setError(Html.fromHtml("<font color='black'>Enter Product Price</font>"));
			}else{
				try {
			//Creat a new Activity
			Intent data = new Intent();
			products.setName(txtName.getText().toString());
			products.setType(txtType.getText().toString());
			products.setPrice(txtPrice.getText().toString());
			data.putExtra("products", products);
			System.out.println(products);
			setResult(Activity.RESULT_OK, data);
			finish();
			
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
	}
	}
	

	public void btnCancel_click(View view) {
		try {
			

			setResult(Activity.RESULT_CANCELED);
			finish();
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}

}
