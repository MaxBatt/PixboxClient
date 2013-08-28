package de.pixbox.client.helpers;


import java.util.ArrayList;

import de.pixbox.client.R;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WebCachedImageView;
import android.widget.ArrayAdapter;
import android.widget.TextView;


/**
 * @author Max Batt
 * List Adapter for GalleryList
 * Defines how every row in List is designed
 */
public class GalleryListAdapter extends ArrayAdapter<Image> {
	private final Context context;
	private final ArrayList<Image> imageList;

	private WebCachedImageView imgView;
	private TextView tvUploadInfo;
	

	// Build ListAdapter from ArrayList
	public GalleryListAdapter(Context context,
			ArrayList<Image> imageList) {
		super(context, R.layout.image_row, imageList);
		this.context = context;
		this.imageList = imageList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Fill ListRow
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.image_row, parent, false);
				
		
		// Put image in row via WebCachedImageView
		imgView = (WebCachedImageView) rowView.findViewById(R.id.imgView);
		imgView.setImageUrl(imageList.get(position).getThumbURL());
		
		tvUploadInfo = (TextView) rowView.findViewById(R.id.tvUploadInfo);
		
		System.out.println("img id: " + imageList.get(position).getId());
		tvUploadInfo.setText(imageList.get(position).getCreatedAt());
		
		
		// Return ListRow.
		return rowView;

	}

}
