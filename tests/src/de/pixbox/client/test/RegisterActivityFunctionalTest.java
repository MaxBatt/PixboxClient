package de.pixbox.client.test;

import de.pixbox.client.MainActivity;
import de.pixbox.client.RegisterActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<RegisterActivity> {

	private RegisterActivity registerActivity;
	private EditText editUsername;
	private Button registerButton;
	private ActivityMonitor activityMonitor;

	public RegisterActivityFunctionalTest() {
		super(RegisterActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		// Get reference to RegisterActivity
		registerActivity = getActivity();

		// Add ActivityMonitor to check for MainActivity
		activityMonitor = getInstrumentation().addMonitor(
				MainActivity.class.getName(), null, false);

		// Get references to EditText for username, RegisterButton and TextView
		// for error messages
		editUsername = (EditText) registerActivity
				.findViewById(de.pixbox.client.R.id.editUsername);
		registerButton = (Button) registerActivity
				.findViewById(de.pixbox.client.R.id.registerButton);
		
	}


	public void testStartMainActivity() throws Exception {

		// Clear username
		editUsername.clearComposingText();

		// Set valid username
		registerActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editUsername.setText("dfhjd");
			}
		});

		// Wait until Intrumentation has the new Value
		getInstrumentation().waitForIdleSync();
		
		// Test if RegisterButton is now enabled
		assertEquals(
				"RegisterButton must be enabled",
				registerButton.isEnabled(), true);
		
		
		// Click registerButton
		TouchUtils.clickView(this, registerButton);
		
		
		
		// Wait 2 seconds for the start of the activity
	    MainActivity mainActivity = (MainActivity) activityMonitor
	        .waitForActivityWithTimeout(2000);
	    assertNotNull(mainActivity);

	}

}
