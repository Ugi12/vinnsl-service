package at.ac.univie.a0908270.nncloud.db;

import at.ac.univie.a0908270.nncloud.db.definition.backpropagation.VinnslDefinition;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vinnsl {
	
	@Id
	@XmlElement
	public String identfier;
	
	@XmlElement
	public VinnslDefinition definition;
	
	@XmlElement
	public String description;
	
	@XmlElement
	public String data;
	
	@XmlElement
	public String instance;
	
	@XmlElement
	public String result;
	
	public Vinnsl() {
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"VinnslDefinition[identfier=%s, definition='%s']",
				identfier, definition);
	}
	
}
