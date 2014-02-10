package dropdash.sh;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class NetstatTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Test
	public void test() throws Exception {
		Netstat cmd = new Netstat();
		String str = cmd.execute();
		assertNotNull(str);
		System.out.println(str);
		assertNotNull(str);
		assertTrue(str.startsWith("[[\""));
		assertTrue(str.endsWith("\"]]"));
		
		String[][] list = fromJson(str, String[][].class);
		assertNotNull(list);
		assertTrue(list.length >0);
		assertNotNull(list[0]);
		assertEquals(7, list[0].length);
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
