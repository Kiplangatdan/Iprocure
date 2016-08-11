package com.iprocure.handler;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android_iprocure.R.id;
import com.android_iprocure.R.layout;
import com.iprocure_dan.Products;

public class ProductsAdapter extends BaseAdapter {
	private Context context;

	private List<Products> items;
	private LayoutInflater inflater;

	public ProductsAdapter(Context context, List<Products> item_prod) {
		super();
		this.context = context;
		this.items = item_prod;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void notifyDataSetChanged() {
		try {
			super.notifyDataSetChanged();
		} catch (Exception e) {
			trace("Erro: " + e.getMessage());
		}
	}

	private void trace(String msg) {
		toast(msg);
	}

	public void toast(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public int getCount() {
		return items.size();
	}

	// Delete a product form the list
	public void remove(final Products prod) {
		this.items.remove(prod);
	}

	// Add product
	public void add(final Products prod) {
		this.items.add(prod);
	}

	public Object getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewGroup2) {
		try {
			Products products = items.get(position);

			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(layout.products_row, null);
				holder = new ViewHolder();
				holder.tvName = (TextView) convertView.findViewById(id.name);
				holder.tvType = (TextView) convertView.findViewById(id.type);
				holder.tvPrice = (TextView) convertView.findViewById(id.price);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvName.setText(products.getName());
			holder.tvType.setText(products.getType());
			holder.tvPrice.setText(products.getPrice());

			return convertView;

		} catch (Exception e) {
			trace("Erro : " + e.getMessage());
		}
		return convertView;
	}
	static class ViewHolder {
		public TextView tvName;
		public TextView tvType;
		public TextView tvPrice;
	}
}
