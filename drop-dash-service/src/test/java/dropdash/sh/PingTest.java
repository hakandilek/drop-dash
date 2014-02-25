package dropdash.sh;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class PingTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Test
	public void test() throws Exception {
		Ping cmd = new Ping();
		String str = cmd.execute();
		assertNotNull(str);
		System.out.println(str);
		assertNotNull(str);
		assertTrue(str.startsWith("[[\""));
		assertTrue(str.endsWith("\"]]"));

		String[][] list = fromJson(str, String[][].class);
		assertNotNull(list);
		assertEquals(4, list.length);
		assertEquals(2, list[0].length);
		assertEquals(2, list[1].length);
		assertEquals(2, list[2].length);
		assertEquals(2, list[3].length);
	}

}
