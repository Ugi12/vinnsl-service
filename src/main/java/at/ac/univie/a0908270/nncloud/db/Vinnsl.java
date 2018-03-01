package at.ac.univie.a0908270.nncloud.db;

import at.ac.univie.a00908270.vinnsl.schema.*;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Vinnsl {
	
	@Id
	public String identifier;
	
	@XmlElement
	public Definition definition;
	
	@XmlElement
	public Description description;
	
	@XmlElement
	public Dataschema data;
	
	@XmlElement
	public Instanceschema instance;
	
	@XmlElement
	public Resultschema result;
	
	@XmlTransient
	public NnCloud nncloud;
	
	public Vinnsl() {
		this.nncloud = new NnCloud();
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"VinnslDefinition[identifier=%s, definition='%s']",
				identifier, definition);
	}
	
}
