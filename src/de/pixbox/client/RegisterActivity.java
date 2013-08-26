package de.pixbox.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
		AsyncHttpClient client = new AsyncHttpClient();
		
		try {
			String user = URLEncoder.encode(et.getText()
					.toString(), "UTF-8");
			
			RestClient.get("user/new/?u=" + user, null, new AsyncHttpResponseHandler() {
	            @Override
	            public void onSuccess(String response) {
	                // Do something with the response
	            	pb.setVisibility(View.GONE);
	                System.out.println(response);
	            }
	        });
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
