package de.pixbox.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;

import de.pixbox.client.helpers.GalleryListAdapter;
import de.pixbox.client.helpers.Image;
import de.pixbox.client.helpers.RestClient;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;



/**
 * Shows a list of all uploaded images and their upload date.
 * On click on an image the image is opened in gallery.
 * On long click on an image you can delete the image, copy the public url of the image and also show it in gallery
 * @author Max Batt
 *
 */
public class GalleryActivity extends ListActivity implements
		OnItemClickListener, OnItemLongClickListener {

	// ArrayList with all images
	private ArrayList<Image> imageList;
	// Adapter for Listview
	private GalleryListAdapter adapter;
	// position in list of the clicked image
	private int actPosition;
	
	private static String imageFolder = Environment.getExternalStorageDirectory().toString() + "/pixpox/pictures";
	

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Fetch Username and I from intent extras
		// Extras
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		// Get data via the key
		String userID = extras.getString("userID");
		String username = extras.getString("username");
		
		//Create a PixBox images folder, if it not exists
        createImageFolder(imageFolder);
		
		
		
		// Downlaod JSON list with all image URLS and infos from server with AsyncHTTPClient
		RestClient.get("pictures/?u=" + userID, null,
				new AsyncHttpResponseHandler() {

					// If request is successful
					@Override
					public void onSuccess(String response) {
						
						//Log.d("pixbox", response);
						
						// Convert JSON-Array in ArrayList
						Gson gson = new Gson();
						JsonParser parser = new JsonParser();
					    JsonArray Jarray = parser.parse(response).getAsJsonArray();

					    imageList = new ArrayList<Image>();

					    for(JsonElement obj : Jarray )
					    {
					        Image image = gson.fromJson( obj , Image.class);
					        imageList.add(image);
					    }
					    
					    // If there are images in the ArrayList
					    if (imageList.size() > 0) {

							// Create ListAdapter
							adapter = new GalleryListAdapter(GalleryActivity.this, imageList);
							setListAdapter(adapter);

							// Fill ListView
							ListView listView = getListView();
							
							// OnItemClickListener for Click on an item in list
							listView.setOnItemClickListener(GalleryActivity.this);
							
						} else {
							// If there are no images in the list, show error message
							showDialog(getResources().getString(
									R.string.no_images));
							
						}
						
					}

					// If request was not succesful, show error
					@Override
					public void onFailure(Throwable error, String content) {
						showDialog(getResources().getString(
								R.string.err_connect));
						Log.d("Pixbox", "HTTP error: " + error.getMessage());
					}
				});
		
		
		
	}

	
	/* (non-Javadoc)
	 * onItemClickListener for ListView.
	 * on Click the image is opened in gallery
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		// List-position of the clicked image
		actPosition = position;
		
		
		
		//Check if the file is already on SD Card. If not, download.
		String imagePath = imageFolder + "/" + imageList.get(position).getFilename();
		File file = new File(imagePath);
		
		if (!file.exists()){
			// Start AsyncTask downloading image
			new DownloadFileFromURL().execute(imageList.get(position).getUrl());
			System.out.println("Downloaded the file!");
		}
		else{
			openImageInGallery(imagePath);
			System.out.println("Opened The file from SD Card");
		}
		
	}
	
	
	/* (non-Javadoc)
	 * onItemLongClickListener for ListView.
	 * on Long click on an item a menu is shown
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		System.out.println("longclick");
		
		return true;
	}
	
	
	
	
	/**
	 * AsyncTask for Downloading image, writing it to local Storage and open in Gallery
	 * @author Max Batt
	 *
	 */
	class DownloadFileFromURL extends AsyncTask<String, String, String> {
		 
	    /**
	     * Before starting background thread
	     * Show Progress Bar Dialog
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        //showDialog(progress_bar_type);
	    }
	 
	    /**
	     * Downloading file in background thread and writing it to SD Card
	     * */
	    @Override
	    protected String doInBackground(String... f_url) {
	    	int count;
	        try {
	            URL url = new URL(f_url[0]);
	            URLConnection conection = url.openConnection();
	            conection.connect();
	 
	            // input stream to read file - with 8k buffer
	            InputStream input = new BufferedInputStream(url.openStream(), 8192);
	 
	            
	            
	            // Output stream to write file
	            OutputStream output = new FileOutputStream(imageFolder + "/" +  imageList.get(actPosition).getFilename());
	 
	            byte data[] = new byte[1024];
	            
	            long total = 0;
	 
	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // writing data to file
	                output.write(data, 0, count);
	            }
	 
	            // flushing output
	            output.flush();
	 
	            // closing streams
	            output.close();
	            input.close();
	 
	        } catch (Exception e) {
	            Log.d("Error: ", e.getMessage());
	        }
	 
	        return null;
	    }
	 
	    /**
	     * Updating progress bar
	     * */
	    protected void onProgressUpdate(String... progress) {
	        
	   }
	 
	    // After background task open the image in gallery.
	    @Override
	    protected void onPostExecute(String file_url) {
	      
	 
	        // Displaying downloaded image into image view
	        // Reading image path from sdcard
	        String imagePath = imageFolder + "/" + imageList.get(actPosition).getFilename();
	        Log.d("pixbox", "Downloaded file to " + imagePath);
	        // setting downloaded into image view
	       // my_image.setImageDrawable(Drawable.createFromPath(imagePath));
	        
	        openImageInGallery(imagePath);
	        
	    }
	 
	}
	
	public void openImageInGallery(String imagePath){
		Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + imagePath), "image/*");
        startActivity(intent);
	}
	
	
	
	
	
	
	
	
	/**	
	 * Show dialog for error message with given MSG param
	 * @param msg
	 * */
	protected void showDialog(String msg) {
		new AlertDialog.Builder(GalleryActivity.this).setMessage(msg)

				.setPositiveButton(getResources().getString(
						R.string.back),
				// Click Listener
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								//cloase dialog
								dialog.cancel();
								GalleryActivity.super.onBackPressed();
							}
						}
				).create().show();
	}
	
	public void createImageFolder(String folder){
		File f = new File(folder);
		if(!f.exists()){
			f.mkdirs();
		}
	}

}
