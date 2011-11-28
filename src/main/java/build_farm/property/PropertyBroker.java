package build_farm.property;

import java.util.Properties;

public interface PropertyBroker {
	String getProperty(String name);

	String eval(String expression) throws Exception;
	Boolean evalAsBoolean(String expression) throws Exception;
	Properties evalAll(Properties override) throws Exception;
}
