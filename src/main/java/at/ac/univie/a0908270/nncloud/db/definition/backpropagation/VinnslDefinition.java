package at.ac.univie.a0908270.nncloud.db.definition.backpropagation;

import at.ac.univie.a0908270.nncloud.db.parameters.Parameters;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlElement;

public class VinnslDefinition {
	
	@Id
	@XmlElement
	public String identfier;
	
	@XmlElement
	public Structure structure;
	
	@XmlElement
	public Parameters parameters;
	
	public VinnslDefinition() {
	}
	
	public VinnslDefinition(Structure structure) {
		this.structure = structure;
	}
	
	@Override
	public String toString() {
		return String.format(
				"VinnslDefinition[identfier=%s, desc='%s']",
				identfier, structure);
	}
	
}
