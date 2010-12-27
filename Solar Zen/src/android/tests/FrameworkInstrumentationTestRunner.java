/**
 * FrameworkInstrumentationTestRunner.java 
 * FrameworkInstrumentationTestRunner
 *
 * Created by Kolesin Andrey on 24.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package android.tests;

import com.epazzzsoftware.solarzen.CosTargetTest;
import com.epazzzsoftware.solarzen.GTest;

import junit.framework.TestSuite;
import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;


/**
 * @author s4relok
 *
 */
public class FrameworkInstrumentationTestRunner extends
		InstrumentationTestRunner {
	
	@Override
	public TestSuite getAllTests() {
		
		InstrumentationTestSuite suite = new InstrumentationTestSuite(this);

        suite.addTestSuite(GTest.class);
        suite.addTestSuite(CosTargetTest.class);

        return suite;

	}
	
	@Override
	public ClassLoader getLoader() {
		
		return FrameworkInstrumentationTestRunner.class.getClassLoader();

	}

}
