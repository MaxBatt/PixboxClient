package de.pixbox.client.test;

import de.pixbox.client.RegisterActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class RegisterActivityUnitTest extends ActivityUnitTestCase<RegisterActivity> {

	private int buttonId;
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
	    
	    
	    assertEquals(1,1);
	  }
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
