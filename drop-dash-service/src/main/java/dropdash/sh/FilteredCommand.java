package dropdash.sh;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.json.ObjectMapperFactory;

public abstract class FilteredCommand extends ShellCommand implements Function<String, String> {

	ObjectMapper json = new ObjectMapperFactory().build();

	Splitter lineSplitter = Splitter.on("\n").trimResults().omitEmptyStrings();
	Splitter commaSplitter = Splitter.on(',').trimResults();
	Splitter semicolSplitter = Splitter.on(';').trimResults();
	Splitter spaceSplitter = Splitter.on(' ').trimResults().omitEmptyStrings();


	public FilteredCommand(String shell, String command) {
		super(shell, command);
		filter = this;
	}

	public FilteredCommand(String command) {
		super(command);
		filter = this;
	}

	List<String> lineSplit(String input) {
		return Lists.newArrayList(lineSplitter.split(input));
	}

	List<String> commaSplit(String input) {
		return Lists.newArrayList(commaSplitter.split(input));
	}

	List<String> semicolSplit(String input) {
		return Lists.newArrayList(semicolSplitter.split(input));
	}

	List<String> spaceSplit(String input) {
		return Lists.newArrayList(spaceSplitter.split(input));
	}

	<O> String json(O obj) throws IOException {
		return json.writeValueAsString(obj);
	}


}
