/**
 * GTest.java 
 * GTest
 *
 * Created by Kolesin Andrey on 24.07.2009
 * Copyright 2009 Epazzz Software. All rights reserved.
 */
package com.epazzzsoftware.solarzen;

import junit.framework.TestCase;

/**
 * @author s4relok
 *
 */
public class GTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.epazzzsoftware.solarzen.G#init()}.
	 */
	public final void testInit() {
		
		
		assertTrue(G.ARROW_LEN == 223);
	}

}
