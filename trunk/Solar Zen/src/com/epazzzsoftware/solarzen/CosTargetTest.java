/**
 * CosTargetTest.java 
 * CosTargetTest
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
public class CosTargetTest extends TestCase {
		
	private CosTarget ct;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ct = new CosTarget(0, 0, 2);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.epazzzsoftware.solarzen.CosTarget#update()}.
	 */
	public final void testUpdate() {
		int prev = ct.x;
		ct.update();
		assertTrue(ct.x == prev+2);
	}

	/**
	 * Test method for {@link com.epazzzsoftware.solarzen.CosTarget#nextY()}.
	 */
	public final void testNextY() {
		assertTrue(true);
	}

}
