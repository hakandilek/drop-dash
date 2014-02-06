package dropdash.sh;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class IpTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Test
	public void test() throws Exception {
		Ip cmd = new Ip();
		String str = cmd.execute();
		assertNotNull(str);
		System.out.println(str);
		assertNotNull(str);
		assertTrue(str.startsWith("[[\"external ip\",\""));
		assertTrue(str.endsWith("\"]]"));
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
