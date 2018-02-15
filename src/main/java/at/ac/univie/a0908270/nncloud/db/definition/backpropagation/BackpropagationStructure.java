package at.ac.univie.a0908270.nncloud.db.definition.backpropagation;

import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlElement;

public abstract class BackpropagationStructure {
	
	@Id
	@XmlElement
	public String id;
	
	@XmlElement
	public Integer dimension;
	
	@XmlElement
	public Integer size;
	
	public BackpropagationStructure() {
	}
}
