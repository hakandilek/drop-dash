package dropdash.sh;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class BandwidthTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Test
	public void test() throws Exception {
		Bandwidth cmd = new Bandwidth();
		String str = cmd.execute();
		assertNotNull(str);
		System.out.println(str);
		assertNotNull(str);
		assertTrue(str.startsWith("{"));
		assertTrue(str.endsWith("}"));

		@SuppressWarnings("unchecked")
		Map<String, Integer> map = fromJson(str, HashMap.class);
		assertNotNull(map);
		assertEquals(2, map.size());
		assertNotNull(map.get("rx"));
		assertNotNull(map.get("tx"));
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
