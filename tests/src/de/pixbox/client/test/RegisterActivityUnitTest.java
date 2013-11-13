package de.pixbox.client.test;

import de.pixbox.client.RegisterActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

public class RegisterActivityUnitTest extends
		ActivityUnitTestCase<RegisterActivity> {

	private RegisterActivity registerActivity;
	private int buttonID;
	private Button registerButton;

	public RegisterActivityUnitTest() {
		super(RegisterActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Start RegisterActivity
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				RegisterActivity.class);
		startActivity(intent, null, null);

		// Get reference to RegisterActivity
		registerActivity = getActivity();
	}

	@SmallTest
	public void testLayout() {
		// Test if RegisterButton exists and is initialized
		buttonID = de.pixbox.client.R.id.registerButton;
		assertNotNull("Register Button must be initialized",
				registerActivity.findViewById(buttonID));

		registerButton = (Button) registerActivity.findViewById(buttonID);

		// Test if RegisterButton has the right Text
		assertEquals("Incorrect label of the Register Button", "Create user",
				registerButton.getText());

		// Test if RegisterButton is disabled
		assertEquals(
				"RegisterButton must be disabled at the Start of the activity",
				registerButton.isEnabled(), false);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
