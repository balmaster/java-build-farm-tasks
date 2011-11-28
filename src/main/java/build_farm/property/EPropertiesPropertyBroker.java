package build_farm.property;

import java.io.IOException;
import java.util.Properties;

import net.jmatrix.eproperties.EProperties;
import net.jmatrix.eproperties.SubstitutionProcessor;

public class EPropertiesPropertyBroker implements PropertyBroker {

	private EProperties properties = new EProperties();

	public EPropertiesPropertyBroker(String propertyFile) throws IOException {
		properties.load(propertyFile);
	}

	public String getProperty(String name) {
		return properties.getString(name);
	}

	public String eval(String expression) throws Exception {
		try
		{
		  return expression!=null? SubstitutionProcessor.processSubstitution(expression, properties):"";
		}
		catch (Exception e) {
			throw new Exception(new StringBuilder().append("Eval expression " ).append(expression).toString(), e);
		}
	}

	public Boolean evalAsBoolean(String expression) throws Exception {
		String result = eval(expression);
		if (result != null)
			return Boolean.parseBoolean(result);
		return false;
	}

	public Properties evalAll(Properties override) throws Exception {
		for ( String opn: override.stringPropertyNames())
		{
			properties.put(opn,override.get(opn));
		}
		Properties result = new Properties();
		for (Object key : properties.keySet()) {
			try
			{

			result.put(key, eval(getProperty((String)key)));			
			}
			catch (Exception e) {
				throw new Exception(new StringBuilder().append("Eval property " ).append(key).append(" expression ").append(getProperty((String)key)).toString(), e);
			}
		}
		return result;
	}

}
