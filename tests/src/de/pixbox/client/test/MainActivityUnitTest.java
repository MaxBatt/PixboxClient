package de.pixbox.client.test;

import de.pixbox.client.MainActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class MainActivityUnitTest extends ActivityUnitTestCase<MainActivity> {

	private int buttonId;
	private MainActivity activity;

	public MainActivityUnitTest() {
		super(MainActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
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
