package dropdash.sh;

import static com.yammer.dropwizard.testing.JsonHelpers.fromJson;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class FilterTest {

	private String df;
	private String mem;
	private String online;
	private String ps;
	private String users;
	private String uptime;
	private String whereis;
	private String whereis_out;

	@Before
	public void setUp() throws Exception {
		df = load("df.txt");
		mem = load("mem.txt");
		online = load("online.txt");
		ps = load("ps.txt");
		users = load("users.txt");
		uptime = load("uptime.txt");
		whereis = load("whereis.txt");
		whereis_out = load("whereis.out.txt");
	}

	private String load(String res) throws Exception {
		URL url = Resources.getResource(res);
		String text = Resources.toString(url, Charsets.UTF_8);
		return text;
	}

	@Test
	public void df() {
		String out = new Df().apply(df);
		assertNotNull(out);
		try {
			String[][] data = fromJson(out, String[][].class);
			assertNotNull(data);
			assertEquals(9, data.length);

			assertNotNull(data[0]);
			assertEquals(6, data[0].length);
			assertNotNull(data[0][0]);
			assertNotNull(data[0][1]);
			assertNotNull(data[0][2]);
			assertNotNull(data[0][3]);
			assertNotNull(data[0][4]);
			assertNotNull(data[0][5]);
			assertEquals("/dev/sda6", data[0][0]);
			assertEquals("126G", data[0][1]);
			assertEquals("66G", data[0][2]);
			assertEquals("54G", data[0][3]);
			assertEquals("55%", data[0][4]);
			assertEquals("/", data[0][5]);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e + "");
		}
	}

	@Test
	public void mem() {
		String out = new Mem().apply(mem);
		assertNotNull(out);
		try {
			String[] data = fromJson(out, String[].class);
			assertNotNull(data);
			assertEquals(4, data.length);
			assertNotNull(data[0]);
			assertNotNull(data[1]);
			assertNotNull(data[2]);
			assertNotNull(data[3]);
			assertEquals("Mem:", data[0]);
			assertEquals("7981", data[1]);
			assertEquals("3887", data[2]);
			assertEquals("4093", data[3]);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e + "");
		}
	}

	@Test
	public void online() {
		String out = new Online().apply(online);
		assertNotNull(out);
		System.out.println(out);
		try {
			String[][] data = fromJson(out, String[][].class);
			assertNotNull(data);
			assertEquals(3, data.length);
			assertNotNull(data[0]);
			assertEquals(4, data[0].length);
			assertNotNull(data[0][0]);
			assertNotNull(data[0][1]);
			assertNotNull(data[0][2]);
			assertNotNull(data[0][3]);
			assertEquals("hakan", data[0][0]);
			assertEquals(":0", data[0][1]);
			assertEquals("14:01", data[0][2]);
			assertEquals("5:30m", data[0][3]);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e + "");
		}
	}

	@Test
	public void ps() {
		String out = new Ps().apply(ps);
		assertNotNull(out);
		System.out.println(out);
		try {
			String[][] data = fromJson(out, String[][].class);
			assertNotNull(data);
			assertEquals(196, data.length);
			assertNotNull(data[0]);
			assertEquals(11, data[0].length);
			assertNotNull(data[0][0]);
			assertNotNull(data[0][1]);
			assertNotNull(data[0][2]);
			assertNotNull(data[0][3]);
			assertNotNull(data[0][4]);
			assertNotNull(data[0][5]);
			assertNotNull(data[0][6]);
			assertNotNull(data[0][7]);
			assertNotNull(data[0][8]);
			assertNotNull(data[0][9]);
			assertNotNull(data[0][10]);
			assertEquals("root", data[0][0]);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e + "");
		}
	}

	@Test
	public void users() {
		String out = new Users().apply(users);
		assertNotNull(out);
		System.out.println(out);
		try {
			String[][] data = fromJson(out, String[][].class);
			assertNotNull(data);
			assertEquals(40, data.length);
			assertNotNull(data[0]);
			assertEquals(2, data[0].length);
			assertNotNull(data[0][0]);
			assertNotNull(data[0][1]);
			assertEquals("system,root", data[0][0]);
			assertEquals("/root", data[0][1]);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e + "");
		}
	}
	
	@Test
	public void uptime() {
		String str = new Uptime().apply(uptime);
		assertNotNull(str);
		assertEquals("\"9 hours 54 minutes\"", str);
	}

	@Test
	public void whereis() {
		String str = new Whereis().apply(whereis);
		assertNotNull(str);
		assertEquals(whereis_out, str);
	}


}
