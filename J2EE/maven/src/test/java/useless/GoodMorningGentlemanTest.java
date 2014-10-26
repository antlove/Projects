package useless;
import junit.framework.Assert;

import org.junit.Test;

import com.CoberturaStart;
public class GoodMorningGentlemanTest {
	
	@Test
	public void testGoodMorningGentleman(){
		CoberturaStart coberturaStart = new CoberturaStart();
		coberturaStart.goodMorningGentleman();
		Assert.assertTrue(true);
	}
}
