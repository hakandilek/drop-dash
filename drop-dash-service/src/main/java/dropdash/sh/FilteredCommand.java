package dropdash.sh;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public abstract class FilteredCommand extends ShellCommand implements Function<String, String> {

	private static final Logger log = LoggerFactory
			.getLogger(FilteredCommand.class);

	static ObjectMapper json = new ObjectMapperFactory().build();

	static Splitter lineSplitter = Splitter.on("\n").trimResults().omitEmptyStrings();
	static Splitter commaSplitter = Splitter.on(',').trimResults();
	static Splitter semicolSplitter = Splitter.on(';').trimResults();
	static Splitter spaceSplitter = Splitter.on(' ').trimResults().omitEmptyStrings();
	static Joiner spaceJoiner = Joiner.on(' ').skipNulls();


	public FilteredCommand(String shell, String command) {
		super(shell, command);
		filter = this;
	}

	public FilteredCommand(String command) {
		super(command);
		filter = this;
	}

	static List<String> lineSplit(String input) {
		return Lists.newArrayList(lineSplitter.split(input));
	}

	static List<String> commaSplit(String input) {
		return Lists.newArrayList(commaSplitter.split(input));
	}

	static List<String> semicolSplit(String input) {
		return Lists.newArrayList(semicolSplitter.split(input));
	}

	static List<String> spaceSplit(String input) {
		return Lists.newArrayList(spaceSplitter.split(input));
	}

	static String spaceJoin(List<String> input) {
		return spaceJoiner.join(input);
	}

	static <O> String json(O obj) {
		try {
			return json.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("error on json processing", e);
			return null;
		}
	}


}
