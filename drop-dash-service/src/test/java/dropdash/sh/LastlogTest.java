package dropdash.sh;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class LastlogTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Test
	public void test() throws Exception {
		Lastlog cmd = new Lastlog();
		String str = cmd.execute();
		assertNotNull(str);
		System.out.println(str);
		assertNotNull(str);
		assertTrue(str.startsWith("[[\""));
		assertTrue(str.endsWith("\"]]"));
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
