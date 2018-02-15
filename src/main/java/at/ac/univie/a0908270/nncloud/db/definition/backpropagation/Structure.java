package at.ac.univie.a0908270.nncloud.db.definition.backpropagation;

import javax.xml.bind.annotation.XmlElement;

public class Structure {
	
	@XmlElement
	public Input input;
	
	@XmlElement
	public Output output;
	
	@XmlElement
	public Hidden hidden;
	
	@XmlElement
	public String connections;
	
	public Structure() {
	}
	
	public Structure(Input input, Output output, Hidden hidden, String connections) {
		this.input = input;
		this.output = output;
		this.hidden = hidden;
		this.connections = connections;
	}
}
