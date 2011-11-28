package build_farm.common;

public class SetProperty
{
	String name;
	String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public SetProperty() {
		super();
	}
	
	private String file;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	private String prefix;
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	boolean dirAsPrefix = false;
	public boolean isDirAsPrefix() {
		return dirAsPrefix;
	}
	public void setDirAsPrefix(boolean dirAsPrefix) {
		this.dirAsPrefix = dirAsPrefix;
	}
	
}