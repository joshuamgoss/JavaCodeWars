import static org.junit.Assert.*;

import org.junit.Test;

public class ShortestPathTest {

	@Test
	public void testShortestPath() {
		assertEquals(4,ShortestPath.shortestPath(".W.\n.W.\n..."));
		assertEquals(-1, ShortestPath.shortestPath(".W.\n.W.\n.W."));
		assertEquals(0, ShortestPath.shortestPath("."));
		assertEquals(48,ShortestPath.shortestPath(".W...W...\n.W.W.W.W.\n.W.W.W.W.\n.W.W.W.W.\n.W.W.W.W.\n.W.W.W.W.\n.W.W.W.W.\n.W.W.W.W.\n...W...W.\n"));
	}

}
