package de.pixbox.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.widget.TextView;
//  
public class HttpGetter extends AsyncTask<Void, Void, String> {

	String result = "fail";
	
	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return GetSomething();
	}
	
	final String GetSomething()
	{
		String url = "http://www.maxbatt.de/picbox/pictures/upload";
		BufferedReader inStream = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpRequest = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpRequest);
			inStream = new BufferedReader(
				new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer buffer = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = inStream.readLine()) != null) {
				buffer.append(line + NL);
			}
			inStream.close();

			result = buffer.toString();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	protected void onPostExecute(String page)
	{    	
	}	
}