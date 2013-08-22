package com.mycompany.helloworld;

import junit.framework.TestCase;

public class AddTest extends TestCase {

	private static Add add = new Add();

	public AddTest(String name) {
		super(name);
	}

	public void testAdd() {

		assertEquals(4, add.add(2, 2));
	}

}
