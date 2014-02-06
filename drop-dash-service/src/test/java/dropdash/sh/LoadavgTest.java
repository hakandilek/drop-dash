package dropdash.sh;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class LoadavgTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		Loadavg cmd = new Loadavg();
		String str = cmd.execute();
		assertNotNull(str);
		String js = json(str);
		System.out.println(js);
		assertNotNull(js);
		assertEquals("\"[[\\\"", js.subSequence(0, 5));
		assertEquals("]]\"", js.substring(js.length()-3));
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
