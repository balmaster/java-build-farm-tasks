package build_farm.property;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import build_farm.common.SetProperty;

public class EvalPropertiesTask extends Task {
	
	private String srcFile;
	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}

	public String getDstFile() {
		return dstFile;
	}

	public void setDstFile(String dstFile) {
		this.dstFile = dstFile;
	}

	private String dstFile;
	
	public List<SetProperty> getSetProperties() {
		return setProperties;
	}

	private List<SetProperty> setProperties=new ArrayList<SetProperty>();	

	public void addConfiguredSetProperty(SetProperty value) {
		setProperties.add(value);
	}
	
	public void execute() throws BuildException {
		try {
			PropertyBroker propertyBroker = new EPropertiesPropertyBroker(getSrcFile());
			Properties overrides = new Properties();
			for (SetProperty sp : getSetProperties()) {
				overrides.put(sp.getName(), sp.getValue());
			}
			Properties p = propertyBroker.evalAll(overrides);
			OutputStream out = new FileOutputStream(getDstFile());
			p.store(out, "");
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new BuildException(new StringBuilder("Eval properties.").append(e.getMessage()).toString(), e);
		}
	}

}
