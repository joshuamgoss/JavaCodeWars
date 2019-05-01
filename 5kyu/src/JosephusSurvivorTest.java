import static org.junit.Assert.*;

import org.junit.Test;

public class JosephusSurvivorTest {

	@Test
	public void testJosephusSurvivor() {
		assertEquals(4, JosephusSurvivor.josephusSurvivor(7,3));
		assertEquals(10, JosephusSurvivor.josephusSurvivor(11,19));
		assertEquals(28, JosephusSurvivor.josephusSurvivor(40,3));
		assertEquals(13, JosephusSurvivor.josephusSurvivor(14,2));
	}

}
