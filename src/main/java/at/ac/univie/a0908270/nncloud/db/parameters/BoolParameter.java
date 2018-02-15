package at.ac.univie.a0908270.nncloud.db.parameters;

public class BoolParameter extends Parameter<Boolean> {
	
	public BoolParameter(String name, String label, Boolean value) {
		this.name = name;
		this.label = label;
		this.value = value;
	}
	
	public BoolParameter() {
	}
	
	public Boolean getValue() {
		return value;
	}
	
	public void setValue(Boolean value) {
		this.value = value;
	}
}
