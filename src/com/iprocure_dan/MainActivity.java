package com.iprocure_dan;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android_iprocure.R;
import com.iprocure.handler.ProductsAdapter;
import com.iprocure.handler.ProductsDB;

public class MainActivity extends ListActivity {
	private static final int POSITION = 0;
	private static final int EDIT = 1;
	final Products product_list=null;

	private ProductsDB productsDB;
	List<Products> products;
	ProductsAdapter adapter;
	
	boolean blnShort = false;
	int ptn = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		productsDB = new ProductsDB(this);
		productsDB.open();
		products = productsDB.readAll();

		adapter = new ProductsAdapter(this, products);
		setListAdapter(adapter);

		registerForContextMenu(getListView());
	}

	//Click listener - Add product button
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.add:
			InsertProduct();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Products prod = null;

		try {
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == Activity.RESULT_OK) {
				
				prod = (Products) data.getExtras().getSerializable("products");

				
				if (requestCode == POSITION) {
					//Check if database has any records
					if (!prod.getName().equals("")) {
						
						productsDB.open();

						
						productsDB.create(prod);

						// Add a new product item
						products.add(prod);
					}
				} else if (requestCode == EDIT) {
					productsDB.open();
					
					products.set(ptn, prod);
				}

				
				adapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
	}

	private void InsertProduct() {
		try {

			Intent it = new Intent(this, ProductsUI.class);
			it.putExtra("edit", POSITION);
			startActivityForResult(it, POSITION);// Display details of selected product
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

	@Override
	protected void onResume() {
		
		productsDB.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		
		productsDB.close();
		super.onPause();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		try {
			

			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			if (!blnShort) {
				ptn = info.position;
			}
			blnShort = false;

			menu.setHeaderTitle("Selection:");
			//Display Menu--Edit product or Delete
			String[] menuItems = getResources().getStringArray(R.array.menu);
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
	}

	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Products product_list = null;
		try {
			int menuItemIndex = item.getItemId();

			
			product_list = (Products) getListAdapter().getItem(ptn);

			if (menuItemIndex == 0) {
				
				Intent it = new Intent(this, ProductsUI.class);
				it.putExtra("edit", EDIT);
				it.putExtra("products", product_list);
				startActivityForResult(it, EDIT); 
				
			} else if (menuItemIndex == 1) {
				productsDB.delete(product_list);
				products.remove(product_list);
				adapter.notifyDataSetChanged();
				Toast.makeText(this, "Product Deleted Successfully", 
						   Toast.LENGTH_LONG).show();
				
				/*AlertDialog.Builder builder = new AlertDialog.Builder(this);

			    builder.setTitle("Confirm");
			    builder.setMessage("Are you sure?");

			    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			    	

			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			        	//dialog.dismiss();
			        	
			        }
			    });
			    

			    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			        //@Override
			        public void onClick(DialogInterface dialog, int which) {

			            // Do nothing
			            dialog.dismiss();
			        }
			    });

			    AlertDialog alert = builder.create();
			    alert.show();
				*/
				 
			}
		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
		return false;

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		ptn = position;
		blnShort = true;
		this.openContextMenu(l);
	}
}
