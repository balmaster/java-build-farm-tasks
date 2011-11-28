package build_farm.password;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ReadPasswordTask extends Task {
	
	private String property;
	private String prompt;
	
	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public void execute() throws BuildException {
		try {
			String password = new jline.ConsoleReader().readLine(getPrompt(), new Character((char)0));
			getProject().setProperty(getProperty(), password);
		} catch (IOException e) {
			throw new BuildException("Read password",e);
		}

	}

}
