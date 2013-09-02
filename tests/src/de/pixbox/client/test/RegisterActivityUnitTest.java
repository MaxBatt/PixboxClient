package de.pixbox.client.test;

import de.pixbox.client.RegisterActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

public class RegisterActivityUnitTest extends ActivityUnitTestCase<RegisterActivity> {

	private int registerButtonID;
	private RegisterActivity activity;

	public RegisterActivityUnitTest() {
		super(RegisterActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				RegisterActivity.class);
		startActivity(intent, null, null);
		activity = getActivity();
	}

	
	@SmallTest
	  public void testLayout() {
	    
		registerButtonID = de.pixbox.client.R.id.registerButton;
	    assertNotNull("RegisterButton does not exist", activity.findViewById(registerButtonID));
	    Button registerButton = (Button) activity.findViewById(registerButtonID);
	    assertEquals("Incorrect label of the Register Button", activity.getResources().getString(de.pixbox.client.R.string.register_button), registerButton.getText());
	  }
	
	protected void tearDown() throws Exception {

	}
		

}
