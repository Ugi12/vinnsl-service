package at.ac.univie.a0908270.nncloud.db.parameters;

public class ValueParameter extends Parameter<String> {
	
	public ValueParameter(String name, String label, String value) {
		this.name = name;
		this.label = label;
		this.value = value;
	}
	
	public ValueParameter() {
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
