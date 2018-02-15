package at.ac.univie.a0908270.nncloud.db.parameters;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlElement;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "_class")
public abstract class Parameter<T> {
	@XmlElement
	public String name;
	@XmlElement
	public String label;
	@XmlElement
	public T value;
	
}
