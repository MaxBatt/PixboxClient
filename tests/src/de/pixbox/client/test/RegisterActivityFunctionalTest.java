package de.pixbox.client.test;

import java.util.Random;

import de.pixbox.client.MainActivity;
import de.pixbox.client.RegisterActivity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<RegisterActivity> {

	private RegisterActivity registerActivity;
	private MainActivity mainActivity;
	private EditText editUsername;
	private Button registerButton;
	private ActivityMonitor activityMonitor;
	private TextView tvUserWelcome;

	public RegisterActivityFunctionalTest() {
		super(RegisterActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();

		// Get reference to RegisterActivity
		registerActivity = getActivity();

		// Add ActivityMonitor to get reference to MainActivity later on
		activityMonitor = getInstrumentation().addMonitor(
				MainActivity.class.getName(), null, false);

		// Get references to textfield for username and RegisterButton
		editUsername = (EditText) registerActivity
				.findViewById(de.pixbox.client.R.id.editUsername);
		registerButton = (Button) registerActivity
				.findViewById(de.pixbox.client.R.id.registerButton);

	}

	
	@SmallTest
	public void testRegistration() throws Exception {

		final String incorrectUsername = "foo#bar";
		final String correctUsername = createRandomString();

		// Set incorrect username
		// Setting an EditText must run on the UI thread
		registerActivity.runOnUiThread(new Runnable() {
			public void run() {
				editUsername.setText(incorrectUsername);
			}
		});

		// Wait until instrumentation gets the new Value
		getInstrumentation().waitForIdleSync();

		// Test if RegisterButton is disabled
		assertEquals(
				"RegisterButton must be disabled if username contains not allowed chars",
				registerButton.isEnabled(), false);

		// Set empty username
		registerActivity.runOnUiThread(new Runnable() {
			public void run() {
				editUsername.setText("");
			}
		});

		getInstrumentation().waitForIdleSync();

		// Test if RegisterButton is still disabled
		assertEquals("RegisterButton must be disabled if Username is empty",
				registerButton.isEnabled(), false);

		// Set correct username
		registerActivity.runOnUiThread(new Runnable() {
			public void run() {
				editUsername.setText(correctUsername);
			}
		});

		getInstrumentation().waitForIdleSync();

		// Test if RegisterButton is enabled now
		assertEquals("RegisterButton must be enabled if username is correct",
				registerButton.isEnabled(), true);

		if (registerButton.isEnabled()) {
			// Click registerButton
			TouchUtils.clickView(this, registerButton);

			// Wait 4 seconds for the start of the MainActivity
			mainActivity = (MainActivity) activityMonitor
					.waitForActivityWithTimeout(4000);

			// Test if MainActivity has been started
			assertNotNull("Main Activity was not started after registering",
					mainActivity);

			// Get reference to the TextView for the Welcome Message
			tvUserWelcome = (TextView) mainActivity
					.findViewById(de.pixbox.client.R.id.tvUserWelcome);

			// Test if the TextView is on the screen
			ViewAsserts.assertOnScreen(mainActivity.getWindow().getDecorView(),
					tvUserWelcome);

			// Test if welcome message and username are displayed
			assertEquals(
					"Username is missing in welcome message",
					registerActivity.getResources().getString(
							de.pixbox.client.R.string.user_welcome_text,
							correctUsername), tvUserWelcome.getText()
							.toString());
		}

	}

	protected void tearDown() throws Exception {
		registerActivity.finish();
		mainActivity.finish();
		super.tearDown();
	}

	private String createRandomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

}
