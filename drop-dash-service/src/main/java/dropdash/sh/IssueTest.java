package dropdash.sh;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public class IssueTest {

	ObjectMapper json = new ObjectMapperFactory().build();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		Issue cmd = new Issue();
		String str = cmd.execute();
		assertNotNull(str);
		String js = json(str);
		System.out.println(js);
		assertNotNull(js);
		assertTrue(js.startsWith("\""));
		assertTrue(js.endsWith("\""));
	}

	protected <O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}

}
