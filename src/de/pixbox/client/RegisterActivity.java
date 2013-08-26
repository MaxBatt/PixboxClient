package de.pixbox.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private EditText et;
	private Button btn;
	private TextView tv;
	private ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);

		et = (EditText) findViewById(R.id.editUsername);
		btn = (Button) findViewById(R.id.okBtn);
		tv = (TextView) findViewById(R.id.tvUserResult);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(View.GONE);

		btn.setEnabled(false);

		// EditTextOnChange
		et.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {

				if (isEmpty(et)) {
					btn.setEnabled(false);
				} else {
					btn.setEnabled(true);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Button Click
	public void onBtnClicked(View v) {
		pb.setVisibility(View.VISIBLE);
		new MyAsyncTask().execute(et.getText().toString());
	}

	private class MyAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String response = postData(params[0]);
			return response;
		}

		String error = "An error has occured";
		protected void onPostExecute(String response) {
			pb.setVisibility(View.GONE);
			
			if(response.contains("http://")){
				tv.setText(response);
			}
			else{
				tv.setText(error + response);
			}
			
		}

		protected void onProgressUpdate(Integer... progress) {
			pb.setProgress(progress[0]);
		}

		public String postData(String username) {
			try {

				String usernameValue = URLEncoder.encode(et.getText()
						.toString(), "UTF-8");

				// Create http cliient object to send request to server
				HttpClient client = new DefaultHttpClient();

				String URL = "http://www.maxbatt.de/picbox/user/new?u="
						+ usernameValue;
				System.out.println(URL);

				try {
					String response = "";

					// Create Request to server and get response

					HttpGet httpget = new HttpGet(URL);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					response = client.execute(httpget, responseHandler);

					// Show response on activity

					System.out.println("Response: " + response);
					return response;
				
				} catch (Exception ex) {
					System.out.println("Error: " + ex);
				}
			} catch (UnsupportedEncodingException ex) {
				System.out.println("Error: " + ex);
			}
			return null;
		}

	}

	private boolean isEmpty(EditText etText) {
		if (etText.getText().toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}

}
