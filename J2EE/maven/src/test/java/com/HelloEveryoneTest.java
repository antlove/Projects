package com;
import org.junit.Test;
import junit.framework.Assert;
public class HelloEveryoneTest {
	@Test
	public void testHelloEveryone(){
		CoberturaStart coberturaStart = new CoberturaStart();
		coberturaStart.helloEveryone();
		Assert.assertTrue(true);
	}
}
